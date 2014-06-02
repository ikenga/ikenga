package me.ikenga.web;

import me.ikenga.web.base.components.*;

import me.ikenga.web.user.components.LoginPage;
import me.ikenga.web.user.components.RegistrationPage;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
        getMarkupSettings().setStripWicketTags(true);

        getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
        getApplicationSettings().setInternalErrorPage(ErrorPage500.class);
        getApplicationSettings().setAccessDeniedPage(ErrorPage403.class);

        mountPage("/highscores", HighscoresPage.class);
        mountPage("/users", UsersPage.class);
        mountPage("/register", RegistrationPage.class);
        mountPage("/login", LoginPage.class);
        mountPage("/error403", ErrorPage403.class);
        mountPage("/error404", ErrorPage404.class);
        mountPage("/error500", ErrorPage500.class);
        initializeSvnCollector();
    }

    private void initializeSvnCollector() {

        SvnCollector svnCollector = applicationContext.getBean(SvnCollector.class);
        if (svnCollector.init()) {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            final ScheduledFuture<?> handle = executor.scheduleAtFixedRate(svnCollector, 0l, 5l, TimeUnit.MINUTES);
            executor.schedule(new Runnable() {
                public void run() {
                    handle.cancel(false);
                }
            }, 5l, TimeUnit.DAYS);
        }
    }

}
