package me.ikenga.user.registration.ui;

import me.ikenga.base.ui.components.forms.PasswordFieldPanel;
import me.ikenga.base.ui.components.forms.TextFieldPanel;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

public class RegistrationForm extends Form<RegistrationFormBean> {

    public RegistrationForm(String id) {
        super(id, Model.of(new RegistrationFormBean()));
        initComponents();
    }

    private void initComponents() {
        RegistrationFormBean bean = getModelObject();

        TextFieldPanel<String> usernamePanel = new TextFieldPanel<>("usernameField", model(from(bean).getUsername()));
        usernamePanel.getField().setRequired(true);
        usernamePanel.getField().add(new AttributeModifier("placeholder", getString("usernameField.placeholder")));
        add(usernamePanel);

        TextFieldPanel<String> emailPanel = new TextFieldPanel<>("emailField", model(from(bean).getEmail()));
        emailPanel.getField().setRequired(true);
        emailPanel.getField().add(EmailAddressValidator.getInstance());
        emailPanel.getField().add(new AttributeModifier("placeholder", getString("emailField.placeholder")));
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
        RegistrationFormBean bean = getModelObject();
    }
}
