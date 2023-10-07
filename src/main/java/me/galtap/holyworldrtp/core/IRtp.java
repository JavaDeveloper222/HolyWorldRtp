package me.galtap.holyworldrtp.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public interface IRtp {
    boolean isHasNotPermission(Player player);
    boolean isHasCooldown(Player player);
    Location getSafeLocation(Player player);
    void teleport(Player player,Location location);
    int getCooldown();
    String getCooldownKay();
    String getPermission();
    void teleportProcess(Player player,Location location, int notDamageTime, List<PotionEffect> effects, String teleportMsg, String coordinateMsg);
}
