package me.galtap.holyworldrtp.settings;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import me.galtap.holyworldrtp.utility.LoggerManager;
import me.galtap.holyworldrtp.utility.SimpleUtil;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class GeneralSettings {
    private final List<String> helpList;
    private final int maxY;
    private final int tryFindLocation;
    private final int noDamageTime;
    private final List<Material> blockList = new ArrayList<>();

    public GeneralSettings(ErrorHandle errorHandle){
        ConfigurationSection section = HolyWorldRtp.getInstance().getConfig();
        helpList = SimpleUtil.getColorText(section.getStringList("RtpHelpList"));
        maxY = errorHandle.check(section,120,"MaxY");
        noDamageTime = errorHandle.check(section,8,"NoDamageTime");
        tryFindLocation = errorHandle.check(section,25,"TryFoundLocation");
        for(String materialName: section.getStringList("blockList")){
            final Material material = Material.getMaterial(materialName);
            if(material == null){
                LoggerManager.MATERIAL_NULL.logError(materialName);
                continue;
            }
            blockList.add(material);
        }
    }

    public List<String> getHelpList() {
        return List.copyOf(helpList);
    }

    public int getMaxY() {
        return maxY;
    }

    public int getTryFindLocation() {
        return tryFindLocation;
    }

    public int getNoDamageTime() {
        return noDamageTime;
    }

    public List<Material> getBlockList() {
        return List.copyOf(blockList);
    }
}
