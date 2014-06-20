package me.ikenga.base.ui;

import java.util.List;

import me.ikenga.persistence.entity.MetricEntity;
import me.ikenga.persistence.entity.UserEntity;
import me.ikenga.persistence.repository.MetricRepository;

import me.ikenga.persistence.repository.UserRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersPage extends DashboardPage {

    private static final Logger logger = LoggerFactory
            .getLogger(UsersPage.class);

    @SpringBean
    private MetricRepository metricRepository;

    @SpringBean
    private UserRepository userRepository;

    public UsersPage() {
        UserEntity user = userRepository.findByUsername("kloe");

        add(new ListView<MetricEntity>("userList", metricRepository.findByUser(user)) {

            @Override
            protected void populateItem(ListItem<MetricEntity> item) {
                MetricEntity data = item.getModelObject();
                logger.info("processing metric data: " + data.toString());
                item.add(new Label("name", data.getUser().getUsername()));
                item.add(new Label("metric", data.getMetricName()));
                item.add(new Label("value", data.getValue()));
            }

        });
    }

}
