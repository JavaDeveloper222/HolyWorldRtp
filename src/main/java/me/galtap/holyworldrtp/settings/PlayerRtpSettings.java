package me.galtap.holyworldrtp.settings;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class PlayerRtpSettings {
    private final int minOnlineInServer;
    private int minDistance;
    private int maxDistance;
    private final int cooldown;
    private final int tryFoundPlayer;
    private final List<String> worldBlockList;
    public PlayerRtpSettings(ErrorHandle errorHandle){
        ConfigurationSection section = HolyWorldRtp.getInstance().getConfig();
        minOnlineInServer = errorHandle.check(section,1,"RtpPlayer.minOnlineInServer");
        minDistance = errorHandle.check(section,100,"RtpPlayer.minDistance");
        maxDistance = errorHandle.check(section,110,"RtpPlayer.maxDistance");
        cooldown = errorHandle.check(section, 60, "RtpPlayer.cooldown");
        tryFoundPlayer = errorHandle.check(section,25,"RtpPlayer.tryFoundPlayer");
        worldBlockList = section.getStringList("RtpPlayer.worldBlock");
        maxDistance = Math.max(minDistance,maxDistance);
        minDistance = Math.min(minDistance,maxDistance);
    }

    public int getMinOnlineInServer() {
        return minOnlineInServer;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getTryFoundPlayer() {
        return tryFoundPlayer;
    }

    public List<String> getWorldBlockList() {
        return List.copyOf(worldBlockList);
    }
}
