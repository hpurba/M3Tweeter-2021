package edu.byu.cs.tweeter.model.net;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.FeedTweetsRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.request.StoryTweetsRequest;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.FeedTweetsResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.model.service.response.StoryTweetsResponse;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server go through
 * this class.
 */
public class ServerFacade {
    // Server URL: This is the invoke URL of my API on AWS. It can be found by going to your API in AWS, clicking
    // on stages in the right-side menu, and clicking on the stage you deployed your API to.
    private static final String SERVER_URL = "https://yz55y6hm7b.execute-api.us-west-2.amazonaws.com/dev";

    // Instantiate the Client Communicator with the SERVER_URL
    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    /**
     * Performs a Login of a user.
     * If successful, returns the logged in user and an auth token in the LoginResponse object.
     *
     * @param request   LoginRequest Object which contains all information needed to perform a login.
     * @return          LogoutResponse Object.
     */
    public LoginResponse login(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
        LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);
        if(response.isSuccess()) {
            return response;
        } else {
            // TODO: potentially consider changing this to handle somewhere the error 400 and 500 response codes.
            throw new RuntimeException(response.getMessage());
        }
    }

    /**
     * Performs a Logout of the current user.
     * If successful, returns a LogoutResponse with success = true and message = null.
     *
     * @param request   LogoutRequest Object which contains all information needed to perform a logout.
     * @return          LogoutResponse object.
     */
    public LogoutResponse logout(LogoutRequest request, String urlPath) throws IOException, TweeterRemoteException {
        LogoutResponse response = clientCommunicator.doPost(urlPath, request, null, LogoutResponse.class);
        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    /**
     * Performs a retrieval of followees of a user (Can be the current user or some other user).
     * If successful, this method returns a FollowingResponse Object which contains List<User> which are
     * followees of the user (a follower) specified in the request.
     * Also in the FollowingRequest is the limit for the number of followees returned to allow for pagination.
     * - This also means it contains the lastFollowee of the previous request.
     *
     * @param request   FollowingRequest Object which contains all information necessary to get the followees of a user (the follower).
     * @return          FollowingResponse object.
     */
    public FollowingResponse getFollowees(FollowingRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        FollowingResponse response = clientCommunicator.doPost(urlPath, request, null, FollowingResponse.class);
        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }


    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followers returned and to return the next set of
     * followers after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the user whose followers are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public FollowerResponse getFollowers(FollowerRequest request, String urlPath) throws IOException, TweeterRemoteException {
        FollowerResponse response = clientCommunicator.doPost(urlPath, request, null, FollowerResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }

    }


    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followers returned and to return the next set of
     * followers after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the tweet are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public FeedTweetsResponse getFeedTweets(FeedTweetsRequest request, String urlPath) throws IOException, TweeterRemoteException {

        FeedTweetsResponse response = clientCommunicator.doPost(urlPath, request, null, FeedTweetsResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followers returned and to return the next set of
     * followers after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the tweet are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public StoryTweetsResponse getStoryTweets(StoryTweetsRequest request, String urlPath) throws IOException, TweeterRemoteException {

        StoryTweetsResponse response = clientCommunicator.doPost(urlPath, request, null, StoryTweetsResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }


    /**
     * Performs a login and if successful, returns the logged in user and an auth token. The current
     * implementation is hard-coded to return a dummy user and doesn't actually make a network
     * request.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public RegisterResponse register(RegisterRequest request, String urlPath) throws IOException, TweeterRemoteException {
        RegisterResponse response = clientCommunicator.doPost(urlPath, request, null, RegisterResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    // POST A TWEET
    public TweetResponse tweet(TweetRequest request, String urlPath) throws IOException, TweeterRemoteException {
        TweetResponse response = clientCommunicator.doPost(urlPath, request, null, TweetResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public FollowingStatusResponse changeToFollow(FollowingStatusRequest request, String urlPath) throws IOException, TweeterRemoteException {
        FollowingStatusResponse response = clientCommunicator.doPost(urlPath, request, null, FollowingStatusResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public FollowingStatusResponse changeToUnFollow(FollowingStatusRequest request, String urlPath) throws IOException, TweeterRemoteException {
        FollowingStatusResponse response = clientCommunicator.doPost(urlPath, request, null, FollowingStatusResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
}