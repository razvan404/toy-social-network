package application.service;

import application.model.Community;
import application.model.User;
import application.repository.memory.CommunityRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunityService {
    private final CommunityRepository repository;
    private final UserService userService;
    public long identity = 0;

    public CommunityService(UserService userService) {
        this.repository = CommunityRepository.getInstance();
        this.userService = userService;

    }

    public void saveCommunity(List<User> userList) {
        Community community = new Community(++identity, userList);
        community.setFriendshipsCount(countFriendships(community));
        community.setSocialScore(calculateSocialScore(community));
        repository.save(community);
    }

    public void clear() {
        identity = 0;
        repository.clear();
    }

    /**
     * Counts how many friendships are in the community
     * @return the number of friendships in the community
     */
    public int countFriendships(Community community) {
        AtomicInteger vertices = new AtomicInteger(0);
        community.getUserList().forEach(user -> userService.findFriendsOf(user.getID(), user.getID())
                .forEach(friend -> vertices.set(vertices.get() + 1)));
        return vertices.get() / 2;
    }

    /**
     * Calculates the distance of the longest path from a given node in the community.
     * @param user the current node in the community graph
     * @return the distance of the longest path from the current node to any unvisited node in the community graph
     */
    private int longestPathFromNode(User user, Set<UUID> visited) {
        AtomicInteger maxDistance = new AtomicInteger(1);
        userService.findFriendsOf(user.getID(), user.getID()).forEach(friend -> {
            if (!visited.contains(friend.getID())) {
                visited.add(friend.getID());
                int distance = longestPathFromNode(userService.find(friend.getID()), visited) + 1;
                visited.remove(friend.getID());
                maxDistance.set(Math.max(maxDistance.get(), distance));
            }
        });
        return maxDistance.get();
    }

    /**
     * Calculates the distance of the longest path from the entire community.
     * @return the distance of the longest path in the community graph
     */
    public int calculateSocialScore(Community community) {
        AtomicInteger maxDistance = new AtomicInteger(0);
        community.getUserList().forEach(user -> {
            Set<UUID> visited = new HashSet<>();
            visited.add(user.getID());
            int distance = longestPathFromNode(user, visited);
            maxDistance.set(Math.max(maxDistance.get(), distance));
        });
        return maxDistance.get();
    }

    public Collection<Community> findAll() {
        return repository.findAll();
    }
}
