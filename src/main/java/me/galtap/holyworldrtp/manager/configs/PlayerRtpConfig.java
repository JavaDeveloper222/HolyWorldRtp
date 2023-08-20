package me.galtap.holyworldrtp.manager.configs;

import me.galtap.holyworldrtp.manager.AbstractConfig;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class PlayerRtpConfig extends AbstractConfig {
    private final int minOnlineInServer;
    private final int minOnlineInWorld;
    private final int minDistance;
    private final int maxDistance;
    private final List<String> worldBlockList;
    public PlayerRtpConfig(JavaPlugin plugin, ErrorHandle errorHandle) {
        super(plugin, errorHandle);
        ConfigurationSection section = sectionProcess(plugin.getConfig(),"RtpPlayer");
        minOnlineInServer = errorHandle.check(section,2,"minOnlineInServer");
        minOnlineInWorld = errorHandle.check(section,2,"minOnlineInWorld");
        maxDistance = errorHandle.check(section,100,"maxDistance");
        minDistance = errorHandle.check(section,1,"minDistance");
        cooldown = errorHandle.check(section,0,"cooldown");
        worldBlockList = section.getStringList("worldBlock");
    }

    public int getMinOnlineInServer() {
        return minOnlineInServer;
    }

    public int getMinOnlineInWorld() {
        return minOnlineInWorld;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public List<String> getWorldBlockList() {
        return worldBlockList;
    }
}
