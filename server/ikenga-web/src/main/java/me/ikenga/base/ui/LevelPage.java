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

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;

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

        add(new ListView<List<MetricValue>>("userBox", metricsList) {

            @Override
            protected void populateItem(ListItem<List<MetricValue>> item) {
                logger.info(item + " -> " + item.getModelObject().getClass());
                List<MetricValue> metric = item.getModelObject();

                item.add(new Label("userName", metric.get(0).getUserId()));

                item.add(new ListView<MetricValue>("userMetrics", metric) {
                    @Override
                    protected void populateItem(ListItem<MetricValue> item) {
                        MetricValue metric = item.getModelObject();
                        final int progress = metric.getValue().intValue();
                        item.add(new Label("metricName", metric.getMetric().getIdentifier()));

                        WebMarkupContainer metricProgress = new WebMarkupContainer("metricProgress");
                        metricProgress.add(new AttributeAppender("style", String.format("width: %s%%", metric.getValue().intValue() % 100)));
                        item.add(metricProgress);
                        item.add(new Label("metricLevel", getLeveldescription(metric.getMetric().getIdentifier(), metric.getValue() / 100l)));
                        item.add(new Label("metricValue", metric.getValue()));
                    }
                });
                
            }
            /**
             * CSC-like solution for Proof of Concept only! needs refactoring!
             */
            private String getLeveldescription(String identifier, long l) {

                String leveldescription = String.valueOf(l);

                if (identifier.equals("SvnAddCount")) {
                    switch ((int) l) {
                        case 0:
                            return leveldescription + " - Add Starter";
                        case 1:
                            return leveldescription + " - better Adder";
                        case 2:
                            return leveldescription + " - advanced Adder";
                        case 3:
                            return leveldescription + " - Addicted";
                        case 4:
                            return leveldescription + " - Addministrator";
                    }
                }
                if (identifier.equals("SvnDeleteCount")) {
                    switch ((int) l) {
                        case 0:
                            return leveldescription + " - Remove Starter";
                        case 1:
                            return leveldescription + " - Eraser";
                        case 2:
                            return leveldescription + " - Eliminator";
                        case 3:
                            return leveldescription + " - Devastator";
                        case 4:
                            return leveldescription + " - badass Killer";
                    }
                }
                if (identifier.equals("SvnModifyCount")) {
                    switch ((int) l) {
                        case 0:
                            return leveldescription + " - shyenne";
                        case 1:
                            return leveldescription + " - wisenhimer";
                        case 2:
                            return leveldescription + " - game changer";
                        case 3:
                            return leveldescription + " - Caterpillar";
                        case 4:
                            return leveldescription + " - Revoluzz0r";
                    }
                }
                return null;
            }
        });

    }

}
