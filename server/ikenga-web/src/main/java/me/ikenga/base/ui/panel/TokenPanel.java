package me.ikenga.base.ui.panel;

import me.ikenga.api.token.Token;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;


/**
 * Created by schiller on 06.06.2014.
 */
public class TokenPanel extends Panel{

    public TokenPanel(String id, Token token) {
        super(id);
        add(new Label("tokenName", token.getTokenname()));
        add(new Label("owner", token.getOwner()));
        add(new Label("value", token.getValue()));
    }

}
