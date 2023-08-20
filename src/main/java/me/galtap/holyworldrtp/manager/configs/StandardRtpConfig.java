package me.galtap.holyworldrtp.manager.configs;

import me.galtap.holyworldrtp.manager.AbstractConfig;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class StandardRtpConfig extends AbstractConfig {
    private final int xMin;
    private final int xMax;
    private final int zMin;
    private final int zMax;
    private final World world;
    private final boolean enableCheckMove;
    private final List<String> worldBlockList;
    private final HashMap<String,Integer> groups = new HashMap<>();

    public StandardRtpConfig(JavaPlugin plugin, ErrorHandle errorHandle) {
        super(plugin, errorHandle);
        ConfigurationSection section = sectionProcess(plugin.getConfig(),"StandardRtp");
        ConfigurationSection delaySection = sectionProcess(section,"delay");

        cooldown = errorHandle.check(section,0,"cooldown");
        worldBlockList = section.getStringList("worldBlock");
        xMin = errorHandle.check(section,0,"xMin");
        xMax = errorHandle.check(section,0,"xMax");
        zMin = errorHandle.check(section,0,"zMin");
        zMax = errorHandle.check(section,0,"zMax");
        enableCheckMove = section.getBoolean("enableCheckMove");
        world = errorHandle.check(section,"world");

        for(String group: delaySection.getKeys(false)){
            int time = errorHandle.check(delaySection,0,group);
            groups.put(group,time);

        }

    }

    public HashMap<String, Integer> getGroups() {
        return groups;
    }

    public int getxMin() {
        return xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public int getzMin() {
        return zMin;
    }

    public int getzMax() {
        return zMax;
    }

    public World getWorld() {
        return world;
    }

    public boolean isEnableCheckMove() {
        return enableCheckMove;
    }

    public List<String> getWorldBlockList() {
        return worldBlockList;
    }
}
