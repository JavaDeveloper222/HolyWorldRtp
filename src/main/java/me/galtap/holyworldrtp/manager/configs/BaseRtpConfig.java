package me.galtap.holyworldrtp.manager.configs;

import me.galtap.holyworldrtp.manager.AbstractConfig;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class BaseRtpConfig extends AbstractConfig {
    private final int minDistance;
    private final int maxDistance;
    private final boolean enableBlockCheck;
    private final List<String> worldBlockList;
    private final List<Material> regionBlocks = new ArrayList<>();

    public BaseRtpConfig(JavaPlugin plugin, ErrorHandle errorHandle) {
        super(plugin, errorHandle);
        ConfigurationSection section = sectionProcess(plugin.getConfig(),"RtpBase");
        ConfigurationSection blockCheck_section = sectionProcess(section,"blockCheck");
        minDistance = errorHandle.check(section,1,"minDistance");
        maxDistance = errorHandle.check(section,100,"maxDistance");
        enableBlockCheck = blockCheck_section.getBoolean("enable");
        worldBlockList = section.getStringList("worldBlock");
        List<String> regionBlockNames = blockCheck_section.getStringList("regionBlocks");
        for(String materialName: regionBlockNames){
            Material material = Material.getMaterial(materialName);
            if(material == null){
                Bukkit.getLogger().info(ChatColor.RED+"[HolyWorldRtp] такого материала как - "+materialName+" - не существует");
                continue;
            }
            regionBlocks.add(material);
        }


    }

    public List<Material> getRegionBlocks() {
        return regionBlocks;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public boolean isEnableBlockCheck() {
        return enableBlockCheck;
    }

    public List<String> getWorldBlockList() {
        return worldBlockList;
    }
}
