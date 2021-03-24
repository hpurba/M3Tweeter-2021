package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.server.dao.FollowerDAO;

public class FollowerServiceImpl {
    public FollowerResponse getFollowers(FollowerRequest request) {
        return getFollowerDAO().getFollowers(request);
    }

    FollowerDAO getFollowerDAO() {
        return new FollowerDAO();
    }
}
