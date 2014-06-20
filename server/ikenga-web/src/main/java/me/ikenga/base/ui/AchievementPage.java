/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.base.ui;

import me.ikenga.api.feedback.metrics.MetricValue;
import me.ikenga.persistence.repository.MetricRepository;
import me.ikenga.persistence.repository.UserRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author kloe
 */
public class AchievementPage extends DashboardPage {

    private static final Logger logger = LoggerFactory.getLogger(HighscoresPage.class);

    @SpringBean
    private MetricRepository metricRepository;

    @SpringBean
    private UserRepository userRepository;

    public AchievementPage() {

        List<String> teams = userRepository.findDistinctTeams();
        List<String> Level1teams = new ArrayList<>();

        for (String team : teams) {
            if(hasMinLevel1Achieved(team)){
                Level1teams.add(team);
            }
        }

        add(new Label("level1Label", Model.of("Level 1 Teams")));
        add(new ListView<String>("level1List", Level1teams) {
            @Override
            protected void populateItem(ListItem<String> item) {
                item.add(new Label("teamname", item.getModelObject()));
            }
        });
    }

    private Boolean hasMinLevel1Achieved(String team) {
        List<String> usersOfTeam = userRepository.findUsernamesByTeam(team);
        Set<String> checklist = new HashSet<>();
        for (MetricValue metric : metricRepository.findSumValuesOfTeam(team)) {
            if (metric.getValue() > 10) {
                checklist.add(metric.getOwner());
            }
        }
        return checklist.containsAll(usersOfTeam);
    }
}
