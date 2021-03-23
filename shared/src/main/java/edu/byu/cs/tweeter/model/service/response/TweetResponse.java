package edu.byu.cs.tweeter.model.service.response;

public class TweetResponse extends Response {

    private User user;

    public TweetResponse(String message) {
        super(false, message);
    }


    public TweetResponse(User user) {
        super(true, null);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
