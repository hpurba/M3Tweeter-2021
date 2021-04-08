package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.FeedTweetsService;
import edu.byu.cs.tweeter.model.service.FollowerService;
import edu.byu.cs.tweeter.model.service.request.FeedTweetsRequest;
import edu.byu.cs.tweeter.model.service.response.FeedTweetsResponse;

public class FeedTweetsPresenter {

    private final View view;

    /**
     * This is the interface for the presenter's view (FeedTweetsFragment).
     * It is the interface by which this presenter can communicate with it's view (call it's methods).
     * Methods listed here should be used only for retrieval, or raising an event (ex: change a button status).
     *  - If needed, specify methods here that will be called on the view in response to model updates.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance of a FeedTweetsPresenter with the provided view (should be FeedTweetsFragment).
     * @param view View, which should be a FeedTweetsFragment view for which this class is the presenter.
     */
    public FeedTweetsPresenter(View view) {
        this.view = view;
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    /**
     * Makes the retrieval of the feed of tweets.
     * @param request   contains the data required to fulfill the request.
     * @return
     * @throws IOException
     */
    public FeedTweetsResponse getFeedTweets(FeedTweetsRequest request) throws IOException {
        FeedTweetsService feedTweetsService = getFeedTweetsService();
        return feedTweetsService.getFeedTweets(request);
    }

    /**
     * Returns an instance of {@link FollowerService}. Allows mocking of the FollowerService class
     * for testing purposes. All usages of FollowerService should get their FollowerService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    FeedTweetsService getFeedTweetsService() {
        return new FeedTweetsService();
    }

}
