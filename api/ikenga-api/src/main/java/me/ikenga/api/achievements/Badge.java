package me.ikenga.api.achievements;

import me.ikenga.api.metrics.Metric;
import me.ikenga.api.metrics.MetricValue;

import java.util.List;

/**
 * An achievement with a simple fulfillment logic that takes the current and newly added metric values for one or more
 * metrics into consideration.
 */
public abstract class Badge {

    private List<Metric> subscribedMetrics;

    /**
     * Initializing Constructor.
     *
     * @param subscribedMetrics a List of all metrics that are relevant for fulfilling this achievement. These metrics
     *                          will be considered as "subscribed".
     */
    public Badge(List<Metric> subscribedMetrics) {
        this.subscribedMetrics = subscribedMetrics;
    }

    /**
     * Determines whether this achievement has been fulfilled for a certain user. This method is called during the
     * collection of metric values from various sources.
     *
     * @param metricValues the metric values of all metrics this achievement has subscribed for a single user.
     * @return true if the preconditions for this achievements are fulfilled, false if not.
     */
    public abstract boolean isFulfilled(List<MetricValue> metricValues);
}
