package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

public class LogoutService extends BaseService implements ILogoutService {
    LogoutRequest logoutRequest;
    LogoutResponse logoutResponse;

    // The url_path extension for login. (Can be found in AWS console -> API:Tweeter -> Stages -> dev tab)
    private static final String URL_PATH = "/logout";


    public LogoutResponse logout(LogoutRequest request) throws IOException {
        this.logoutRequest = request;
        processServiceRequest();
        return logoutResponse;
    }

    @Override
    public void doServiceSpecificTask() throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        this.logoutResponse = serverFacade.logout(logoutRequest, URL_PATH);
    }
}