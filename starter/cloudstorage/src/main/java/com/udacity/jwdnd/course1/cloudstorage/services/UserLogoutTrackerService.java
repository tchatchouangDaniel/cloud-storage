package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserLogoutTrackerService {
    private boolean userLoggedOut;

    @PostConstruct
    public void postConstruct(){
        this.userLoggedOut = false;
    }

    public boolean hasUserLoggedOut() {
        return userLoggedOut;
    }

    public void setUserLoggedOut(boolean userLoggedOut) {
        this.userLoggedOut = userLoggedOut;
    }
}
