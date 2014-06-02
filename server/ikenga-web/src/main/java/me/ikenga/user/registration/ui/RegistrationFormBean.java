package me.ikenga.user.registration.ui;

import java.io.Serializable;

/**
 * Bean to capture the data from the registration form.
 */
public class RegistrationFormBean implements Serializable {

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
