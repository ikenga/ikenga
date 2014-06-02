package me.ikenga.awarder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by schiller on 02.06.2014.
 */
@Entity
public class RevisionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long revision;
    String host;
    String path;

    public Long getRevision() {
        return revision;
    }

    public void setRevision(Long revision) {
        this.revision = revision;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
