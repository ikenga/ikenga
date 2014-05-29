package me.ikenga.web.persistence;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.time.DateUtils;

@Entity
public class DailyMetric {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date day;
	private String userName;
	private String metricName;
	private Long value;

	protected DailyMetric() {
	}

	public DailyMetric(Date date, String userName, String metricName,
			Long value) {
		this.day = DateUtils.truncate(date, Calendar.DATE);
		this.userName = userName;
		this.metricName = metricName;
		this.value = value;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("%s has %d in %s on %s", userName, value,
				metricName, day);
	}

}
