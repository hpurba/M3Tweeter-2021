package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.FeedTweetsRequest;
import edu.byu.cs.tweeter.model.service.response.FeedTweetsResponse;

/**
 * FeedTweetsService extends the BaseService Abstract Class to get the Tweets for the Feed.
 */
public class FeedTweetsService extends BaseService{

    // Feed of Tweets Response and Request Objects.
    FeedTweetsResponse feedTweetsResponse;
    FeedTweetsRequest feedTweetsRequest;

    /**
     * This is called to get the Tweets for the users Feed.
     * Takes a FeedTweetsRequest as the parameter and returns a FeedTweetsResponse.
     *
     * @param request A FeedTweetsRequest Object.
     * @return A FeedTweetsResponse Object.
     * @throws IOException
     */
    public FeedTweetsResponse getFeedTweets(FeedTweetsRequest request) throws IOException {
        this.feedTweetsRequest = request;
        processServiceRequest();    // Sets up the ServerFacade and calls the doServiceSpecificTask.
        return feedTweetsResponse;
    }

    /**
     * This is the primary method in the Template pattern of the BaseService Class.
     * This will get the Feed Tweets from the server facade using the provided
     * feedTweetsRequest (which is first passed into getFeedTweets).
     */
    @Override
    public void doServiceSpecificTask() {
        feedTweetsResponse = getServerFacade().getFeedTweets(feedTweetsRequest);
    }
}