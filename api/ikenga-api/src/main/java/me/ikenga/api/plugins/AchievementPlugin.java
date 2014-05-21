package me.ikenga.api.plugins;

import me.ikenga.api.achievements.SimpleMetricAchievement;

import java.util.List;

/**
 * Interface for plugins that add achievements to the ikenga server.
 */
public interface AchievementPlugin extends Plugin {

    /**
     * Returns all SimpleMetricAchievements this plugin contributes to ikenga.
     */
    public List<SimpleMetricAchievement> getSimpleMetricAchievements();


}
