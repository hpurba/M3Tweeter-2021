package edu.byu.cs.tweeter.model.service;

import java.io.IOException;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

public class LogoutService extends BaseService {
    LogoutRequest logoutRequest;
    LogoutResponse logoutResponse;

    public LogoutResponse logout(LogoutRequest request) throws IOException {
        this.logoutRequest = request;
        processServiceRequest();
        return logoutResponse;
    }

    @Override
    public void doServiceSpecificTask() {
        logoutResponse = serverFacade.logout(logoutRequest);
        if(logoutResponse.isSuccess()) {
            // Success
        }
    }
}