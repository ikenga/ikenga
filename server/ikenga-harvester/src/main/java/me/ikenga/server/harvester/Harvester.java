package me.ikenga.server.harvester;

import me.ikenga.api.metrics.MetricValue;
import me.ikenga.server.domain.Project;

import java.util.List;

public class Harvester {

    /**
     * "Harvests" all metrics from all registered collector plugins for the given Project context. If a collector plugin
     * throws an exception, it is skipped and processing continues with the other plugins.
     */
    public List<MetricValue> harvestMetrics(Project project) {
        throw new UnsupportedOperationException("not implemented yet!");
    }

}
