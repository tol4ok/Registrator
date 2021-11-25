package me.kuboeb69.registrator.listeners;

import me.kuboeb69.registrator.Registrator;
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
        boolean isRegistered = _plugin.getConfig().contains("registered-players." + event.getPlayer().getName());
        boolean isLogined = _plugin.getConfig().getBoolean("registered-players." + event.getPlayer().getName() + ".is-logined");

        if(!isRegistered || !isLogined) {
            event.setCancelled(true);
        }
    }
}
