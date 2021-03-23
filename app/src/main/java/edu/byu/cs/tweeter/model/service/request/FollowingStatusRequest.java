package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.User;

public class FollowingStatusRequest {

    private User user;
//    private final String myUsername;
//    private final String otherPersonUsername;
    private Boolean isFollowing;

    /**
     * Creates an instance.
     *
     * @param user
     * @param isFollowing
     */
    public FollowingStatusRequest(User user, Boolean isFollowing) {
        this.user = user;
        this.isFollowing = isFollowing;
    }

    public User getUser() {
        return user;
    }
//    public String getMyUsername() {
//        return myUsername;
//    }
//    public String getOtherPersonUsername() {
//        return otherPersonUsername;
//    }

    public Boolean getFollowing() {
        return isFollowing;
    }
}
