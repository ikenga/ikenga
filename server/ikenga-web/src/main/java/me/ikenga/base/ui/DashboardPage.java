package me.ikenga.base.ui;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class DashboardPage extends BasePage {

    public DashboardPage() {
        add(new BookmarkablePageLink<String>("linkHighscores",
                HighscoresPage.class));
        add(new BookmarkablePageLink<String>("linkLevels", LevelPage.class));
        add(new BookmarkablePageLink<String>("linkToken", TokenPage.class));
        add(new BookmarkablePageLink<String>("linkUsers", UsersPage.class));

    }

}
