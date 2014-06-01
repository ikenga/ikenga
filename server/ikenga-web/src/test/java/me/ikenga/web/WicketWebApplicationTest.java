package me.ikenga.web;

import me.ikenga.IkengaBoot;
import me.ikenga.base.configuration.IkengaWebInitializer;
import me.ikenga.base.ui.HighscoresPage;
import me.ikenga.base.ui.UsersPage;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IkengaWebInitializer.class,
		IkengaBoot.class })
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
