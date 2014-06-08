package me.ikenga.user.login.ui;

import me.ikenga.IkengaSession;
import me.ikenga.base.ui.BasePage;
import me.ikenga.base.ui.components.forms.PasswordFieldPanel;
import me.ikenga.base.ui.components.forms.TextFieldPanel;
import me.ikenga.user.login.InvalidLoginCredentialsException;
import me.ikenga.user.login.LoginCredentials;
import me.ikenga.user.login.LoginData;
import me.ikenga.user.login.LoginService;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

public class LoginPage extends BasePage {

    @SpringBean
    private LoginService loginService;

    public LoginPage() {

        add(new LoginForm("loginForm"));

        add(new AjaxEventBehavior("onload") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                target.appendJavaScript("$('body').attr('class','bg-black');");
                target.appendJavaScript("$('html').attr('class','bg-black');");
            }
        });
    }

}
