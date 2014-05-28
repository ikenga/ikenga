package me.ikenga.api.metrics;


/**
 * Immutable base class for any metric to be measured with ikenga.
 *
 * @author Tom
 */
public class Metric {

    private String identifier;

    /**
     * Initializing constructor.
     *
     * @param identifier the unique identifier for this metric. Each plugin that defines it's own metrics should prefix
     *                   the metrics with it's own unique namespace.
     */
    public Metric(String identifier) {
        if (identifier == null) {
            throw new NullPointerException("input parameters to this method may not be null!");
        }
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }


    /**
     * A Metric is equal to another Metric if the identifier and all attributes are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Metric that = (Metric) o;

        return this.identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return this.identifier.hashCode();
    }
}
