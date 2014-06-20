package me.ikenga.persistence.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class MetricEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long svnRevision;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    private String metricName;
    private Long value;
    @Column(length = 2000)
    private String message;

    protected MetricEntity() {
    }

    public MetricEntity(long svnRevision, Date date, UserEntity user, String metricName,
                        Long value, String message) {
        this.svnRevision = svnRevision;
        this.date = date;
        this.user = user;
        this.metricName = metricName;
        this.value = value;
        //TODO Workaround. längeren messages sollten anders behandelt werden als abschneiden
        this.message = message.length()<1999 ? message : message.substring(0,1999);
    }

    // TODO: check if necessary, may be a good idea to use DTO class
    public MetricEntity(UserEntity user, String metricName,
                        Long value) {
        this.user = user;
        this.metricName = metricName;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
        return String.format("%s has %d in %s on %s", user, value,
                metricName, date);
    }

}
