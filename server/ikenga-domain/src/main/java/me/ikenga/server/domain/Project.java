package me.ikenga.server.domain;

import java.util.List;

public class Project {

    private Long id;

    private String name;

    private List<String> activePlugins;

    private List<Player> players;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The IDs of the plugins (i.e. the full qualified class names of the Plugin implementations) that are activated for
     * this project.
     */
    public List<String> getActivePlugins() {
        return activePlugins;
    }

    public void setActivePlugins(List<String> activePlugins) {
        this.activePlugins = activePlugins;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
