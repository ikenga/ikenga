package me.ikenga.user.login.ui;

import me.ikenga.IkengaSession;
import me.ikenga.base.ui.components.forms.PasswordFieldPanel;
import me.ikenga.base.ui.components.forms.TextFieldPanel;
import me.ikenga.user.login.InvalidLoginCredentialsException;
import me.ikenga.user.login.LoginCredentials;
import me.ikenga.user.login.LoginData;
import me.ikenga.user.login.LoginService;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

public class LoginForm extends Form<LoginCredentials> {

    @SpringBean
    private LoginService loginService;

    private FormComponent usernameField;

    public LoginForm(String id) {
        super(id, Model.of(new LoginCredentials()));
        initComponents();
    }

    private void initComponents() {
        LoginCredentials credentials = getModelObject();

        TextFieldPanel<String> usernamePanel = new TextFieldPanel<>("usernameField", model(from(credentials).getUsernameOrEmail()));
        usernamePanel.getField().add(new AttributeAppender("placeholder", getString("usernameField.placeholder")));
        usernamePanel.getField().setRequired(true);
        usernameField = usernamePanel.getField();
        add(usernamePanel);

        PasswordFieldPanel passwordPanel = new PasswordFieldPanel("passwordField", model(from(credentials).getPassword()));
        passwordPanel.getField().add(new AttributeAppender("placeholder", getString("passwordField.placeholder")));
        passwordPanel.getField().setRequired(true);
        add(passwordPanel);
    }

    @Override
    protected void onSubmit() {
        try {
            LoginCredentials credentials = getModelObject();
            LoginData loginData = loginService.login(credentials);
            IkengaSession session = (IkengaSession) IkengaSession.get();
            session.login(loginData);
            setResponsePage(getApplication().getHomePage());
        } catch (InvalidLoginCredentialsException e) {
            usernameField.error(getString("loginForm.usernameField.fieldGroup.field.invalidCredentials"));
        }
    }
}