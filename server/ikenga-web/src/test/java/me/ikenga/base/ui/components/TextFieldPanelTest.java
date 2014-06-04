package me.ikenga.base.ui.components;

import me.ikenga.base.ui.components.forms.TextFieldPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class TextFieldPanelTest {

    @Test
    public void testRender(){
        WicketTester tester = new WicketTester();
        TextFieldPanel<String> panel = new TextFieldPanel<>("fieldPanel", Model.of("Text"));
        tester.startComponentInPage(panel);
    }

}
