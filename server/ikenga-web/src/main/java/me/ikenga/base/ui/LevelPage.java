/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.base.ui;

import me.ikenga.api.metrics.MetricValue;
import me.ikenga.awarder.MetricRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.progressbar.ProgressBar;
import org.wicketstuff.progressbar.Progression;
import org.wicketstuff.progressbar.ProgressionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kloe
 */
public class LevelPage extends DashboardPage {

    private static final Logger logger = LoggerFactory.getLogger(LevelPage.class);

    @SpringBean
    private MetricRepository metricRepository;

    public LevelPage(final PageParameters parameters) {
        List<String> nameList = metricRepository.findUserNames();
        List<List<MetricValue>> metricsList = new ArrayList<>();

        for (String name : nameList) {
            metricsList.add(metricRepository.findHighestValuesByUserName(name));
        }


        add(new ListView<List<MetricValue>>("usersList", metricsList) {

            @Override
            protected void populateItem(ListItem<List<MetricValue>> item) {
                logger.info(item + " -> " + item.getModelObject().getClass());
                List<MetricValue> metric = item.getModelObject();

                item.add(new Label("userLabel", metric.get(0).getUserId()));

                item.add(new ListView<MetricValue>("userList", metric) {
                             @Override
                             protected void populateItem(ListItem<MetricValue> item) {
                                 MetricValue metric = item.getModelObject();
                                 final int progress = metric.getValue().intValue();
                                 item.add(new Label("metric", metric.getMetric().getIdentifier()));

                                 ProgressBar bar = new ProgressBar("bar", new ProgressionModel() {
                                     protected Progression getProgression() {
                                         return new Progression(progress % 100);
                                     }
                                 });
                                 item.add(bar);
                                 bar.getMarkupAttributes();
                                 item.add(new Label("level", metric.getValue() / 100l));
                                 item.add(new Label("value", metric.getValue()));
                             }
                         }
                );
            }
        });

    }

}
