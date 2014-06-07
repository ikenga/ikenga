package me.ikenga.authorization;

import me.ikenga.IkengaSession;
import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This AuthorizationStrategy evaluates the @RolesAllowed annotation on Wicket components.
 */
public class IkengaAuthorizationStrategy implements IAuthorizationStrategy {

    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
        RolesAllowed rolesAnnotation = componentClass.getAnnotation(RolesAllowed.class);
        if (rolesAnnotation == null) {
            return true;
        } else {
            IkengaSession session = (IkengaSession) IkengaSession.get();
            Role[] allowedRoles = rolesAnnotation.value();
            Set<Role> userRoles = session.getRoles();
            userRoles.retainAll(Arrays.asList(allowedRoles));
            return userRoles.size() > 0;
        }
    }

    @Override
    public boolean isActionAuthorized(Component component, Action action) {
        return true;
    }

}
