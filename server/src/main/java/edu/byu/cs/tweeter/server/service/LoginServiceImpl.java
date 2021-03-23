package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.ILoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.server.dao.LoginDAO;

/**
 * Login services layer to which the handler delegates each request.
 */
public class LoginServiceImpl implements ILoginService {

    @Override
    public LoginResponse login(LoginRequest request) {
        return loginAttemptDAO().verifyLogin(request);
    }

    LoginDAO loginAttemptDAO() {
        return new LoginDAO();
    }
}
