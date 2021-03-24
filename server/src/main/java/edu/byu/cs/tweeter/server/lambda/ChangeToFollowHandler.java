package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;
import edu.byu.cs.tweeter.server.service.ChangeToFollowServiceImpl;

public class ChangeToFollowHandler implements RequestHandler<FollowingStatusRequest, FollowingStatusResponse> {
    @Override
    public FollowingStatusResponse handleRequest(FollowingStatusRequest request, Context context) {
        ChangeToFollowServiceImpl changeToFollowService = new ChangeToFollowServiceImpl();
        return changeToFollowService.changeToFollow(request);
    }
}
