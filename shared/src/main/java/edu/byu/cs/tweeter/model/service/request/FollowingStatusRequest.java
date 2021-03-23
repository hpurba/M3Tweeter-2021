package edu.byu.cs.tweeter.model.service.request;

public class FollowingStatusRequest {


    private final String myUsername;
    private final String otherPersonUsername;
    private Boolean isFollowing;

    /**
     * Creates an instance.
     *
     * @param myUsername the username of the user to be logged in.
     * @param otherPersonUsername
     */
    public FollowingStatusRequest(String myUsername, String otherPersonUsername, Boolean isFollowing) {
        this.myUsername = myUsername;
        this.otherPersonUsername = otherPersonUsername;
        this.isFollowing = isFollowing;
    }


    public String getMyUsername() {
        return myUsername;
    }

    public String getOtherPersonUsername() {
        return otherPersonUsername;
    }

    public Boolean getFollowing() {
        return isFollowing;
    }
}
