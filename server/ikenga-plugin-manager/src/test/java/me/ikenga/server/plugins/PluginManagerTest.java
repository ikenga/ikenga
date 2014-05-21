package me.ikenga.server.plugins;

import me.ikenga.api.plugins.AchievementPlugin;
import me.ikenga.api.plugins.CollectorPlugin;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PluginManagerTest {

    private PluginManager manager = new PluginManager();

    @Test
    public void testGetCollectorPlugins() {
        List<CollectorPlugin> plugins = manager.getCollectorPlugins();
        Assert.assertEquals(1, plugins.size());
        Assert.assertEquals(DummyCollectorPlugin.class, plugins.get(0).getClass());

        // if we expect identical objects on subsequent calls the following lines should be uncommented
        // List<CollectorPlugin> plugins2 = manager.getCollectorPlugins();
        // Assert.assertSame(plugins.get(0), plugins2.get(0));
    }

    @Test
    public void testGetAchievementPlugins() {
        List<AchievementPlugin> plugins = manager.getAchievementPlugins();
        Assert.assertEquals(1, plugins.size());
        Assert.assertEquals(DummyAchievementPlugin.class, plugins.get(0).getClass());

        // if we expect identical objects on subsequent calls the following lines should be uncommented
        // List<AchievementPlugin> plugins2 = manager.getAchievementPlugins();
        // Assert.assertSame(plugins.get(0), plugins2.get(0));
    }

}
