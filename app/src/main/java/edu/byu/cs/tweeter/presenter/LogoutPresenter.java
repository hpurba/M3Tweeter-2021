package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.LogoutService;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

public class LogoutPresenter {

    private final View view;

    /**
     * This is the interface for the presenter's view (MainActivity).
     * It is the interface by which this presenter can communicate with it's view (call it's methods).
     * Methods listed here should be used only for retrieval, or raising an event (ex: change a button status).
     *  - If needed, specify methods here that will be called on the view in response to model updates.
     *
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
        // String getUserAlias();   // TODO: Do this if you have time. user.getAlias() is currently in mainActivity.
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public LogoutPresenter(View view) {
        this.view = view;
    }

    /**
     * Makes a logout request.
     *
     * @param logoutRequest the request.
     */
    public LogoutResponse logout(LogoutRequest logoutRequest) throws IOException {
        LogoutService logoutService = new LogoutService();
        return logoutService.logout(logoutRequest);
    }

    LogoutService getLogoutService() {
        return new LogoutService();
    }
}
