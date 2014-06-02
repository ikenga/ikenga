package me.ikenga.user.registration.ui;

import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import static org.wicketstuff.lazymodel.LazyModel.from;
import static org.wicketstuff.lazymodel.LazyModel.model;

public class RegistrationForm extends Form {

    public RegistrationForm(String id) {
        super(id);
        initComponents(new RegistrationFormBean());
    }

    private void initComponents(RegistrationFormBean bean) {
        FeedbackPanel feedback = new FeedbackPanel("feedback");
        add(feedback);

        add(new TextField<>("usernameField", model(from(bean).getUsername()))
                .setRequired(true));
        add(new TextField<>("emailField", model(from(bean).getEmail()))
                .setRequired(true)
                .add(EmailAddressValidator.getInstance()));
        add(new PasswordTextField("passwordField", model(from(bean).getPassword()))
                .setRequired(true));
        add(new PasswordTextField("retypedPasswordField", model(from(bean).getRetypedPassword()))
                .setRequired(true));
    }
}
