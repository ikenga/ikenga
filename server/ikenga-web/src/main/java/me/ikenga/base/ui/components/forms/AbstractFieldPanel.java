package me.ikenga.base.ui.components.forms;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * Base class for all input field panels. It contains an input field and a feedback panel for this input field.
 * It takes care of the proper HTML and CSS output to be themed correctly.
 */
public abstract class AbstractFieldPanel<T> extends Panel {

    private FormComponent<T> field;

    private IModel<T> model;

    /**
     * Construct.
     *
     * @param id         the wicket ID of this panel.
     * @param fieldModel the model to be used by the input field that will be constructed as part of this panel.
     */
    public AbstractFieldPanel(String id, IModel<T> fieldModel) {
        super(id);
        this.model = fieldModel;
        this.field = createFormComponent("field", fieldModel);
        setRenderBodyOnly(true);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        WebMarkupContainer fieldGroup = new WebMarkupContainer("fieldGroup");
        add(fieldGroup);
        final IModel<FeedbackMessage> feedbackModel = new SingleFeedbackMessageModel(field);
        fieldGroup.add(field);
        fieldGroup.add(new FieldFeedbackLabel("feedbackLabel", field, feedbackModel));
        fieldGroup.add(new AttributeModifier("class", new IModel<String>() {
            @Override
            public String getObject() {
                FeedbackMessage message = feedbackModel.getObject();
                if (message != null && feedbackModel.getObject().isError()) {
                    return "form-group has-error";
                } else if (field.getInput() != null && !"".equals(field.getInput())) {
                    return "form-group has-success";
                } else {
                    return "form-group";
                }
            }

            @Override
            public void setObject(String object) {
            }

            @Override
            public void detach() {
            }
        }));

    }

    /**
     * Must be implemented by subclasses and return the FormComponent that should be inserted into this panel.
     *
     * @param wicketId the wicket ID that must be used when constructing the FormComponent
     * @param model    the model that must be passed to the created FormComponent
     * @return the FormComponent that will be the central element of this panel
     */
    protected abstract FormComponent<T> createFormComponent(String wicketId, IModel<T> model);

    public FormComponent<T> getField() {
        return field;
    }
}
