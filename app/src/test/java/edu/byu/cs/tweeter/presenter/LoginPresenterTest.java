package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.ILoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

class LoginPresenterTest {


    private LoginRequest request;
    private LoginRequest badRequest;
    private LoginResponse response;
    private LoginResponse badResponse;
    private ILoginService mockLoginService;
    private LoginPresenter presenter;

    @BeforeEach
    public void setup() throws IOException {

        String username = "@hpurba";
        String password = "password";

        User user = new User("Test", "User",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");


        AuthToken authToken = new AuthToken();

        request = new LoginRequest(username, password);
        badRequest = new LoginRequest(null, null);
        response = new LoginResponse(user, authToken);
        badResponse = new LoginResponse((User) null, (AuthToken) null);

        // Create a mock FollowingService
        mockLoginService = Mockito.mock(ILoginService.class);
        Mockito.when(mockLoginService.login(request)).thenReturn(response);

        // Wrap a FollowingPresenter in a spy that will use the mock service.
        presenter = Mockito.spy(new LoginPresenter(new LoginPresenter.View() {}));
        Mockito.when(presenter.getLoginService()).thenReturn(mockLoginService);
    }

    @Test
    public void testGetLogin_returnsServiceResult() throws IOException {
        Mockito.when(mockLoginService.login(request)).thenReturn(response);

        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        Assertions.assertEquals(response.getUser().getFirstName(), presenter.login(request).getUser().getFirstName());
    }

    @Test
    public void testGetLogin_serviceThrowsIOException_presenterThrowsIOException() throws IOException {
        Mockito.when(mockLoginService.login(badRequest)).thenReturn(response);
        LoginPresenter testPresenter = new LoginPresenter(new LoginPresenter.View() {});
        Assertions.assertEquals(response.isSuccess(), presenter.login(request).isSuccess());
        Assertions.assertThrows(IOException.class, () -> {
            testPresenter.login(badRequest);
        });
    }
}