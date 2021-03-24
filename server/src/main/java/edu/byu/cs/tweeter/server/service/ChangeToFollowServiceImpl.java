package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;
import edu.byu.cs.tweeter.server.dao.ChangeToFollowDAO;

public class ChangeToFollowServiceImpl {

    public FollowingStatusResponse changeToFollow(FollowingStatusRequest request) {
        if (request == null) {
            throw new RuntimeException("[BadRequest400] 400");
        } else if (request.getUser() == null || request.getFollowing() == null) {
            throw new RuntimeException("[BadRequest500] 500");
        }
        return changeToFollowDAO().changeToFollow(request);
    }
    ChangeToFollowDAO changeToFollowDAO() { return new ChangeToFollowDAO(); }
}
