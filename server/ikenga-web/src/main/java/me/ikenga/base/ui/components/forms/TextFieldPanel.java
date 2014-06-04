package me.ikenga.base.ui.components.forms;

import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class TextFieldPanel<T> extends AbstractFieldPanel<T> {

    public TextFieldPanel(String id, IModel<T> fieldModel) {
        super(id, fieldModel);
    }

    @Override
    protected FormComponent<T> createFormComponent(String wicketId, IModel<T> model) {
        return new TextField<>(wicketId, model);
    }

}
