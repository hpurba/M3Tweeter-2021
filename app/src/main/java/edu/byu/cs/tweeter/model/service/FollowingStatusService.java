package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;

public class FollowingStatusService extends BaseService {

    FollowingStatusResponse followingStatusResponse;
    FollowingStatusRequest followingStatusRequest;

    public FollowingStatusResponse getFollowingStatus(FollowingStatusRequest request) throws IOException {
        this.followingStatusRequest = request;
        processServiceRequest();
        return followingStatusResponse;
    }

    @Override
    public void doServiceSpecificTask() throws IOException {
        followingStatusResponse = serverFacade.checkFollowingStatus(followingStatusRequest);
//        if(followingStatusResponse.isSuccess()) {
//
//        }
    }
}
