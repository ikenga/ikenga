package me.ikenga.api.metrics;

/**
 * Basic class to count values for a given Metric.
 *
 * @author Tom
 */
public class MetricValue {

    private Metric metric;

    private String userId;

    private Long value;

    /**
     * Initializing constructor.
     *
     * @param metric the Metric for which to count a value.
     * @param userId identifier of the user for which the metric is counted.
     * @param value  the initial value of the metric.
     */
    public MetricValue(Metric metric, String userId, Long value) {
        if (metric == null || userId == null || value == null) {
            throw new NullPointerException("input parameters may not be null!");
        }
        this.userId = userId;
        this.metric = metric;
        this.value = value;
    }
    
        public MetricValue(String metric, String userId, Long value) {
        if (metric == null || userId == null || value == null) {
            throw new NullPointerException("input parameters may not be null!");
        }
        this.userId = userId;
        this.metric = new Metric(metric);
        this.value = value;
    }

    /**
     * Initializing constructor.
     *
     * @param metric the Metric for which to count a value.
     * @param userId identifier of the user for which the metric is counted.
     */
    public MetricValue(Metric metric, String userId) {
        if (metric == null || userId == null) {
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

    public String getUserId() {
        return userId;
    }
}
