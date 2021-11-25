package me.kuboeb69.registrator.commands;

import me.kuboeb69.registrator.Registrator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RegCommand implements CommandExecutor {

    private final Registrator _plugin;

    public RegCommand(Registrator plugin) {
        _plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String str, String[] args) {
        if(args.length != 2 || !(sender instanceof Player)) {
            return false;
        }
        if(!args[0].equals(args[1])) {
            sender.sendMessage(ChatColor.RED + "[Error] " + ChatColor.GOLD + "Пароли не совпадают");
            return false;
        }
        if(_plugin.getConfig().contains("registered-players." + sender.getName())) {
            sender.sendMessage(ChatColor.RED + "[Error] " + ChatColor.GOLD + "Вы уже зарегестрированны");
            return false;
        }
        if(args[0].length() < 6) {
            sender.sendMessage(ChatColor.RED + "[Error] " + ChatColor.GOLD + "Длина пароля должна быть не менее 6 символов");
            return false;
        }

        _plugin.getConfig().set("registered-players." + sender.getName() + ".password", args[0]);
        _plugin.getConfig().set("registered-players." + sender.getName() + ".is-logined", true);
        _plugin.saveConfig();

        sender.sendMessage(ChatColor.GREEN + "[Info] " + ChatColor.GOLD + "Вы были успешно зарегестрированны(заскамленны)");
        return true;
    }

}
