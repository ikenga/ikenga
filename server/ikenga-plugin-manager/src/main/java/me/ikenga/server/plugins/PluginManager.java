package me.ikenga.server.plugins;

import me.ikenga.api.plugins.AchievementPlugin;
import me.ikenga.api.plugins.CollectorPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Central class for accessing all ikenga plugins registered within the JVM.
 */
public class PluginManager {

    /**
     * Returns all CollectorPlugin implementations that are registered with the JVM's ServiceLoader. See the
     * ServiceLoader documentation for a description of how to register a plugin.
     */
    public List<CollectorPlugin> getCollectorPlugins() {
        return getPlugins(CollectorPlugin.class);
    }

    /**
     * Returns all AchievementPlugin implementations that are registered with the JVM's ServiceLoader. See the
     * ServiceLoader documentation for a description of how to register a plugin.
     */
    public List<AchievementPlugin> getAchievementPlugins() {
        return getPlugins(AchievementPlugin.class);
    }

    public <P> List<P> getPlugins(Class<P> pluginClass) {
        ServiceLoader loader = ServiceLoader.load(pluginClass);
        List<P> resultList = new ArrayList<>();
        Iterator<P> iterator = loader.iterator();
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        return resultList;
    }

}
