package me.ikenga.user.registration.ui;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class RegistrationPageTest {

    @Test
    public void testRender() {
        WicketTester tester = new WicketTester();
        tester.startPage(RegistrationPage.class);
        tester.assertRenderedPage(RegistrationPage.class);
    }
}
