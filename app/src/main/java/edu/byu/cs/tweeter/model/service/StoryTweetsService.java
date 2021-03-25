package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.StoryTweetsRequest;
import edu.byu.cs.tweeter.model.service.response.StoryTweetsResponse;

public class StoryTweetsService extends BaseService implements IStoryTweetsService {

    StoryTweetsRequest storyTweetsRequest;
    StoryTweetsResponse storyTweetsResponse;

    // The url_path extension for tweets. (Can be found in AWS console -> API:Tweeter -> Stages -> dev tab)
    private static final String URL_PATH = "/storytweets";

    public StoryTweetsResponse getStoryTweets(StoryTweetsRequest request) throws IOException {
        this.storyTweetsRequest = request;
        processServiceRequest();
        return storyTweetsResponse;
    }

    @Override
    public void doServiceSpecificTask() throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        this.storyTweetsResponse = serverFacade.getStoryTweets(storyTweetsRequest, URL_PATH);
    }
}