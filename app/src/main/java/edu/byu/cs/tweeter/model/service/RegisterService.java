package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterService extends BaseService {

    RegisterResponse registerResponse;
    RegisterRequest registerRequest;

    // The url_path extension for register. (Can be found in AWS console -> API:Tweeter -> Stages -> dev tab)
    private static final String URL_PATH = "/registeruser";

    public RegisterResponse register(RegisterRequest request) throws IOException {
        this.registerRequest = request;
        processServiceRequest();
        return registerResponse;
    }

    @Override
    public void doServiceSpecificTask() throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        this.registerResponse = serverFacade.register(registerRequest, URL_PATH);
        if(registerResponse.isSuccess()) {
            loadImage(registerResponse.getUser());
        }
    }

    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the instance.
     */
    public ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}