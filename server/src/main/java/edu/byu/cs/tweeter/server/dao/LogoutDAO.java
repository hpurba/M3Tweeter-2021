package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

public class LogoutDAO {
    public LogoutResponse logout(LogoutRequest request){
        LogoutResponse logoutResponse;
        logoutResponse = new LogoutResponse(true);

//        if (request.getUsername() != null) {
//            logoutResponse = new LogoutResponse(true);
//        }
//        else {
//            logoutResponse = new LogoutResponse(false);
//        }
        return logoutResponse;
    }
}