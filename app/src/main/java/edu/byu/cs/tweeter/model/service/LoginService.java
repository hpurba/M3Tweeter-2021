package edu.byu.cs.tweeter.model.service;
import java.io.IOException;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

/**
 * LoginService extends the BaseService Abstract Class to login the user.
 */
public class LoginService extends BaseService {
    // Login Request and Response Objects.
    LoginRequest loginRequest;
    LoginResponse loginResponse;

    // The url_path extension for login. (Can be found in AWS console -> API:Tweeter -> Stages -> dev tab)
    private static final String URL_PATH = "/loginuser";

    /**
     * This is called to login a existing user.
     * Takes a LoginRequest as the parameter and returns a LoginResponse.
     *
     * @param request A LoginRequest Object.
     * @return A LoginResponse Object.
     * @throws IOException
     */
    public LoginResponse login(LoginRequest request) throws IOException, TweeterRemoteException {
        this.loginRequest = request;
        processServiceRequest();    // Sets up the ServerFacade and calls the doServiceSpecificTask.
        return loginResponse;
    }
    /**
     * Loads the profile image data for the user.
     *
     * @param user the user whose profile image data is to be loaded.
     */
    public void loadImage(User user) throws IOException {
        byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
        user.setImageBytes(bytes);
    }

    /**
     * This is the primary method in the Template pattern of the BaseService Class.
     * This will login a user by calling the login method in the server facade
     * by passing it the provided loginRequest (which is first passed into login).
     * Returning the loginResponse is handled in the login() method.
     */
    @Override
    public void doServiceSpecificTask() throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        LoginResponse loginResponse = serverFacade.login(loginRequest, URL_PATH);
        if(loginResponse.isSuccess()) {
            loadImage(loginResponse.getUser());
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


// TODO: DELETE THIS WHEN WE KNOW THE LOGIN IS WORKING.
// Original before implementing template method pattern.
///**
// * LoginService extends the BaseService Abstract Class to login the user.
// */
//public class LoginService implements ILoginService {
//    // The url_path extension for login. (Can be found in AWS console -> API:Tweeter -> Stages -> dev tab)
//    private static final String URL_PATH = "/loginuser";
//
//
//    public LoginResponse login(LoginRequest request) throws IOException, TweeterRemoteException {
//        ServerFacade serverFacade = getServerFacade();
//        LoginResponse loginResponse = serverFacade.login(request, URL_PATH);
//        if(loginResponse.isSuccess()) {
//            loadImage(loginResponse.getUser());
//        }
//        return loginResponse;
//    }
//    /**
//     * Loads the profile image data for the user.
//     *
//     * @param user the user whose profile image data is to be loaded.
//     */
//    private void loadImage(User user) throws IOException {
//        byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
//        user.setImageBytes(bytes);
//    }
//    /**
//     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
//     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
//     * method to allow for proper mocking.
//     *
//     * @return the instance.
//     */
//    ServerFacade getServerFacade() {
//        return new ServerFacade();
//    }
//}