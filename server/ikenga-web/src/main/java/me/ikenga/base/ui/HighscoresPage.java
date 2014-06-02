/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.base.ui;

import java.util.List;
import me.ikenga.api.metrics.MetricValue;

import me.ikenga.awarder.MetricEntity;
import me.ikenga.awarder.MetricRepository;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author kloe
 */
public class HighscoresPage extends DashboardPage {

    private static final Logger logger = LoggerFactory.getLogger(HighscoresPage.class);

    @SpringBean
    private MetricRepository metricRepository;

    public HighscoresPage(final PageParameters parameters) {
        List<MetricValue> metricsList = metricRepository.findHighestValues();
        add(new ListView<MetricValue>("userList", metricsList) {

            @Override
            protected void populateItem(ListItem<MetricValue> item) {
                logger.info(item + " -> " + item.getModelObject().getClass());
                MetricValue metric = item.getModelObject();
                item.add(new Label("name", metric.getUserId()));
                item.add(new Label("metric", metric.getMetric().getIdentifier()));
                item.add(new Label("value", metric.getValue()));
            }

        });
    }

}
