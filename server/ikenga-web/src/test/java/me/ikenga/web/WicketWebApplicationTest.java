package me.ikenga.web;

import me.ikenga.IkengaWebApplication;
import me.ikenga.IkengaWebInitializer;
import me.ikenga.web.base.components.HighscoresPage;
import me.ikenga.web.base.components.UsersPage;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IkengaWebInitializer.class,
		IkengaWebApplication.class })
public class WicketWebApplicationTest {

	@Autowired
	private TestService testService;

	@Test
	public void testHighscores() {
		WicketTester wicketTester = testService.getWicketTester();
		wicketTester.startPage(HighscoresPage.class);
		wicketTester.assertRenderedPage(HighscoresPage.class);
	}

	@Test
	public void testUsers() {
		WicketTester wicketTester = testService.getWicketTester();
		wicketTester.startPage(UsersPage.class);
		wicketTester.assertRenderedPage(UsersPage.class);
	}

}
