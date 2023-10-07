package me.galtap.holyworldrtp.settings;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import me.galtap.holyworldrtp.utility.LoggerManager;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class BaseRtpSettings {
    private final int minDistance;
    private final int maxDistance;
    private final int cooldown;
    private final int tryFoundBase;
    private final boolean isRegionBlocksEnable;
    private final List<Material> regionBlocks = new ArrayList<>();
    private final List<String> worldBlockList;
    public BaseRtpSettings(ErrorHandle errorHandle){
        ConfigurationSection section = HolyWorldRtp.getInstance().getConfig();
        minDistance = errorHandle.check(section,100,"RtpBase.minDistance");
        maxDistance = errorHandle.check(section,110,"RtpBase.minDistance");
        cooldown = errorHandle.check(section,0,"RtpBase.cooldown");
        tryFoundBase = errorHandle.check(section,25,"RtpBase.tryFoundBase");
        isRegionBlocksEnable = section.getBoolean("RtpBase.blockCheck.enable");
        worldBlockList = section.getStringList("RtpBase.worldBlock");
        for(String materialName: section.getStringList("RtpBase.blockCheck.regionBlocks")){
            Material material = Material.getMaterial(materialName);
            if(material == null){
                LoggerManager.MATERIAL_NULL.logError(materialName);
                continue;
            }
            regionBlocks.add(material);
        }

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

    public int getTryFoundBase() {
        return tryFoundBase;
    }

    public boolean isRegionBlocksEnable() {
        return isRegionBlocksEnable;
    }

    public List<Material> getRegionBlocks() {
        return List.copyOf(regionBlocks);
    }

    public List<String> getWorldBlockList() {
        return List.copyOf(worldBlockList);
    }
}
