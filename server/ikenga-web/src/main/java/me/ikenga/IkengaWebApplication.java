package me.ikenga;

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
 * The web application class also serves as spring boot starting point by using
 * spring boot's EnableAutoConfiguration annotation and providing the main
 * method.
 *
 * @author kloe
 */
@Component
@EnableAutoConfiguration
@ComponentScan
public class IkengaWebApplication extends WebApplication {

    private final static Logger logger = LoggerFactory
            .getLogger(IkengaWebApplication.class);

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * spring boot main method to build context
     *
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(
                IkengaWebApplication.class, args);

        DailyMetricsRepository repository = context
                .getBean(DailyMetricsRepository.class);
        Date now = new Date();
        repository.save(new DailyMetric(now, "kloe", "LINES_OF_CODE", 20L));
        repository.save(new DailyMetric(now, "steven", "LINES_OF_CODE", 30L));
        repository.save(new DailyMetric(now, "penta", "LINES_OF_CODE", 10L));
        repository.save(new DailyMetric(now, "kloe", "ADDED_LINES", 20L));
        repository.save(new DailyMetric(now, "steven", "ADDED_LINES", 25L));
        repository.save(new DailyMetric(now, "penta", "ADDED_LINES", 8L));
        repository.save(new DailyMetric(now, "kloe", "REMOVED_LINES", 0L));
        repository.save(new DailyMetric(now, "steven", "REMOVED_LINES", 5L));
        repository.save(new DailyMetric(now, "penta", "REMOVED_LINES", 2L));

    }

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
