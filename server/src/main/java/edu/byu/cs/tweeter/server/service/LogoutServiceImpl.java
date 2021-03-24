package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.server.dao.LogoutDAO;

public class LogoutServiceImpl {

    public LogoutResponse logout(LogoutRequest request) {
        return logoutDAO().logout(request);
    }

    LogoutDAO logoutDAO() { return new LogoutDAO(); }
}
