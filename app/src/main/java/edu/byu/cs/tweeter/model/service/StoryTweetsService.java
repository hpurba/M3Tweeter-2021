package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.StoryTweetsRequest;
import edu.byu.cs.tweeter.model.service.response.StoryTweetsResponse;

public class StoryTweetsService extends BaseService implements IStoryTweetsService {
    // The url_path extension for story tweets. (Can be found in AWS console -> API:Tweeter -> Stages -> dev tab)
    private static final String URL_PATH = "/storytweets";

    // StoryTweets Request and Response Objects.
    StoryTweetsRequest storyTweetsRequest;
    StoryTweetsResponse storyTweetsResponse;


    /**
     * Performs a fetch of a user's story, which is a collection of their own tweets.
     *
     * @param request   StoryTweetsRequest Object.
     * @return          StoryTweetsResponse Object.
     * @throws IOException
     */
    public StoryTweetsResponse getStoryTweets(StoryTweetsRequest request) throws IOException {
        this.storyTweetsRequest = request;
        processServiceRequest();
        return storyTweetsResponse;
    }

    /**
     * This is the primary method in the Template pattern of the BaseService Class.
     * This will fetch a user's story (user's own tweets) by calling the getStoryTweets method in the server facade
     * by passing it the provided StoryTweetsRequest (which is first passed into the getStoryTweets method).
     * Returning the StoryTweetsResponse Object is handled in the getStoryTweets() method.
     *
     * @throws IOException
     * @throws TweeterRemoteException
     */
    @Override
    public void doServiceSpecificTask() throws IOException, TweeterRemoteException {
        ServerFacade serverFacade = getServerFacade();
        this.storyTweetsResponse = serverFacade.getStoryTweets(storyTweetsRequest, URL_PATH);
    }
}