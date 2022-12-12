package application.service;

import application.domain.Community;
import application.domain.Friend;
import application.domain.User;
import application.repository.memory.CommunityRepository;
import application.utils.observer.Observable;

import java.util.*;

/**
 * The class <b>NetworkService</b> is used to manipulate data from more services related to the social network.
 */
public class NetworkService extends Observable {
    public final UserService userService;
    public final FriendshipService friendshipService;
    private final CommunityService communityService;
    private Friend currentUser;

    /**
     * Constructs a new NetworkService
     *
     * @param userService       the service of Users
     * @param friendshipService the service of Friendships
     */
    public NetworkService(UserService userService, FriendshipService friendshipService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.communityService = new CommunityService(userService);
    }

    /**
     * Deletes a User and it's Friendships.
     *
     * @param user the User to be deleted
     */
    public void deleteUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("The user cannot be null!");
        }
        userService.findFriendsOf(currentUser.getID(), currentUser.getID())
                .forEach(friend -> friendshipService.delete(user.getID(), friend.getID()));
        userService.delete(user);
    }

    /**
     * Generates all the communities from the network into the CommunityRepository, a community should have at least 2 members.
     */
    private void findAllCommunities() {
        communityService.clear();
        /*Map<UUID, Boolean> visited = new HashMap<>();
        userService.findAll().forEach(user -> {
            if (!visited.containsKey(user.getID())) {
                List<User> userList = new ArrayList<>();
                populateCommunityUserListFromUser(userService.find(user.getID()), userList, visited);
                if (userList.size() > 1) {
                    communityService.saveCommunity(userList);
                }
            }
        });*/
    }

    /**
     * Populates the list of users from a community, starting with a given User
     * @param user User, it's not in a Community, starting point
     * @param userList List of Users, representing the Users in the current Community
     * @param visited Map of Users to Boolean, a User is marked as true in the map if he's already on a Community
     */
    private void populateCommunityUserListFromUser(User user, List<User> userList, Map<UUID, Boolean> visited) {
        visited.put(user.getID(), true);
        userList.add(user);

        userService.findFriendsOf(currentUser.getID(), user.getID()).forEach(friend -> {
            if (!visited.containsKey(friend.getID())) {
                populateCommunityUserListFromUser(userService.find(friend.getID()), userList, visited);
            }
        });
    }

    public Optional<Community> getCommunityFromUser(User user) {
        List<User> userList = new ArrayList<>();
        Map<UUID, Boolean> visited = new HashMap<>();
        populateCommunityUserListFromUser(userService.find(user.getID()), userList, visited);
        if (userList.size() == 1) {
            return Optional.empty();
        }
        Community community = new Community(userList);
        community.setFriendshipsCount(communityService.countFriendships(community));
        community.setSocialScore(communityService.calculateSocialScore(community));
        return Optional.of(community);
    }

    /**
     * @return the list of Communities in the Network
     */
    public Collection<Community> getCommunities() {
        findAllCommunities();
        return communityService.findAll().stream().sorted((o1, o2) -> o1.getSocialScore() != o2.getSocialScore() ?
                -1 * Integer.compare(o1.getSocialScore(), o2.getSocialScore()) :
                -1 * Integer.compare(o1.getFriendshipsCount(), o2.getFriendshipsCount())).toList();
    }

    public void login(String mailAddress, String password) {
        User user = userService.getUserByMailAndPassword(mailAddress, password);
        currentUser = new Friend(user, null, 0);
    }

    public Friend getCurrentUser() {
        return currentUser;
    }

    public List<Friend> findByName(String subString) {
        return userService.findByName(currentUser.getID(), subString);
    }

    public List<Friend> findCommonFriends(UUID user) {
        return userService.findCommonFriends(currentUser.getID(), user);
    }

    public List<Friend> findFriendsOf(UUID user) {
        return userService.findFriendsOf(currentUser.getID(), user);
    }

    public Friend findFriend(UUID userID) {
        if (currentUser.getID().equals(userID)) {
            return new Friend(currentUser, null, 0);
        }
        return userService.findFriend(currentUser.getID(), userID);
    }
}