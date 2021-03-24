package edu.byu.cs.tweeter.model.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.byu.cs.tweeter.BuildConfig;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
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
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {

    // This is the hard coded followee data returned by the 'getFollowees()' method
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    private final User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
    private final User user2 = new User("Amy", "Ames", FEMALE_IMAGE_URL);
    private final User user3 = new User("Bob", "Bobson", MALE_IMAGE_URL);
    private final User user4 = new User("Bonnie", "Beatty", FEMALE_IMAGE_URL);
    private final User user5 = new User("Chris", "Colston", MALE_IMAGE_URL);
    private final User user6 = new User("Cindy", "Coats", FEMALE_IMAGE_URL);
    private final User user7 = new User("Dan", "Donaldson", MALE_IMAGE_URL);
    private final User user8 = new User("Dee", "Dempsey", FEMALE_IMAGE_URL);
    private final User user9 = new User("Elliott", "Enderson", MALE_IMAGE_URL);
    private final User user10 = new User("Elizabeth", "Engle", FEMALE_IMAGE_URL);
    private final User user11 = new User("Frank", "Frandson", MALE_IMAGE_URL);
    private final User user12 = new User("Fran", "Franklin", FEMALE_IMAGE_URL);
    private final User user13 = new User("Gary", "Gilbert", MALE_IMAGE_URL);
    private final User user14 = new User("Giovanna", "Giles", FEMALE_IMAGE_URL);
    private final User user15 = new User("Henry", "Henderson", MALE_IMAGE_URL);
    private final User user16 = new User("Helen", "Hopwell", FEMALE_IMAGE_URL);
    private final User user17 = new User("Igor", "Isaacson", MALE_IMAGE_URL);
    private final User user18 = new User("Isabel", "Isaacson", FEMALE_IMAGE_URL);
    private final User user19 = new User("Justin", "Jones", MALE_IMAGE_URL);
    private final User user20 = new User("Jill", "Johnson", FEMALE_IMAGE_URL);

    // Collection of Tweet(s). [tweet1 - tweet20]
    private final Tweet tweet1 = new Tweet("@AllenAnderson", "I am Allen! Nice to meet you all!", "Allen Anderson");
    private final Tweet tweet2 = new Tweet("@AmyAmes", "Yooo I'm Amy.", "Amy Ames");
    private final Tweet tweet3 = new Tweet("@BobBobson", "Welcome to Bob paradise", "Bob Bobson");
    private final Tweet tweet4 = new Tweet("@BonnieBeatty", "I don't have anything to say", "Bonnie Beatty");
    private final Tweet tweet5 = new Tweet("@ChrisColston", "Motorycles are cool", "Chris Colston");
    private final Tweet tweet6 = new Tweet("@CindyCoats", "I love physics", "Cindy Coats");
    private final Tweet tweet7 = new Tweet("@DanDonaldson", "Do I look like a duck to you?", "Dan Donaldson");
    private final Tweet tweet8 = new Tweet("@DeeDempsey", "Doooo doooooooo doodly dooo", "Dee Dempsey");
    private final Tweet tweet9 = new Tweet("@ElliottEnderson", "heyoooooo", "Elliott Enderson");
    private final Tweet tweet10 = new Tweet("@ElizabethEngle", "bah blah", "Elizabeth Engle");
    private final Tweet tweet11 = new Tweet("@FrankFrandson", "frank is dope bro", "Frank Frandson");
    private final Tweet tweet12 = new Tweet("@FranFranklin", "i am rich", "Fran Franklin");
    private final Tweet tweet13 = new Tweet("@GaryGilbert", "i am poor", "Gary Gilbert");
    private final Tweet tweet14 = new Tweet("@GiovannaGiles", "i have a good life", "Giovanna Giles");
    private final Tweet tweet15 = new Tweet("@HenryHenderson", "one republic yo", "Henry Henderson");
    private final Tweet tweet16 = new Tweet("@HelenHopwell", "Have hope for the future", "Helen Hopwell");
    private final Tweet tweet17 = new Tweet("@IgorIsaacson", "You should give this guy 100% because hes a good person", "Igor Isaacson");
    private final Tweet tweet18 = new Tweet("@IsabelIsaacson", "I like to eat pizza", "Isabel Isaacson");
    private final Tweet tweet19 = new Tweet("@JustinJones", "world peace is not possible", "Justin Jones");
    private final Tweet tweet20 = new Tweet("@JillJohnson", "I am an olympian", "JillJohnson");


    /**
     * Returns the list of dummy followee data. This is written as a separate method to allow
     * mocking of the followees.
     *
     * @return the generator.
     */
    List<User> getDummyFollowees() {
        return Arrays.asList(user1, user2, user3, user4, user5, user6, user7,
                user8, user9, user10, user11, user12, user13, user14, user15, user16, user17, user18,
                user19, user20);
    }


    // TODO: Set this to the invoke URL of your API. Find it by going to your API in AWS, clicking
    //  on stages in the right-side menu, and clicking on the stage you deployed your API to.
    private static final String SERVER_URL = "https://yz55y6hm7b.execute-api.us-west-2.amazonaws.com/dev";

    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    /**
     * Performs a login and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse login(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
        LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    /**
     * Performs a logout and if successful
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
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
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the followees.
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
     * Returns the list of dummy follower data. This is written as a separate method to allow
     * mocking of the followers.
     *
     * @return the generator.
     */
    List<User> getDummyFollowers() {
        return Arrays.asList(user1, user2, user3, user4, user5, user6, user7,
                user8, user9, user10, user11, user12, user13, user14, user15, user16, user17, user18,
                user19, user20);
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


    /**
     * Gets the count of users from the database that the user specified is following. The
     * current implementation uses generated data and doesn't actually access a database.
     *
     * @param follower the User whose count of how many following is desired.
     * @return said count.
     */
    public Integer getFolloweeCount(User follower) {
        // TODO: uses the dummy data.  Replace with a real implementation.
        return getDummyFollowees().size();
    }




    /**
     * Determines the index for the first followee in the specified 'allFollowees' list that should
     * be returned in the current request. This will be the index of the next followee after the
     * specified 'lastFollowee'.
     *
     * @param lastFollowee the last followee that was returned in the previous request or null if
     *                     there was no previous request.
     * @param allFollowees the generated list of followees from which we are returning paged results.
     * @return the index of the first followee to be returned.
     */
//    private int getFolloweesStartingIndex(User lastFollowee, List<User> allFollowees) {
//
//        int followeesIndex = 0;
//
//        if(lastFollowee != null) {
//            // This is a paged request for something after the first page. Find the first item
//            // we should return
//            for (int i = 0; i < allFollowees.size(); i++) {
//                if(lastFollowee.equals(allFollowees.get(i))) {
//                    // We found the index of the last item returned last time. Increment to get
//                    // to the first one we should return
//                    followeesIndex = i + 1;
//                    break;
//                }
//            }
//        }
//        return followeesIndex;
//    }

    /**
     * Determines the index for the first follower in the specified 'allFollowers' list that should
     * be returned in the current request. This will be the index of the next follower after the
     * specified 'lastFollower'.
     *
     * @param lastFollower the last follower that was returned in the previous request or null if
     *                     there was no previous request.
     * @param allFollowers the generated list of followers from which we are returning paged results.
     * @return the index of the first follower to be returned.
     */
//    private int getFollowersStartingIndex(User lastFollower, List<User> allFollowers) {
//
//        int followersIndex = 0;
//
//        if(lastFollower != null) {
//            // This is a paged request for something after the first page. Find the first item
//            // we should return
//            for (int i = 0; i < allFollowers.size(); i++) {
//                if(lastFollower.equals(allFollowers.get(i))) {
//                    // We found the index of the last item returned last time. Increment to get
//                    // to the first one we should return
//                    followersIndex = i + 1;
//                    break;
//                }
//            }
//        }
//        return followersIndex;
//    }

}