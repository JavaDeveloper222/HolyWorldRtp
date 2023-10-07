package me.galtap.holyworldrtp.settings;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.model.CustomRtpData;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import me.galtap.holyworldrtp.utility.SimpleUtil;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CustomRtpSettings {
    private final Map<String,CustomRtpData> customRtpDataMap = new HashMap<>();
    private final ErrorHandle errorHandle;

    public CustomRtpSettings(ErrorHandle errorHandle){
        this.errorHandle = errorHandle;
        ConfigurationSection section = HolyWorldRtp.getInstance().getConfig();
        ConfigurationSection typeSection = SimpleUtil.processSection(section,"CustomRtp.type");
        for(String typeName: typeSection.getKeys(false)){
            CustomRtpData customRtpData = loadCustomRtpData(typeName,section,typeSection);
            customRtpDataMap.put(customRtpData.getType(),customRtpData);
        }
    }
    private CustomRtpData loadCustomRtpData(String rtpType, ConfigurationSection section, ConfigurationSection typeSection){
        int xMin = errorHandle.check(typeSection,0,rtpType+".xMin");
        int xMax = errorHandle.check(typeSection,0,rtpType+".xMax");
        int zMin = errorHandle.check(typeSection,0,rtpType+".zMin");
        int zMax = errorHandle.check(typeSection,0,rtpType+".zMax");
        World world = errorHandle.check(typeSection,rtpType+".world");
        List<String> worldBlockList = section.getStringList("CustomRtp.worldBlock."+rtpType);
        Map<String, Integer> rtpCooldownMap = new HashMap<>();
        ConfigurationSection rtpSection = SimpleUtil.processSection(section,"CustomRtp.cooldown."+rtpType);
        for(String group: rtpSection.getKeys(false)){
            int cooldown = Math.abs(rtpSection.getInt(group));
            rtpCooldownMap.put(group.toLowerCase(Locale.ENGLISH),cooldown);
        }
        return new CustomRtpData(rtpType.toLowerCase(Locale.ENGLISH),xMin,xMax,zMin,zMax,world,worldBlockList,rtpCooldownMap);
    }

    public CustomRtpData getRtpData(String rtpType){
        return customRtpDataMap.get(rtpType.toLowerCase(Locale.ENGLISH));
    }
}
