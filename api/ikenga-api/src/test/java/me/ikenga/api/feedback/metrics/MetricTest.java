package me.ikenga.api.feedback.metrics;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MetricTest {

    @Test
    public void testEquals() {
        Map<String, String> attributes1 = new HashMap<>();
        attributes1.put("fileType", ".txt");
        attributes1.put("color", "red");

        Map<String, String> attributes2 = new HashMap<>();
        attributes2.put("fileType", ".txt");
        attributes2.put("color", "red");

        Metric metric1 = new Metric("me.ikenga.testmetric");
        Metric metric2 = new Metric("me.ikenga.testmetric");

        Assert.assertEquals(metric1, metric2);
    }
}
