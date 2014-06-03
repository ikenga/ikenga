package me.ikenga.user.registration.ui;

import me.ikenga.base.ui.BasePage;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class RegistrationPage extends BasePage {

    public RegistrationPage() {
        initComponents();
        add(new AjaxEventBehavior("onload") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                target.appendJavaScript("$('body').attr('class','bg-black');");
                target.appendJavaScript("$('html').attr('class','bg-black');");
            }
        });
    }

    private void initComponents() {
        add(new RegistrationForm("registrationForm"));
    }

}
