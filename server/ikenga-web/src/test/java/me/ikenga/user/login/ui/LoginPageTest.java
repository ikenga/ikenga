package me.ikenga.user.login.ui;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class LoginPageTest {

    @Test
    public void testRender() {
        WicketTester tester = new WicketTester();
        tester.startPage(LoginPage.class);
        tester.assertRenderedPage(LoginPage.class);
    }
}
