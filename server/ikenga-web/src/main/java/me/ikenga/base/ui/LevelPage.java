/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.base.ui;

import me.ikenga.api.feedback.metrics.MetricValue;
import me.ikenga.persistence.repository.MetricRepository;
import me.ikenga.persistence.entity.UserEntity;
import me.ikenga.persistence.repository.UserRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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

    @SpringBean
    private UserRepository userRepository;

    public LevelPage() {
        Iterable<UserEntity> users = userRepository.findAll();
        List<List<MetricValue>> metricsList = new ArrayList<>();

        for (UserEntity user : users) {
            metricsList.add(metricRepository.findSumValuesByUserName(user));
        }

        add(new ListView<List<MetricValue>>("userBox", metricsList) {

            @Override
            protected void populateItem(ListItem<List<MetricValue>> item) {
                logger.info(item + " -> " + item.getModelObject().getClass());
                List<MetricValue> metric = item.getModelObject();

                item.add(new Label("userName", metric.get(0).getOwner()));

                item.add(new ListView<MetricValue>("userMetrics", metric) {
                    @Override
                    protected void populateItem(ListItem<MetricValue> item) {
                        MetricValue metric = item.getModelObject();
                        item.add(new Label("metricName", metric.getMetric().getIdentifier()));
                        WebMarkupContainer metricProgress = new WebMarkupContainer("metricProgress");
                        metricProgress.add(new AttributeAppender("style", String.format("width: %s%%", metric.getValue().intValue() % 100)));
                        metricProgress.add(new AttributeAppender("class", getColor(metric.getValue() / 100l)));
                        item.add(metricProgress);
                        item.add(new Label("metricLevel", getLeveldescription(metric.getMetric().getIdentifier(), metric.getValue() / 100)));
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
                        default:
                            return leveldescription + " - Add Lord";
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
                        default:
                            return leveldescription + " - Remove Lord";
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
                        default:
                            return leveldescription + " - Modify Lord";
                    }
                }
                return null;
            }

            private String getColor(long l){

                switch ((int) l) {
                    case 0:
                        return " progress-bar-light-blue";
                    case 1:
                        return " progress-bar-aqua";
                    case 2:
                        return " progress-bar-green";
                    case 3:
                        return " progress-bar-yellow";
                    case 4:
                        return " progress-bar-red";
                    default:
                        return " progress-bar-red";
                }
            }
        });

    }

}
