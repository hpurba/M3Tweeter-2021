package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

/**
 * The presenter for the login functionality of the application.
 */
public class LoginPresenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
        // TODO: This should have get username field, get password field.
        String getUsernameText();
        String getPasswordText();
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public LoginPresenter(View view) {
        this.view = view;
    }

    /**
     * Makes a login request.
     *
     */
//    public LoginResponse login(LoginRequest loginRequest) throws IOException, TweeterRemoteException {
    public LoginResponse login() throws IOException, TweeterRemoteException {

        LoginRequest loginRequest = new LoginRequest(view.getUsernameText(), view.getPasswordText());

        LoginService loginService = getLoginService();
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null){
            throw new IOException();
        }
        return loginService.login(loginRequest);
    }

    LoginService getLoginService() {
        return new LoginService();
    }
}
