package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

public class FollowerService extends BaseService {

    FollowerResponse followerResponse;
    FollowerRequest followerRequest;

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followers returned and to return the next set of
     * followers after any that were returned in a previous request. Uses the {@link ServerFacadeOriginalM2} to
     * get the followers from the server.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followers.
     */
    public FollowerResponse getFollowers(FollowerRequest request) throws IOException {
        this.followerRequest = request;
        processServiceRequest();
        return followerResponse;
    }

    @Override
    public void doServiceSpecificTask() throws IOException {

        followerResponse = getServerFacade().getFollowers(followerRequest);

        if(followerResponse.isSuccess()) {
            loadImages(followerResponse);
        }
    }

    /**
     * Loads the profile image data for each follower included in the response.
     *
     * @param response the response from the follower request.
     */
    public void loadImages(FollowerResponse response) throws IOException {
        for(User user : response.getFollowers()) {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        }
    }

}
