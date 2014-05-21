package me.ikenga.server.plugins;

import me.ikenga.api.metrics.MetricValue;
import me.ikenga.api.plugins.CollectorPlugin;

import java.util.List;

public class DummyCollectorPlugin implements CollectorPlugin {

    @Override
    public List<MetricValue> collect() {
        return null;
    }

}
