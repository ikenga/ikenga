package me.ikenga.base.ui.components.forms;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;

public class PasswordFieldPanel extends AbstractFieldPanel<String> {

    public PasswordFieldPanel(String id, IModel<String> fieldModel) {
        super(id, fieldModel);
    }

    @Override
    protected FormComponent<String> createFormComponent(String wicketId, IModel<String> model) {
        return new PasswordTextField(wicketId, model);
    }

}
