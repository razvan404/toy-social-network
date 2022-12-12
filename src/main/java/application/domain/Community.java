package application.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The class <b>Community</b> represents an undirected connected graph for the users of the application, based on their friendships.
 */
public class Community extends Entity<Long> {
    private final List<User> userList;
    private static long CURRENT_ID = 0L;
    private int socialScore;
    private int friendshipsCount;

    /**
     * Class constructor for the Community.
     * @param userList List of Users, the members of the Community
     */
    public Community(List<User> userList) {
        super(++CURRENT_ID);
        this.userList = userList;
    }

    /**
     * Resets the current identifier for the communities.
     */
    public static void resetID() {
        CURRENT_ID = 0L;
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