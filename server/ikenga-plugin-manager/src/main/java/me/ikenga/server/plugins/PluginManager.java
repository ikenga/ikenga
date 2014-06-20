package me.ikenga.server.plugins;

import me.ikenga.api.plugins.AchievementPlugin;
import me.ikenga.api.plugins.CollectorPlugin;

import java.util.*;

/**
 * Central class for accessing all ikenga plugins registered within the JVM.
 */
public class PluginManager {

    /**
     * Returns all CollectorPlugin implementations that are registered with the JVM's ServiceLoader. See the
     * ServiceLoader documentation for a description on how to register a plugin.
     */
    public List<CollectorPlugin> getCollectorPlugins() {
        return getPlugins(CollectorPlugin.class);
    }

    /**
     * Returns all AchievementPlugin implementations that are registered with the JVM's ServiceLoader. See the
     * ServiceLoader documentation for a description on how to register a plugin.
     */
    public List<AchievementPlugin> getAchievementPlugins() {
        return getPlugins(AchievementPlugin.class);
    }

    @SuppressWarnings("unchecked")
    public <P> List<P> getPlugins(Class<P> pluginClass) {
        List<P> resultList = new ArrayList<>();

        for (P p : ServiceLoader.load(pluginClass)) {
            resultList.add(p);
        }

        return resultList;
    }

}
