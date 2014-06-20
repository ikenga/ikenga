package me.ikenga.base.ui.panel;

import me.ikenga.api.feedback.metrics.MetricValue;
import me.ikenga.persistence.repository.MetricRepository;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

/**
 * Created by schiller on 06.06.2014.
 */
public class TeamMetricHighscorePanel extends Panel{

    @SpringBean
    private MetricRepository metricRepository;

    public TeamMetricHighscorePanel(String id, List<MetricValue> metrics) {
        super(id);

        add(new ListView<MetricValue>("list", metrics) {
            // This method is called for each 'entry' in the list.
            @Override protected void populateItem(ListItem item) {
                MetricValue values = (MetricValue)item.getModelObject();
                item.add(new Label("team", values.getOwner()));
                item.add(new Label("value", values.getValue()));
            }
        });
    }

    public TeamMetricHighscorePanel(String id, String metric) {
        super(id);

        add(new ListView<MetricValue>("list", metricRepository.findTeamHigscoresByMetric(metric)) {
            // This method is called for each 'entry' in the list.
            @Override protected void populateItem(ListItem item) {
                MetricValue values = (MetricValue)item.getModelObject();
                item.add(new Label("team", values.getOwner()));
                item.add(new Label("value", values.getValue()));
            }
        });
    }

}
