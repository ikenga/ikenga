package me.ikenga.awarder;


import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MetricEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long svnRevision;
    private Date date;
    private String userName;
    private String team;
    private String metricName;
    private Long value;
    @Column(length = 2000)
    private String message;

    protected MetricEntity() {
    }

    public MetricEntity(long svnRevision, Date date, String userName,String team, String metricName,
                        Long value, String message) {
        this.svnRevision = svnRevision;
        this.date = date;
        this.userName = userName;
        this.team = team;
        this.metricName = metricName;
        this.value = value;
        //TODO Workaround. längeren messages sollten anders behandelt werden als abschneiden
        this.message = message.length()<1999 ? message : message.substring(0,1999);
    }

    // TODO: check if necessary, may be a good idea to use DTO class
    public MetricEntity(String userName, String metricName,
                        Long value) {
        this.userName = userName;
        this.metricName = metricName;
        this.value = value;
    }

    public long getSvnRevision() {
        return svnRevision;
    }

    public void setSvnRevision(long svnRevision) {
        this.svnRevision = svnRevision;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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
