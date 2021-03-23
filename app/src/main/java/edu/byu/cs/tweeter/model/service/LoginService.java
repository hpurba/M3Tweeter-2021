package edu.byu.cs.tweeter.model.service;

import java.io.IOException;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

/**
 * Contains the business logic to support the login operation.
 */
public class LoginService extends BaseService {

    LoginResponse loginResponse;
    LoginRequest loginRequest;

    public LoginResponse login(LoginRequest request) throws IOException {
        this.loginRequest = request;
        processServiceRequest();
        return loginResponse;
    }

    @Override
    public void doServiceSpecificTask() {
        loginResponse = serverFacade.login(loginRequest);

        if(loginResponse.isSuccess()) {
            try {
                loadImage(loginResponse.getUser());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

///**
// * Contains the business logic to support the login operation.
// */
//public class LoginService {
//
//    public LoginResponse login(LoginRequest request) throws IOException {
//        ServerFacade serverFacade = getServerFacade();
//        LoginResponse loginResponse = serverFacade.login(request);
//
//        if(loginResponse.isSuccess()) {
//            loadImage(loginResponse.getUser());
//        }
//
//        return loginResponse;
//    }
//
//    /**
//     * Loads the profile image data for the user.
//     *
//     * @param user the user whose profile image data is to be loaded.
//     */
//    private void loadImage(User user) throws IOException {
//        byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
//        user.setImageBytes(bytes);
//    }
//
//    /**
//     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
//     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
//     * method to allow for proper mocking.
//     *
//     * @return the instance.
//     */
//    public ServerFacade getServerFacade() {
//        return new ServerFacade();
//    }
//}
