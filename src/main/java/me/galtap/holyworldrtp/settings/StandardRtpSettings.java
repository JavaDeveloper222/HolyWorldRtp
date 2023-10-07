package me.galtap.holyworldrtp.settings;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import me.galtap.holyworldrtp.utility.SimpleUtil;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Locale;


public class StandardRtpSettings {
    private final int xMin;
    private final int xMax;
    private final int zMin;
    private final int zMax;
    private final World world;
    private final World spawnWorld;
    private final int cooldown;
    private final boolean isEnableMove;
    private final List<String> worldBlockNames;
    private final Map<String,Integer> delayGroupData = new HashMap<>();


    public StandardRtpSettings(ErrorHandle errorHandle){
        HolyWorldRtp plugin = HolyWorldRtp.getInstance();
        ConfigurationSection section = plugin.getConfig();

        xMin = errorHandle.check(section,0,"StandardRtp.xMin");
        xMax = errorHandle.check(section,0,"StandardRtp.xMax");
        zMin = errorHandle.check(section,0,"StandardRtp.zMin");
        zMax = errorHandle.check(section,0,"StandardRtp.zMax");
        world = errorHandle.check(section,"StandardRtp.world");
        spawnWorld = errorHandle.check(section,"StandardRtp.spawnWorld");
        cooldown = errorHandle.check(section,0,"StandardRtp.cooldown");
        isEnableMove = section.getBoolean("StandardRtp.enableCheckMove");
        worldBlockNames = section.getStringList("StandardRtp.worldBlock");

        ConfigurationSection delaySection = SimpleUtil.processSection(section,"StandardRtp.delay");
        Set<String> groupNames = delaySection.getKeys(false);
        for(String key: groupNames){
            int delay = Math.abs(delaySection.getInt(key));
            delayGroupData.put(key.toLowerCase(Locale.ENGLISH),delay);
        }

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

    public World getSpawnWorld() {
        return spawnWorld;
    }

    public int getCooldown() {
        return cooldown;
    }

    public boolean isEnableMove() {
        return isEnableMove;
    }

    public List<String> getWorldBlockNames() {
        return List.copyOf(worldBlockNames);
    }

    public Map<String, Integer> getDelayGroupData() {
        return Map.copyOf(delayGroupData);
    }
}
