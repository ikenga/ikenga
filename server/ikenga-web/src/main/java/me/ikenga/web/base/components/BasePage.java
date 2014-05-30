package me.ikenga.web.base.components;

import me.ikenga.IkengaWebApplication;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

public abstract class BasePage extends WebPage {

	public BasePage() {
		add(new BookmarkablePageLink<String>("linkHighscores",
				HighscoresPage.class));
		add(new BookmarkablePageLink<String>("linkUsers", UsersPage.class));
	}

}
