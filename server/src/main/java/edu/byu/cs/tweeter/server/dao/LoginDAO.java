package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class LoginDAO {

    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private final User user1 = new User("Hikaru", "Purba", MALE_IMAGE_URL);

    public LoginResponse verifyLogin(LoginRequest request) {
        request.getUsername();
        request.getPassword();

        // TODO: Generates dummy data. Replace with a real implementation.
//        User user = new User("Test", "User",
//                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
//        user.setAlias("@testUser");
//        user.setImageBytes(null);

//        user1.setAlias("@hpurba");
        user1.setAlias(request.getUsername());
        LoginResponse loginResponse = new LoginResponse(user1, null);
        return loginResponse;
    }
}
