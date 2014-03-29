package com.thatonetechserver.mcmmotoollevels;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class mcMMOToolLevels extends JavaPlugin {
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        FileConfiguration configuration = this.getConfig();

        PluginManager pluginManager = this.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerEventHandler(configuration), this);
    }

    public void onDisable() {

    }
}
