package me.kuboeb69.registrator.listeners;

import me.kuboeb69.registrator.Registrator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {

    private final Registrator _plugin;

    public PlayerJoinListener(Registrator plugin) {
        _plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = _plugin.getConfig();
        long delay = 600;

        if(!config.contains("registered-players." + player.getName()))
            player.sendMessage(ChatColor.YELLOW + "[Warning] " + ChatColor.GOLD + "Команда для регистрации \"/reg <пароль> <повтор_пароля>\"");
        else {
            player.sendMessage(ChatColor.YELLOW + "[Warning] " + ChatColor.GOLD + "Если вы не регистрировались ранее, значит другой игрок уже регистрировался с таким же никнеймом");
            player.sendMessage(ChatColor.YELLOW + "[Warning] " + ChatColor.GOLD + "Команда для входа \"/login <пароль>\"");

            config.set("registered-players." + player.getName() + ".is-logined", false);
            _plugin.saveConfig();
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (_plugin.getConfig().getBoolean("registered-players." + player.getName() + ".is-logined"))
                    cancel();
                else
                    player.kickPlayer("Не выполнен вход");
            }
        }.runTaskTimer(_plugin, delay, 0);
    }
}
