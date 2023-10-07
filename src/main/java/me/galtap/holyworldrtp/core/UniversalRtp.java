package me.galtap.holyworldrtp.core;

import me.galtap.holyworldrtp.utility.SimpleUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public abstract class UniversalRtp implements IRtp {
    @Override
    public boolean isHasNotPermission(Player player) {
        return !SimpleUtil.isHavePermission(getPermission(), player);
    }

    @Override
    public boolean isHasCooldown(Player player) {
        return SimpleUtil.isHasCooldown(player.getUniqueId()+"."+getCooldownKay(),getCooldown());
    }

    @Override
    public void teleportProcess(Player player,Location location, int notDamageTime, List<PotionEffect> effects, String teleportMsg, String coordinateMsg) {
        SimpleUtil.teleportProcess(player,location,notDamageTime,effects,teleportMsg,coordinateMsg);
    }
}
