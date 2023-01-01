package application.model;

import java.util.Collection;
import java.util.List;

/**
 * The class <b>Community</b> represents an undirected connected graph for the users of the application, based on their friendships.
 */
public class Community extends Entity<Long> {
    private final List<User> userList;
    private int socialScore;
    private int friendshipsCount;

    /**
     * Class constructor for the Community.
     * @param userList List of Users, the members of the Community
     */
    public Community(Long aLong, List<User> userList) {
        super(++aLong);
        this.userList = userList;
    }


    /**
     * @return the members of the Community
     */
    public Collection<User> getUserList() {
        return userList;
    }

    /**
     * @return the social score of a community
     */
    public int getSocialScore() {
        return socialScore;
    }

    /**
     * @return the number of friendships in the community
     */
    public int getFriendshipsCount() {
        return friendshipsCount;
    }

    public void setSocialScore(int socialScore) {
        this.socialScore = socialScore;
    }

    public void setFriendshipsCount(int friendshipsCount) {
        this.friendshipsCount = friendshipsCount;
    }

    @Override
    public String toString() {
        return "Community " + getID() + " (" + socialScore + " social score + " + friendshipsCount + " friendships)";
    }





}