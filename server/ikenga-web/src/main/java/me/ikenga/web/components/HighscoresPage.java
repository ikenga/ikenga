/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.ikenga.web.components;

import java.util.List;

import me.ikenga.awarder.DailyMetric;
import me.ikenga.awarder.DailyMetricsRepository;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * 
 * @author kloe
 */
public class HighscoresPage extends BasePage {

	@SpringBean
	private DailyMetricsRepository dailyMetricsRepository;

	public HighscoresPage(final PageParameters parameters) {
		List<DailyMetric> metricsList = dailyMetricsRepository
				.findHighestValues();
		add(new ListView<DailyMetric>("userList", metricsList) {

			@Override
			protected void populateItem(ListItem<DailyMetric> item) {
				DailyMetric dailyMetrics = item.getModelObject();
				item.add(new Label("date", dailyMetrics.getDay()));
				item.add(new Label("name", dailyMetrics.getUserName()));
				item.add(new Label("metric", dailyMetrics.getMetricName()));
				item.add(new Label("value", dailyMetrics.getValue()));
			}

		});
	}

}
