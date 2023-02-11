package me.allinkdev.onlineplayers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OnlinePlayers extends JavaPlugin implements Listener, CommandExecutor {
    private static final String ONLINE_PLAYERS = "Online players (%d/%d): %s";

    @Override
    public void onDisable() {
        // ...
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        Bukkit.getPluginCommand("list").setExecutor(this);
    }

    private String getOnlinePlayers() {
        final Player[] onlinePlayers = Bukkit.getOnlinePlayers();
        final int maxPlayers = Bukkit.getMaxPlayers();

        return ChatColor.YELLOW + String.format(ONLINE_PLAYERS, onlinePlayers.length, maxPlayers, Arrays.stream(onlinePlayers)
                .map(Player::getName)
                .collect(Collectors.joining(", ")));
    }

    @EventHandler(priority = Event.Priority.Monitor)
    public void onJoin(final PlayerJoinEvent event) {
        Bukkit.broadcastMessage(getOnlinePlayers());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(getOnlinePlayers());

        return true;
    }
}
