package com.automationexercise.context;

import com.automationexercise.models.User;

/** Shared state between step classes (injected by PicoContainer, one instance per scenario). */
public class ScenarioContext {

    private User user;
    private boolean apiUserCreated;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isApiUserCreated() {
        return apiUserCreated;
    }

    public void setApiUserCreated(boolean apiUserCreated) {
        this.apiUserCreated = apiUserCreated;
    }
}