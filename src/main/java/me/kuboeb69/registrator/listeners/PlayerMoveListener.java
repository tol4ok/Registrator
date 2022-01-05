package me.kuboeb69.registrator.listeners;

import me.kuboeb69.registrator.Registrator;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    private final Registrator _plugin;

    public PlayerMoveListener(Registrator plugin) {
        _plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String player_name = event.getPlayer().getName();

        boolean is_registered = _plugin.getConfig().contains("registered-players." + player_name);
        boolean is_logined = _plugin.getConfig().getBoolean("registered-players." + player_name + ".is-logined");

        if (!is_registered || !is_logined) {
            Location from = event.getFrom();
            Location to = event.getTo();

            event.setTo(new Location(to.getWorld(), from.getX(), to.getY(), from.getZ()));
        }
    }
}
