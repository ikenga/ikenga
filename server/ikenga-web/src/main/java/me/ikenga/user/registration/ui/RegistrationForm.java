package me.ikenga.user.registration.ui;

import me.ikenga.IkengaSession;
import me.ikenga.base.ui.components.forms.PasswordFieldPanel;
import me.ikenga.base.ui.components.forms.TextFieldPanel;
import me.ikenga.user.login.LoginCredentials;
import me.ikenga.user.login.LoginData;
import me.ikenga.user.login.LoginService;
import me.ikenga.user.registration.EmailAlreadyExistsException;
import me.ikenga.user.registration.RegistrationData;
import me.ikenga.user.registration.RegistrationService;
import me.ikenga.user.registration.UsernameAlreadyExistsException;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

public class RegistrationForm extends Form<RegistrationData> {

    @SpringBean
    private RegistrationService registrationService;

    @SpringBean
    private LoginService loginService;

    private FormComponent usernameField;

    private FormComponent emailField;

    public RegistrationForm(String id) {
        super(id, Model.of(new RegistrationData()));
        initComponents();
    }

    private void initComponents() {
        RegistrationData bean = getModelObject();

        TextFieldPanel<String> usernamePanel = new TextFieldPanel<>("usernameField", model(from(bean).getUsername()));
        usernamePanel.getField().setRequired(true);
        usernamePanel.getField().add(new AttributeModifier("placeholder", getString("usernameField.placeholder")));
        usernameField = usernamePanel.getField();
        add(usernamePanel);

        TextFieldPanel<String> emailPanel = new TextFieldPanel<>("emailField", model(from(bean).getEmail()));
        emailPanel.getField().setRequired(true);
        emailPanel.getField().add(EmailAddressValidator.getInstance());
        emailPanel.getField().add(new AttributeModifier("placeholder", getString("emailField.placeholder")));
        emailField = emailPanel.getField();
        add(emailPanel);

        PasswordFieldPanel passwordPanel = new PasswordFieldPanel("passwordField", model(from(bean).getPassword()));
        passwordPanel.getField().setRequired(true);
        passwordPanel.getField().add(new AttributeModifier("placeholder", getString("passwordField.placeholder")));
        add(passwordPanel);

        PasswordFieldPanel retypedPasswordPanel = new PasswordFieldPanel("retypedPasswordField", model(from(bean).getPassword()));
        retypedPasswordPanel.getField().setRequired(true);
        retypedPasswordPanel.getField().add(new AttributeModifier("placeholder", getString("retypedPasswordField.placeholder")));
        add(retypedPasswordPanel);

        add(new EqualPasswordInputValidator(retypedPasswordPanel.getField(), passwordPanel.getField()));
    }

    @Override
    protected void onSubmit() {
        try {
            RegistrationData registrationData = getModelObject();
            registrationService.register(registrationData);

            // login user directly after registration
            LoginCredentials credentials = new LoginCredentials();
            credentials.setUsernameOrEmail(registrationData.getUsername());
            credentials.setPassword(registrationData.getPassword());
            LoginData loginData = loginService.login(credentials);
            IkengaSession session = (IkengaSession) IkengaSession.get();
            session.login(loginData);

            setResponsePage(getApplication().getHomePage());
        } catch (UsernameAlreadyExistsException e) {
            usernameField.error(getString("usernameField.alreadyExists"));
        } catch (EmailAlreadyExistsException e) {
            emailField.error(getString("emailField.alreadyExists"));
        }
    }
}
