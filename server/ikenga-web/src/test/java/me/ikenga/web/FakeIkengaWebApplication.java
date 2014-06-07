package me.ikenga.web;

import me.ikenga.IkengaWebApplication;

/**
 * Subclass of IkengaWebApplication to be used in unit tests.
 */
@org.springframework.stereotype.Component
public class FakeIkengaWebApplication extends IkengaWebApplication {

    @Override
    protected void init() {
        super.init();
        getSecuritySettings().setAuthorizationStrategy(new FakeAuthorizationStrategy());
    }
}
