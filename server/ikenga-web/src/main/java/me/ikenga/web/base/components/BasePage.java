package me.ikenga.web.base.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class BasePage extends WebPage {

	public BasePage() {
		add(new BookmarkablePageLink<String>("linkHighscores",
				HighscoresPage.class));
		add(new BookmarkablePageLink<String>("linkUsers", UsersPage.class));
	}

}
