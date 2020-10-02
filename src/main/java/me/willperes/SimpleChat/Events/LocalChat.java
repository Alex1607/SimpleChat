package me.willperes.SimpleChat.Events;

import me.willperes.SimpleChat.Main.SimpleChatMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class LocalChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        sendMessage(e.getMessage(), e.getPlayer().getLocation(), e.getPlayer());
        e.setCancelled(true);

    }

    private void sendMessage(String message, Location loc, Player whoSent) {
        if (!(whoSent.hasPermission("simplechat.local.use"))) {
            whoSent.sendMessage(ChatColor.RED + "You don't have permission to do this.");
            return;
        }

        String playerGroupPrefix = getPlayerGroupPrefix(whoSent);

        // Radius of local message (can be edited at the config.yml file)
        int radius = SimpleChatMain.getPlugin().getConfig().getInt("RadiusNumber");

        /*
        Verify if the player that will receive the message is within the sender's radius. If the player that will receive
        the message is within the radius, he will receive the message. In case there is no one within the radius, the
        sender will receive a message that no one is close to hear him
        */
        List<Player> players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());

        if (players.size() == 1) {
            whoSent.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', "&e[L] " + playerGroupPrefix + "&f" + whoSent.getDisplayName() + "&e: " + message)
            );
            whoSent.sendMessage(ChatColor.YELLOW + "There is no one close to hear you...");
            return;
        }

        boolean hasBeenSend = false;

        for (Player player : players) {
            if (player.getLocation().distance(loc) > radius) {
                continue;
            }

            if (player.equals(whoSent)) {
                continue;
            }

            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', "&e[L] " + playerGroupPrefix + "&f" + whoSent.getDisplayName() + "&e: " + message)
            );

            hasBeenSend = true;
        }

        whoSent.sendMessage(
                ChatColor.translateAlternateColorCodes('&', "&e[L] " + playerGroupPrefix + "&f" + whoSent.getDisplayName() + "&e: " + message)
        );

        if (!hasBeenSend) {
            whoSent.sendMessage(ChatColor.YELLOW + "There is no one close to hear you...");
        }

    }

    public static String getPlayerGroupPrefix(Player whoSent) {
        if (SimpleChatMain.chat.getPrimaryGroup(whoSent) != null) {
            String[] allGroups = SimpleChatMain.chat.getGroups();
            for (String group : allGroups) {
                if (!group.equals(SimpleChatMain.chat.getPrimaryGroup(whoSent))) {
                    continue;
                }
                return SimpleChatMain.chat.getGroupPrefix(whoSent.getWorld(), group);
            }
        }
        return null;
    }
}
