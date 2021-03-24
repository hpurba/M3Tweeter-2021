package edu.byu.cs.tweeter.server.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeToFollowServiceImplTest {

    private FollowingStatusRequest validRequest;
    private FollowingStatusRequest invalidRequest;

    private FollowingStatusResponse validResponse;
    private FollowingStatusResponse invalidResponse;

    private ChangeToFollowServiceImpl implSpy;

    @BeforeEach
    public void setup() throws IOException, TweeterRemoteException {

        String username = "@hpurba";

        User currentUser = new User("Test", "User", null);

        User resultUser1 = new User(null, null,
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        User user = new User("Test", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        String password = "password";

        String AUTH_TOKEN_KEY = "AuthTokenKey";
        AuthToken authToken = new AuthToken();

        validRequest = new FollowingStatusRequest(user, false);
        invalidRequest = new FollowingStatusRequest(null, null);

        validResponse = new FollowingStatusResponse(username, true);
        invalidResponse = new FollowingStatusResponse("An exception occurred");

        implSpy = Mockito.mock(ChangeToFollowServiceImpl.class);
        Mockito.when(implSpy.changeToFollow(validRequest)).thenReturn(validResponse);
        Mockito.when(implSpy.changeToFollow(invalidRequest)).thenReturn(invalidResponse);

    }

    @Test
    public void testChangeToFollow_validRequest_correctResponse(){
        FollowingStatusResponse response = implSpy.changeToFollow(validRequest);
        assertEquals(validResponse, response);
    }

    @Test
    public void testChangeToFollow_invalidRequest_incorrectResponse(){
        FollowingStatusResponse response = implSpy.changeToFollow(invalidRequest);
        assertEquals(invalidResponse, response);
    }

}
