package edu.byu.cs.tweeter.model.service.response;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public class LogoutResponse extends Response  {
    public LogoutResponse(boolean success) {
        super(success);
    }

    LogoutResponse(String message) {
        super(false, message);
    }

    public LogoutResponse() {
        super(true, null);
    }
}
