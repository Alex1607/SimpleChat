package me.willperes.SimpleChat.Main;

import me.willperes.SimpleChat.Commands.Basic;
import net.milkbowl.vault.chat.Chat;

import me.willperes.SimpleChat.Commands.GlobalChat;
import me.willperes.SimpleChat.Events.LocalChat;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleChatMain extends JavaPlugin implements Listener {

    private static SimpleChatMain plugin;
    public static Chat chat;

    @Override
    public void onEnable() {
        plugin.saveDefaultConfig();
        plugin = this;

        this.getCommand("g").setExecutor(new GlobalChat());
        this.getCommand("simplechat").setExecutor(new Basic());
        this.getServer().getPluginManager().registerEvents(new LocalChat(), this);

        setupChat();
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        return (rsp.getProvider() != null);
    }

    public static SimpleChatMain getPlugin() {
        return plugin;
    }
}
