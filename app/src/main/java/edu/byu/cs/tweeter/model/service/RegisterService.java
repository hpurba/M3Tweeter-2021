package edu.byu.cs.tweeter.model.service;

import java.io.IOException;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterService extends BaseService {

    RegisterResponse registerResponse;
    RegisterRequest registerRequest;

    public RegisterResponse register(RegisterRequest request) throws IOException {
        this.registerRequest = request;
        processServiceRequest();
        return registerResponse;
    }

    @Override
    public void doServiceSpecificTask() {
        try {
            registerResponse = serverFacade.register(registerRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}