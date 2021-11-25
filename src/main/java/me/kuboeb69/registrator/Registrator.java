package me.kuboeb69.registrator;

import me.kuboeb69.registrator.commands.LoginCommand;
import me.kuboeb69.registrator.commands.RegCommand;
import me.kuboeb69.registrator.listeners.PlayerJoinListener;
import me.kuboeb69.registrator.listeners.PlayerMoveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Registrator extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().addDefault("registered-players", 0);
        saveDefaultConfig();

        getServer().getPluginCommand("reg").setExecutor(new RegCommand(this));
        getServer().getPluginCommand("login").setExecutor(new LoginCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
    }

    @Override
    public void onDisable() {

    }
}
