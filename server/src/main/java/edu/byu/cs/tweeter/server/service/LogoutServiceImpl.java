package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.server.dao.LogoutDAO;

public class LogoutServiceImpl {

    public LogoutResponse logout(LogoutRequest request) {
        if (request == null) {
            throw new RuntimeException("[BadRequest400] 400");
        } else if (request.getUsername() == null) {
            throw new RuntimeException("[BadRequest500] 500");
        }
        return logoutDAO().logout(request);
    }

    LogoutDAO logoutDAO() { return new LogoutDAO(); }
}
