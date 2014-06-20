/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.base.ui;

import me.ikenga.persistence.repository.MetricRepository;
import me.ikenga.base.ui.panel.TeamMetricHighscorePanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author kloe
 */
public class TeamPage extends DashboardPage {

    private static final Logger logger = LoggerFactory.getLogger(TeamPage.class);

    @SpringBean
    private MetricRepository metricRepository;

    public TeamPage(final PageParameters parameters) {

        add(new Label("overallLabel", Model.of("Overall Highscore")));
        add(new TeamMetricHighscorePanel("overallListPanel", metricRepository.findOverallTeamPoints()));
        add(new Label("addLabel", Model.of("Add Highscore")));
        add(new TeamMetricHighscorePanel("addListPanel", "SvnAddCount"));
        add(new Label("remLabel", Model.of("Remove Highscore")));
        add(new TeamMetricHighscorePanel("remListPanel", "SvnDeleteCount"));
        add(new Label("updLabel", Model.of("Update Highscore")));
        add(new TeamMetricHighscorePanel("updListPanel", "SvnModifyCount"));
        add(new Label("replaceLabel", Model.of("Replace Highscore")));
        add(new TeamMetricHighscorePanel("replaceListPanel", "SvnReplaceCount"));

    }

}
