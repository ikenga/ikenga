package me.ikenga.web.theme;

import me.ikenga.web.theme.fonts.FontsResourceAnchor;
import org.apache.wicket.Application;
import org.apache.wicket.IInitializer;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Initializer for a Wicket application that wants to use the AdminLTE theme theme.
 */
public class AdminLTEThemeInitializer implements IInitializer {

    @Override
    public void init(Application application) {
        WebApplication webApplication = (WebApplication) application;

        // rather clumsy...is there a better way to do this?
        webApplication.mountResource("/wicket/resource/fonts/FontAwesome.otf", new PackageResourceReference(FontsResourceAnchor.class, "FontAwesome.otf"));
        webApplication.mountResource("/wicket/resource/fonts/fontawesome-webfont.eot", new PackageResourceReference(FontsResourceAnchor.class, "fontawesome-webfont.eot"));
        webApplication.mountResource("/wicket/resource/fonts/fontawesome-webfont.svg", new PackageResourceReference(FontsResourceAnchor.class, "fontawesome-webfont.svg"));
        webApplication.mountResource("/wicket/resource/fonts/fontawesome-webfont.ttf", new PackageResourceReference(FontsResourceAnchor.class, "fontawesome-webfont.ttf"));
        webApplication.mountResource("/wicket/resource/fonts/fontawesome-webfont.woff", new PackageResourceReference(FontsResourceAnchor.class, "fontawesome-webfont.woff"));
        webApplication.mountResource("/wicket/resource/fonts/glyphicons-halflings-regular.woff", new PackageResourceReference(FontsResourceAnchor.class, "glyphicons-halflings-regular.woff"));
        webApplication.mountResource("/wicket/resource/fonts/glyphicons-halflings-regular.svg", new PackageResourceReference(FontsResourceAnchor.class, "glyphicons-halflings-regular.svg"));
        webApplication.mountResource("/wicket/resource/fonts/glyphicons-halflings-regular.ttf", new PackageResourceReference(FontsResourceAnchor.class, "glyphicons-halflings-regular.ttf"));
        webApplication.mountResource("/wicket/resource/fonts/glyphicons-halflings-regular.woff", new PackageResourceReference(FontsResourceAnchor.class, "glyphicons-halflings-regular.woff"));
        webApplication.mountResource("/wicket/resource/fonts/ionicons.eot", new PackageResourceReference(FontsResourceAnchor.class, "ionicons.eot"));
        webApplication.mountResource("/wicket/resource/fonts/ionicons.svg", new PackageResourceReference(FontsResourceAnchor.class, "ionicons.svg"));
        webApplication.mountResource("/wicket/resource/fonts/ionicons.ttf", new PackageResourceReference(FontsResourceAnchor.class, "ionicons.ttf"));
        webApplication.mountResource("/wicket/resource/fonts/ionicons.woff", new PackageResourceReference(FontsResourceAnchor.class, "ionicons.woff"));

        webApplication.mountResource("/fonts/FontAwesome.otf", new PackageResourceReference(FontsResourceAnchor.class, "FontAwesome.otf"));
        webApplication.mountResource("/fonts/fontawesome-webfont.eot", new PackageResourceReference(FontsResourceAnchor.class, "fontawesome-webfont.eot"));
        webApplication.mountResource("/fonts/fontawesome-webfont.svg", new PackageResourceReference(FontsResourceAnchor.class, "fontawesome-webfont.svg"));
        webApplication.mountResource("/fonts/fontawesome-webfont.ttf", new PackageResourceReference(FontsResourceAnchor.class, "fontawesome-webfont.ttf"));
        webApplication.mountResource("/fonts/fontawesome-webfont.woff", new PackageResourceReference(FontsResourceAnchor.class, "fontawesome-webfont.woff"));
        webApplication.mountResource("/fonts/glyphicons-halflings-regular.woff", new PackageResourceReference(FontsResourceAnchor.class, "glyphicons-halflings-regular.woff"));
        webApplication.mountResource("/fonts/glyphicons-halflings-regular.svg", new PackageResourceReference(FontsResourceAnchor.class, "glyphicons-halflings-regular.svg"));
        webApplication.mountResource("/fonts/glyphicons-halflings-regular.ttf", new PackageResourceReference(FontsResourceAnchor.class, "glyphicons-halflings-regular.ttf"));
        webApplication.mountResource("/fonts/glyphicons-halflings-regular.woff", new PackageResourceReference(FontsResourceAnchor.class, "glyphicons-halflings-regular.woff"));
        webApplication.mountResource("/fonts/ionicons.eot", new PackageResourceReference(FontsResourceAnchor.class, "ionicons.eot"));
        webApplication.mountResource("/fonts/ionicons.svg", new PackageResourceReference(FontsResourceAnchor.class, "ionicons.svg"));
        webApplication.mountResource("/fonts/ionicons.ttf", new PackageResourceReference(FontsResourceAnchor.class, "ionicons.ttf"));
        webApplication.mountResource("/fonts/ionicons.woff", new PackageResourceReference(FontsResourceAnchor.class, "ionicons.woff"));
    }

    @Override
    public void destroy(Application application) {

    }
}
