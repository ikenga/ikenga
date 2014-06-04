package me.ikenga.base.ui.components.forms;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.model.IModel;

import java.io.Serializable;

/**
 * A dynamic model that contains the message String of the first FeedbackMessage to a given component.
 */
public class SingleFeedbackMessageAsStringModel implements IModel<Serializable> {


    private SingleFeedbackMessageModel parentModel;

    public SingleFeedbackMessageAsStringModel(SingleFeedbackMessageModel parentModel) {
        this.parentModel = parentModel;
    }

    @Override
    public Serializable getObject() {
        FeedbackMessage message = parentModel.getObject();
        if (message == null) {
            return null;
        } else {
            return parentModel.getObject().getMessage();
        }
    }

    @Override
    public void setObject(Serializable object) {

    }

    @Override
    public void detach() {

    }
}
