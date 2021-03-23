package edu.byu.cs.tweeter.model.service;

import java.io.IOException;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetService extends BaseService {

    TweetResponse tweetResponse;
    TweetRequest tweetRequest;

    public TweetResponse tweet(TweetRequest request) throws IOException {
        this.tweetRequest = request;
        processServiceRequest();
        return tweetResponse;
    }

    @Override
    public void doServiceSpecificTask() {
        tweetResponse = serverFacade.tweet(tweetRequest);
        if(tweetResponse.isSuccess()) {
        }
    }
}