package me.ikenga.api.feedback.metrics;

/**
 * Basic class to count values for a given Metric.
 *
 * @author Tom
 */
public class MetricValue {

    private Metric metric;

    private String owner;

    private Long value;


    /**
     * Initializing constructor.
     *
     * @param metric the Metric for which to count a value.
     * @param owner  user or group for which the metric is counted.
     * @param value  the initial value of the metric.
     */
    public MetricValue(Metric metric, String owner, Long value) {
        if (metric == null || owner == null || value == null) {
            throw new NullPointerException("input parameters may not be null!");
        }
        this.owner = owner;
        this.metric = metric;
        this.value = value;
    }


    public MetricValue(String metric, String owner, Long value) {
        if (metric == null || owner == null || value == null) {
            throw new NullPointerException("input parameters may not be null!");
        }
        this.owner = owner;
        this.metric = new Metric(metric);
        this.value = value;
    }

    /**
     * Initializing constructor.
     *
     * @param metric the Metric for which to count a value.
     * @param owner  identifier of the user for which the metric is counted.
     */
    public MetricValue(Metric metric, String owner) {
        if (metric == null || owner == null) {
            throw new NullPointerException("input values may not be null!");
        }
        this.metric = metric;
    }

    /**
     * Adds the given amount to the current value of the metric.
     *
     * @param valueToAdd the amount to add to the current value.
     */
    public void add(Long valueToAdd) {
        this.value += valueToAdd;
    }

    public Long getValue() {
        return this.value;
    }

    public Metric getMetric() {
        return this.metric;
    }

    public String getOwner() {
        return owner;
    }
}
