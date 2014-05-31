package me.ikenga.web;

import java.util.Date;

import me.ikenga.awarder.DailyMetric;
import me.ikenga.web.base.components.HighscoresPage;
import me.ikenga.web.base.components.UsersPage;
import me.ikenga.awarder.DailyMetricsRepository;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * The web application class also serves as spring configuration starting point by using
 * spring configuration's EnableAutoConfiguration annotation and providing the main
 * method.
 *
 * @author kloe
 */
@Component
public class IkengaWebApplication extends WebApplication {

    private final static Logger logger = LoggerFactory
            .getLogger(IkengaWebApplication.class);

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * provides page for default request
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return HighscoresPage.class;
    }

    /**
     * <ul>
     * <li>making the wicket components injectable by activating the
     * SpringComponentInjector</li>
     * <li>mounting the test page</li>
     * <li>logging spring service method output to showcase working integration</li>
     * </ul>
     */
    @Override
    protected void init() {
        super.init();
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this, applicationContext));
        mountPage("/highscores.html", HighscoresPage.class);
        mountPage("/users.html", UsersPage.class);
    }

}
