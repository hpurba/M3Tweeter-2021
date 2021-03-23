package edu.byu.cs.tweeter.model.service;

import java.io.IOException;
import edu.byu.cs.tweeter.model.service.request.StoryTweetsRequest;
import edu.byu.cs.tweeter.model.service.response.StoryTweetsResponse;

public class StoryTweetsService extends BaseService {

    StoryTweetsRequest storyTweetsRequest;
    StoryTweetsResponse storyTweetsResponse;

    public StoryTweetsResponse getStoryTweets(StoryTweetsRequest request) throws IOException {
        this.storyTweetsRequest = request;
        processServiceRequest();
        return storyTweetsResponse;
    }

    @Override
    public void doServiceSpecificTask() {
        storyTweetsResponse = serverFacade.getStoryTweets(storyTweetsRequest);
    }
}