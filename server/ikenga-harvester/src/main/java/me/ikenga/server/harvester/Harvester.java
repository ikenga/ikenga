package me.ikenga.server.harvester;

import me.ikenga.server.domain.Project;

public class Harvester {

    /**
     * "Harvests" all metrics from all registered collector plugins for the given Project context and stores them in the
     * database.
     *
     * @param project The Project for which to collect all current metrics.
     */
    public void harvestMetrics(Project project) {
        throw new UnsupportedOperationException("not implemented yet!");
    }

}
