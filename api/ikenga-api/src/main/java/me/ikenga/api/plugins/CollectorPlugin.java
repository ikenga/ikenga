package me.ikenga.api.plugins;

import me.ikenga.api.metrics.MetricValue;

import java.util.List;

/**
 * Interface for all plugins that collect one or more metrics for any ikenga users from an arbitrary source.
 */
public interface CollectorPlugin extends Plugin {

    /**
     * This method is expected to return a list of MetricValues for any number of users. Repeated calls to this method
     * may return the same result as before, or the value may be reset after each call, depending on the metrics that are
     * measured.
     * <p/>
     * This method may return MetricValues for users that are not registered at the ikenga server. These will simply be
     * ignored. For users registered at the ikenga server, the userId of the MetricValue must match to the ikenga userId
     * of the same user.
     * <p/>
     * If an exception is thrown in this method, the processing of this plugin will stop, but other plugins will still
     * be called.
     *
     * @return a list of MetricValues for any number of users.
     */
    public List<MetricValue> collect();

}
