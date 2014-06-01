package me.ikenga.web.base.components;

import java.util.List;

import me.ikenga.awarder.DailyMetric;
import me.ikenga.awarder.DailyMetricsRepository;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersPage extends DashboardPage{

	private static final Logger logger = LoggerFactory
			.getLogger(UsersPage.class);

	@SpringBean
	private DailyMetricsRepository dailyMetricsRepository;

	public UsersPage() {
		List<DailyMetric> metricsList = dailyMetricsRepository
				.findByUserName("kloe");
		add(new ListView<DailyMetric>("userList", metricsList) {

			@Override
			protected void populateItem(ListItem<DailyMetric> item) {
				DailyMetric dailyMetrics = item.getModelObject();
				logger.info("processing metric data: "
						+ dailyMetrics.toString());
				item.add(new Label("date", dailyMetrics.getDay()));
				item.add(new Label("name", dailyMetrics.getUserName()));
				item.add(new Label("metric", dailyMetrics.getMetricName()));
				item.add(new Label("value", dailyMetrics.getValue()));
			}

		});
	}

}
