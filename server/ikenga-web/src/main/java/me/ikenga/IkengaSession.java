package me.ikenga;

import me.ikenga.authorization.Role;
import me.ikenga.user.login.LoginData;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import java.util.EnumSet;
import java.util.Set;

public class IkengaSession extends WebSession {

    private LoginData loginData;

    public IkengaSession(Request request) {
        super(request);
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void login(LoginData loginData) {
        this.loginData = loginData;
    }

    public Set<Role> getRoles() {
        if (isLoggedIn()) {
            return EnumSet.of(Role.USER);
        } else {
            return EnumSet.of(Role.ANONYMOUS);
        }
    }

    public boolean isLoggedIn() {
        return this.loginData != null;
    }
}
