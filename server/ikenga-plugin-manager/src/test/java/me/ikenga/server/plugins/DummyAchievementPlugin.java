package me.ikenga.server.plugins;

import me.ikenga.api.feedback.achievements.Badge;
import me.ikenga.api.plugins.AchievementPlugin;

import java.util.List;

public class DummyAchievementPlugin implements AchievementPlugin {

    @Override
    public List<Badge> getBadgeAchievements() {
        return null;
    }

}
