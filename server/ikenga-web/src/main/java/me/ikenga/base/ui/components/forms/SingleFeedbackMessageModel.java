package me.ikenga.base.ui.components.forms;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.*;
import org.apache.wicket.model.IModel;

import java.io.Serializable;
import java.util.List;

/**
 * A dynamic model that contains the first FeedbackMessage to a given component.
 */
public class SingleFeedbackMessageModel implements IModel<FeedbackMessage> {

    private Component component;

    public SingleFeedbackMessageModel(Component component) {
        this.component = component;
    }

    @Override
    public FeedbackMessage getObject() {
        IFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(component);
        List<FeedbackMessage> messages = new FeedbackCollector(component).collect(filter);
        if (messages.isEmpty()) {
            return null;
        } else {
            return messages.get(0);
        }
    }

    @Override
    public void setObject(FeedbackMessage object) {

    }

    @Override
    public void detach() {

    }

    public IModel<Serializable> getStringModel() {
        return new SingleFeedbackMessageAsStringModel(this);
    }
}
