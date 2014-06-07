package me.ikenga;

import me.ikenga.authorization.IkengaAuthorizationStrategy;
import me.ikenga.base.ui.*;
import me.ikenga.user.login.ui.LoginPage;
import me.ikenga.user.registration.ui.RegistrationPage;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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

        getSecuritySettings().setAuthorizationStrategy(getAuthorizationStrategy());

        mountPage("/highscores", HighscoresPage.class);
        mountPage("/users", UsersPage.class);
        mountPage("/register", RegistrationPage.class);
        mountPage("/login", LoginPage.class);
        mountPage("/level", LevelPage.class);
        mountPage("/error403", ErrorPage403.class);
        mountPage("/error404", ErrorPage404.class);
        mountPage("/error500", ErrorPage500.class);


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

    public IAuthorizationStrategy getAuthorizationStrategy() {
        return new IkengaAuthorizationStrategy();
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new IkengaSession(request);
    }


}
