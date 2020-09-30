package me.willperes.SimpleChat.Main;

import me.willperes.SimpleChat.Commands.Basic;
import net.milkbowl.vault.chat.Chat;

import me.willperes.SimpleChat.Commands.GlobalChat;
import me.willperes.SimpleChat.Events.LocalChat;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SimpleChatMain extends JavaPlugin implements Listener {

    public static SimpleChatMain plugin;
    public static Chat chat = null;

    public void onEnable() {

        createFiles();
        plugin = this;

        this.getCommand("g").setExecutor(new GlobalChat());
        this.getCommand("simplechat").setExecutor(new Basic());
        this.getServer().getPluginManager().registerEvents(new LocalChat(), this);

        setupChat();

    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return (chat != null);
    }

    private File configf;
    private FileConfiguration config;

    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");

        if(!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml",false);
        }
        config = new YamlConfiguration();

        try {
            config.load(configf);

        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
