package me.ikenga.api.metrics;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Immutable base class for any metric to be measured with ikenga.
 *
 * @author Tom
 */
public class Metric {

    private String identifier;

    private Map<String, String> attributes;

    /**
     * Initializing constructor.
     *
     * @param identifier the unique identifier for this metric. Each plugin that defines it's own metrics should prefix
     *                   the metrics with it's own unique namespace.
     * @param attributes the attributes of this metric. Multiple Metric objects can exist with the same identifier but
     *                   different attributes. Example: a metric counting the number of lines added to a project can
     *                   have the attribute "file type" containing the type of files to which the lines were added. So,
     *                   you can count the number of lines added in .java files and in .html files separately,
     *                   for example.
     */
    public Metric(String identifier, Map<String, String> attributes) {
        if (identifier == null || attributes == null) {
            throw new NullPointerException("input parameters to this method may not be null!");
        }
        this.identifier = identifier;
        this.attributes = attributes;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Map<String, String> getAttributes() {
        return new HashMap<>(this.attributes);
    }

    public String getAttribute(String attributeName) {
        return this.attributes.get(attributeName);
    }


    /**
     * A Metric is equal to another Metric if the identifier and all attributes are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metric metric = (Metric) o;

        if (!CollectionUtils.isEqualCollection(attributes.keySet(), metric.attributes.keySet())) return false;
        if (!CollectionUtils.isEqualCollection(attributes.values(), metric.attributes.values())) return false;
        if (!identifier.equals(metric.identifier)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + attributes.hashCode();
        return result;
    }
}
