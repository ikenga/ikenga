package me.ikenga.server.plugins;

import me.ikenga.api.achievements.SimpleMetricAchievement;
import me.ikenga.api.plugins.AchievementPlugin;

import java.util.List;

public class DummyAchievementPlugin implements AchievementPlugin {

    @Override
    public List<SimpleMetricAchievement> getSimpleMetricAchievements() {
        return null;
    }

}
