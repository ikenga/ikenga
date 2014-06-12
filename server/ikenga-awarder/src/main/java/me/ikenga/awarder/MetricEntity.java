package me.ikenga.awarder;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MetricEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long svnRevision;
    private Date date;
    private String userName;
    private String metricName;
    private Long value;
    private String message;

    protected MetricEntity() {
    }

    public MetricEntity(long svnRevision, Date date, String userName, String metricName,
                        Long value, String message) {
        this.svnRevision = svnRevision;
        this.date = date;
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
