package me.ikenga.web;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

/**
 * AuthorizationStrategy that allows everything to be used in unit tests.
 */
public class FakeAuthorizationStrategy implements IAuthorizationStrategy {

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
        return true;
    }

    @Override
    public boolean isActionAuthorized(Component component, Action action) {
        return true;
    }
}
