package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

/**
 * FollowerService extends the BaseService Abstract Class to get the Followers of a user.
 */
public class FollowerService extends BaseService {

    // Follower Response and Request Objects.
    FollowerResponse followerResponse;
    FollowerRequest followerRequest;

    /**
     * This is called to get the Followers for a user (followee).
     * Takes a FollowerRequest as the parameter and returns a FollowerResponse.
     *
     * @param request A FollowerRequest Object.
     * @return A FollowerResponse Object which contains the followers of the user.
     */
    public FollowerResponse getFollowers(FollowerRequest request) throws IOException {
        this.followerRequest = request;
        processServiceRequest();
        return followerResponse;
    }

    /**
     * This is the primary method in the Template pattern of the BaseService Abstract Class.
     * This will get the Followers from the server facade of a user (followee) using the provided
     * FollowerRequest (which is first passed into getFollowers).
     */
    @Override
    public void doServiceSpecificTask() throws IOException {
        followerResponse = getServerFacade().getFollowers(followerRequest);
        // Images must be loaded into a byte array.
        if(followerResponse.isSuccess()) {
            loadImages(followerResponse);
        }
    }

    /**
     * Loads the profile image data for each follower included in the response.
     * @param response the response from the follower request.
     */
    public void loadImages(FollowerResponse response) throws IOException {
        for(User user : response.getFollowers()) {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        }
    }
}