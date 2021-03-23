package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

abstract class BaseService
{
    public ServerFacade serverFacade;

    /** Sets the serverFacade by calling getServerFacade
     *
     */
    public final void setServerFacade()
    {
        try
        {
            serverFacade = getServerFacade();
        }
        catch (Exception e)
        {
            System.out.println("Retrieving ServerFacade unsuccessful");
        }
    }

    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the instance.
     */
    public ServerFacade getServerFacade() {
        return new ServerFacade();
    }

    /**
     * Loads the profile image data for the user.
     *
     * @param user the user whose profile image data is to be loaded.
     */
    public void loadImage(User user) throws IOException {
        byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
        user.setImageBytes(bytes);
    }

    public abstract void doServiceSpecificTask() throws IOException;

    /** Template
     * Gets the serverFacade and then does the service specific Task
     */
    public final void processServiceRequest( )
    {
        setServerFacade();
        try {
            doServiceSpecificTask();    // This will take care of the service specific task.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
