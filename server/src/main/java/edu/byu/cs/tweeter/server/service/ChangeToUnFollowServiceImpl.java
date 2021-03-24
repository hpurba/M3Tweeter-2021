package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;
import edu.byu.cs.tweeter.server.dao.ChangeToUnFollowDAO;

public class ChangeToUnFollowServiceImpl {
    public FollowingStatusResponse changeToUnFollow(FollowingStatusRequest request) {
        return changeToUnFollowDAO().changeToUnFollow(request);
    }
    ChangeToUnFollowDAO changeToUnFollowDAO() { return new ChangeToUnFollowDAO(); }
}
