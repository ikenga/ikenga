/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.base.ui;

import me.ikenga.api.metrics.MetricValue;
import me.ikenga.awarder.MetricRepository;
import me.ikenga.base.ui.panel.MetricHighscorePanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * @author kloe
 */
public class HighscoresPage extends DashboardPage {

    private static final Logger logger = LoggerFactory.getLogger(HighscoresPage.class);

    @SpringBean
    private MetricRepository metricRepository;

    public HighscoresPage(final PageParameters parameters) {

        add(new Label("overallLabel", Model.of("Overall Highscore")));
        add(new MetricHighscorePanel("overallListPanel", metricRepository.findOverallPoints()));
        add(new Label("addLabel", Model.of("Add Highscore")));
        add(new MetricHighscorePanel("addListPanel", "SvnAddCount"));
        add(new Label("remLabel", Model.of("Remove Highscore")));
        add(new MetricHighscorePanel("remListPanel", "SvnDeleteCount"));
        add(new Label("updLabel", Model.of("Update Highscore")));
        add(new MetricHighscorePanel("updListPanel", "SvnModifyCount"));
        add(new Label("replaceLabel", Model.of("Replace Highscore")));
        add(new MetricHighscorePanel("replaceListPanel", "SvnReplaceCount"));

    }

}
