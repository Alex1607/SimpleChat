package me.willperes.SimpleChat.Commands;

import me.willperes.SimpleChat.Main.SimpleChatMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Basic implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("simplechat")) {

            if(args.length == 0) {

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&6SimpleChat&f&l] " +
                        "&aCommands available: &f(usage: /simplechat <command>)." +
                        "\n&f - &eReload&f: reload the config.yml file."));
                return true;

            }

            if((args.length == 1) && (args[0].equalsIgnoreCase("reload"))) {

                SimpleChatMain.plugin.reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&f&l[&6SimpleChat&f&l] " +
                        "&aConfig reloaded."));
                return true;

            }
        }

        return false;
    }

}
