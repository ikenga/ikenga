package me.ikenga.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * With this annotation you can define the roles of which at least one is needed to interact with a UI component.
 * This annotation is to be applied to Wicket components. If no annotation is applied, every user (including anonymous
 * users) are allowed to interact with the component.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RolesAllowed {
    public Role[] value();
}

