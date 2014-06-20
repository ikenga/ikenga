/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.base.ui;

import me.ikenga.api.feedback.token.Token;
import me.ikenga.persistence.repository.MetricRepository;
import org.apache.wicket.markup.html.basic.Label;
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

        tokenList.add(metricRepository.findEarliestCommit());
        tokenList.add(metricRepository.findLatestCommit());
        tokenList.add(metricRepository.findBiggestCommit().get(0));
        tokenList.add(metricRepository.findMostCommits().get(0));
        tokenList.add(metricRepository.findLongestAvgMessageLen().get(0));
        tokenList.add(metricRepository.findMostCommitsOnOneDay().get(0));

        add(new ListView<Token>("tokenMetrics", tokenList) {
            @Override
            protected void populateItem(ListItem<Token> item) {
                Token token = item.getModelObject();
                item.add(new Label("tokenname", token.getTokenname()));
                item.add(new Label("user", token.getOwner()));
                item.add(new Label("value", token.getValue()));
                item.add(new Label("description", token.getDescription()));
            }
        });
    }
}
