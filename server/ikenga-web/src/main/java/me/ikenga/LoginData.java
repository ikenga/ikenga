package me.ikenga;

import java.io.Serializable;

/**
 * Contains all data about a logged in user that are needed in the UI
 */
public class LoginData implements Serializable {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
