package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.TweeterRemoteException;

public interface LoginService {
    LoginResponse login(LoginRequest request) throws IOException, TweeterRemoteException;
}
