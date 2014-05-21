package me.ikenga.api.plugins;

/**
 * Base interface for all ikenga plugins.
 */
public interface Plugin {

    /**
     * Returns the unique ID of the plugin. The ID should be named after the naming conventions for java packages.
     * @return the unique ID of the plugin.
     */
    public String getPluginId();

}
