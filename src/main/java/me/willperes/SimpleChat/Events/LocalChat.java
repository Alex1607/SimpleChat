package me.willperes.SimpleChat.Events;

import me.willperes.SimpleChat.Main.SimpleChatMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class LocalChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        sendMessage(e.getMessage(),e.getPlayer().getLocation(),e.getPlayer().getDisplayName(),e.getPlayer());
        e.setCancelled(true);

    }

    private void sendMessage(String message, Location loc, String playerName, Player whoSent) {

        if(!(whoSent.hasPermission("simplechat.local.use"))) {
            whoSent.sendMessage(ChatColor.RED + "You don't have permission to do this.");
            return;
        }

        String playerGroupPrefix = null;
        boolean playerHasGroup = false;
        int i;

        if(SimpleChatMain.chat.getPrimaryGroup(whoSent) != null) {

            playerHasGroup = true;
            String[] allGroups = SimpleChatMain.chat.getGroups();

            for(i = 0 ; i < allGroups.length ; i++) {
                if(allGroups[i].equals(SimpleChatMain.chat.getPrimaryGroup(whoSent))) {
                    playerGroupPrefix = SimpleChatMain.chat.getGroupPrefix(whoSent.getWorld(),allGroups[i]);
                }
            }

        }

        // Radius of local message (can be edited at the config.yml file)
        int radius = SimpleChatMain.plugin.getConfig().getInt("RadiusNumber");

        /*
        Verify if the player that will receive the message is within the sender's radius. If the player that will receive
        the message is within the radius, he will receive the message. In case there is no one within the radius, the
        sender will receive a message that no one is close to hear him
         */
        World world = whoSent.getWorld();
        ArrayList<Player> players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
        for(i = 0 ; i < players.size() ; i++) {
            if(players.get(i).getLocation().distanceSquared(loc) < Math.pow(radius,2)) {

                if(players.get(i).equals(whoSent)) {
                    // player equal player
                }else {
                    players.get(i).sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[L] "
                            + playerGroupPrefix + "&f" + whoSent.getDisplayName() + "&e: " + message));
                    whoSent.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[L] "
                            + playerGroupPrefix + "&f" + whoSent.getDisplayName() + "&e: " + message));
                    return;
                }

                // There is no one in the player radius.
            }else{

                if(players.get(i).equals(whoSent)) {
                    // player equal player
                }else {
                    whoSent.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e[L] "
                            + playerGroupPrefix + "&f" + whoSent.getDisplayName() + "&e: " + message));
                    whoSent.sendMessage(ChatColor.YELLOW + "There is no one close to hear you...");
                    return;
                }

            }
        }

    }

}
