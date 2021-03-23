package edu.byu.cs.tweeter.model.service;

import java.io.IOException;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

/**
 * FollowingService extends the BaseService Abstract Class to get the Followers of a user.
 */
public class FollowingService extends BaseService {

    // Following Response and Request Objects.
    FollowingResponse followingResponse;
    FollowingRequest followingRequest;

    /**
     * This is called to get the Followees of a user (people the user is following).
     * Takes a FollowingRequest as the parameter and returns a FollowingResponse.
     *
     * @param request A FollowerRequest Object.
     * @return A FollowerResponse Object which contains the followers of the user.
     */
    public FollowingResponse getFollowees(FollowingRequest request) throws IOException {
        this.followingRequest = request;
        processServiceRequest();
        return followingResponse;
    }

    /**
     * This is the primary method in the Template pattern of the BaseService Abstract Class.
     * This will get the Followees of a user from the server facade (people the user is following)
     * using the provided FollowingRequest (which is first passed into getFollowees).
     */
    @Override
    public void doServiceSpecificTask() throws IOException {
        followingResponse = getServerFacade().getFollowees(followingRequest);
        if(followingResponse.isSuccess()) {
            loadImages(followingResponse);
        }
    }

    /**
     * Loads the profile image data for each followee included in the response.
     * @param response the response from the followee request.
     */
    private void loadImages(FollowingResponse response) throws IOException {
        for(User user : response.getFollowees()) {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        }
    }
}