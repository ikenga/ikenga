package me.ikenga.awarder;

import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity
public class MetricEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date date;
    private String userName;
    private String metricName;
    private Long value;
    private String message;

    protected MetricEntity() {
    }

    public MetricEntity(Date date, String userName, String metricName,
            Long value, String message) {
        this.date = DateUtils.truncate(date, Calendar.DATE);
        this.userName = userName;
        this.metricName = metricName;
        this.value = value;
        this.message = message;
    }

    // TODO: check if necessary, may be a good idea to use DTO class
    public MetricEntity(String userName, String metricName,
            Long value) {
        this.userName = userName;
        this.metricName = metricName;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDay(Date date) {
        this.date = date;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("%s has %d in %s on %s", userName, value,
                metricName, date);
    }

}
