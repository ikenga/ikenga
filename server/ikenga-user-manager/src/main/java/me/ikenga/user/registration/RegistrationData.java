package me.ikenga.user.registration;

import java.io.Serializable;

/**
 * Bean to capture all data needed for a user registration.
 */
public class RegistrationData implements Serializable {

    private String username;

    private String email;

    private String password;

    private String retypedPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypedPassword() {
        return retypedPassword;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }
}
