package org.source.shibaplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.source.shibaplugin.EventListener.PlayerJoin;

public final class Shiba_Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        PlayerJoin run = new PlayerJoin(this);
        getServer().getPluginManager().registerEvents(run, this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
