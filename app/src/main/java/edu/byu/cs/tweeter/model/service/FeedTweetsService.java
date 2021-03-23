package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FeedTweetsRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.FeedTweetsResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class FeedTweetsService extends BaseService{

    FeedTweetsResponse feedTweetsResponse;
    FeedTweetsRequest feedTweetsRequest;

    public FeedTweetsResponse getFeedTweets(FeedTweetsRequest request) throws IOException {
        this.feedTweetsRequest = request;
        processServiceRequest();
        return feedTweetsResponse;
    }

    @Override
    public void doServiceSpecificTask() {
        feedTweetsResponse = getServerFacade().getFeedTweets(feedTweetsRequest);
    }
}
