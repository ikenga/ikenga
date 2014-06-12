/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.base.ui;

import me.ikenga.api.token.Token;
import me.ikenga.awarder.MetricRepository;
import me.ikenga.base.ui.panel.TokenPanel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kloe
 */
public class TokenPage extends DashboardPage {

    private static final Logger logger = LoggerFactory.getLogger(HighscoresPage.class);

    @SpringBean
    private MetricRepository metricRepository;

    public TokenPage(final PageParameters parameters) {

        List<Token> tokenList = new ArrayList<>();

//        tokenList.add(metricRepository.findBiggestCommit());
        tokenList.add(metricRepository.findEarliestCommit());
        tokenList.add(metricRepository.findLatestCommit());

        add(new ListView<Token>("tokenList", tokenList) {
            // This method is called for each 'entry' in the list.
            @Override
            protected void populateItem(ListItem item) {
                item.add(new TokenPanel("tokenPanel", (Token) item.getModelObject()));
            }
        });
    }
}
