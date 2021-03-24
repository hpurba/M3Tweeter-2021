package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;
import edu.byu.cs.tweeter.server.dao.ChangeToUnFollowDAO;

public class ChangeToUnFollowServiceImpl {
    public FollowingStatusResponse changeToUnFollow(FollowingStatusRequest request) {
        if (request == null) {
            throw new RuntimeException("[BadRequest400] 400");
        } else if (request.getUser() == null || request.getFollowing() == null) {
            throw new RuntimeException("[BadRequest500] 500");
        }
        return changeToUnFollowDAO().changeToUnFollow(request);
    }
    ChangeToUnFollowDAO changeToUnFollowDAO() { return new ChangeToUnFollowDAO(); }
}
