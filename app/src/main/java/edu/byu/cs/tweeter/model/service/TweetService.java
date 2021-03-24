package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetService extends BaseService {

    TweetResponse tweetResponse;
    TweetRequest tweetRequest;

    // The url_path extension for tweet. (Can be found in AWS console -> API:Tweeter -> Stages -> dev tab)
    private static final String URL_PATH = "/tweet";

    public TweetResponse tweet(TweetRequest request) throws IOException {
        this.tweetRequest = request;
        processServiceRequest();
        return tweetResponse;
    }

    @Override
    public void doServiceSpecificTask() throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        this.tweetResponse = serverFacade.tweet(tweetRequest, URL_PATH);
    }
}