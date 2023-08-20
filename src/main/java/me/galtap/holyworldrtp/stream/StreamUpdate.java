package me.galtap.holyworldrtp.stream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class StreamUpdate {
    private StreamUpdate() {
        // private constructor to prevent instantiation
        throw new AssertionError("Utility class StreamUpdate cannot be instantiated.");
    }
    private static int checkMoveTask;
    private static int teleportTask;
    public static void checkMove(Location startLocation, Player player, String message, JavaPlugin plugin){
         checkMoveTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
             Location newLocation = player.getLocation();
             if(newLocation.getBlockX() != startLocation.getBlockX() || newLocation.getBlockZ() != startLocation.getBlockZ() || newLocation.getBlockY() != startLocation.getBlockY()){
                 Bukkit.getScheduler().cancelTask(teleportTask);
                 player.sendMessage(message);
                 Bukkit.getScheduler().cancelTask(checkMoveTask);
             }
         },0,20);
    }
    public static void teleport(int delay, Player player, Location location, int noDamageTime, List<PotionEffect> effects, String teleportMessage, String coordinates, JavaPlugin plugin){
        teleportTask = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Bukkit.getScheduler().cancelTask(checkMoveTask);
            player.teleport(location);
            player.setNoDamageTicks(noDamageTime*20);
            player.addPotionEffects(effects);
            player.sendMessage(teleportMessage);
            player.sendMessage(coordinates);
        },delay* 20L);
    }
}
