package me.willperes.SimpleChat.Commands;

import me.willperes.SimpleChat.Main.SimpleChatMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Basic implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!label.equalsIgnoreCase("simplechat")) {
            return false;
        }

        if (!sender.hasPermission("simplechat.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to do this.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&6SimpleChat&f&l] " +
                    "&aCommands available: &f(usage: /simplechat <command>)." +
                    "\n&f - &eReload&f: reload the config.yml file."));
            return true;
        }

        if ((args.length == 1) && (args[0].equalsIgnoreCase("reload"))) {
            SimpleChatMain.getPlugin().reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&6SimpleChat&f&l] &aConfig reloaded."));
            return true;
        }

        return false;
    }

}
