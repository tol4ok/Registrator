package me.kuboeb69.registrator.commands;

import me.kuboeb69.registrator.Registrator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {

    private final Registrator _plugin;

    public LoginCommand(Registrator plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args) {
        FileConfiguration config = _plugin.getConfig();

        if(args.length != 1 || !(sender instanceof Player)) {
            return false;
        }

        if(!config.contains("registered-players." + sender.getName())) {
            sender.sendMessage(ChatColor.RED + "[Error] " + ChatColor.GOLD + "Вы не зарегестрированны");
            return false;
        }

        if(!config.getString("registered-players." + sender.getName() + ".password").equals(args[0])) {
            sender.sendMessage(ChatColor.RED + "[Error] " + ChatColor.GOLD + "Введён неверный пароль");
            return false;
        }

        _plugin.getConfig().set("registered-players." + sender.getName() + ".is-logined", true);
        _plugin.saveConfig();
        sender.sendMessage(ChatColor.GREEN + "[Info] " + ChatColor.GOLD + "Вход успешно выполнен");

        return true;
    }

}
