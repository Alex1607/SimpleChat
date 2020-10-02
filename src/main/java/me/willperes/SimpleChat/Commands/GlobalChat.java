package me.willperes.SimpleChat.Commands;

import me.willperes.SimpleChat.Events.LocalChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalChat implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            // Console
            sender.sendMessage(ChatColor.RED + "Console cannot send global messages using this command.");
            return true;
        }

        if (!sender.hasPermission("simplechat.global.use")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to do this.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Correct usage: /g <message>");
            return true;
        }

        if (label.equalsIgnoreCase("g")) {
            Player player = (Player) sender;

            // Loop structure to create the message String.
            StringBuilder message = new StringBuilder();
            for (String arg : args) {
                message.append(" ").append(arg);
            }

            String playerGroupPrefix = LocalChat.getPlayerGroupPrefix(player);

            /*
            Check if the player has a group prefix.
            If he doesn't, the plugin will send the standard message without a prefix.
            */
            if (playerGroupPrefix != null) {
                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.sendMessage(
                            ChatColor.translateAlternateColorCodes('&', "&7[G] " + playerGroupPrefix + "&f" + player.getDisplayName() + "&7: " + message)
                    );
                }
                return true;
            }

            // Standard message without prefix
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                onlinePlayers.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', "&7[G] &f" + player.getDisplayName() + "&7: " + message)
                );
            }
            return true;
        }

        return false;
    }
}