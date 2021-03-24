package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.StoryTweetsRequest;
import edu.byu.cs.tweeter.model.service.response.StoryTweetsResponse;
import edu.byu.cs.tweeter.server.dao.StoryTweetsDAO;

public class StoryTweetsServiceImpl {

    public StoryTweetsResponse getStoryTweets(StoryTweetsRequest request) {
        return storyTweetsDAO().getStoryTweets(request);
    }

    StoryTweetsDAO storyTweetsDAO() { return new StoryTweetsDAO(); }
}
