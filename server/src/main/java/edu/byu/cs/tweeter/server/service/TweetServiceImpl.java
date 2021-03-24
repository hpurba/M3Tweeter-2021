package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.server.dao.TweetDAO;

public class TweetServiceImpl {

    public TweetResponse tweet(TweetRequest request) {
        return tweetDAO().tweet(request);
    }

    TweetDAO tweetDAO() { return new TweetDAO(); }
}
