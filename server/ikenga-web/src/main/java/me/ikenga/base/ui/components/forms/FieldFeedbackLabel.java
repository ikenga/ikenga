package me.ikenga.base.ui.components.forms;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;

/**
 * A FeedbackLabel showing the first feedback message for a certain form component.
 */
public class FieldFeedbackLabel extends WebMarkupContainer implements IFeedback {

    public FieldFeedbackLabel(String id, FormComponent formComponent, IModel<FeedbackMessage> feedbackModel) {
        super(id, feedbackModel);
        add(new AttributeModifier("for", formComponent.getMarkupId()));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        SingleFeedbackMessageModel model = (SingleFeedbackMessageModel) getDefaultModel();
        Label feedbackMessageLabel = new Label("feedbackMessage", model.getStringModel());
        feedbackMessageLabel.setRenderBodyOnly(true);
        add(feedbackMessageLabel);
    }

    @Override
    public boolean isVisible() {
        SingleFeedbackMessageModel model = (SingleFeedbackMessageModel) getDefaultModel();
        return model.getObject() != null;
    }
}
