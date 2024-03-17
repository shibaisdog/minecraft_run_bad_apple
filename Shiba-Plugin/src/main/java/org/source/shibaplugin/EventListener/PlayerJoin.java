package org.source.shibaplugin.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class PlayerJoin extends JavaPlugin implements Listener {
    private Plugin plugin;
    public PlayerJoin(Plugin pl) {
        plugin = pl;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            int fr = 0;
            while (true) {
                long startTime = System.currentTimeMillis();
                if (Bukkit.getOnlinePlayers().isEmpty()) {
                    return;
                }
                try {
                    URL url = new URL("http://localhost:3000/api/frame/" + (fr + ""));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        String line;
                        player.sendMessage("Response Code: " + responseCode + " [r:"+fr+"]");
                        while ((line = reader.readLine()) != null) {
                            player.sendMessage(line);
                        }
                    }
                    connection.disconnect();
                    fr += 5;
                } catch (MalformedURLException malformedURLException) {
                    //malformedURLException.printStackTrace();
                } catch (IOException ioException) {
                    //ioException.printStackTrace();
                }
                try {
                    long endTime = System.currentTimeMillis();
                    long executionTime = endTime - startTime;
                    Thread.sleep((1000 / 6)-executionTime);
                } catch (InterruptedException exception) {
                    //exception.printStackTrace();
                }
            }
        });
    }
}