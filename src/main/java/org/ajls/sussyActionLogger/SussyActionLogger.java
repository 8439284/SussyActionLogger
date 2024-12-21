package org.ajls.sussyActionLogger;

import org.ajls.sussyActionLogger.command.S;
import org.bukkit.plugin.java.JavaPlugin;

public final class SussyActionLogger extends JavaPlugin {
    public static SussyActionLogger getPlugin() {
        return plugin;
    }

    static SussyActionLogger plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new MyListener(), this);
        getCommand("s").setExecutor(new S());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
