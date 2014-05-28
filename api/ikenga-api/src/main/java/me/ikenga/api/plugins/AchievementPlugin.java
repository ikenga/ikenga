package me.ikenga.api.plugins;

import me.ikenga.api.achievements.Badge;

import java.util.List;

/**
 * Interface for plugins that add achievements to the ikenga server.
 */
public interface AchievementPlugin extends Plugin {

    /**
     * Returns all achievements of type Badge this plugin contributes to ikenga.
     */
    public List<Badge> getBadgeAchievements();


}
