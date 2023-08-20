package me.galtap.holyworldrtp.manager.configs;

import me.galtap.holyworldrtp.manager.AbstractConfig;
import me.galtap.holyworldrtp.model.CustomRtpContainer;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class CustomRtpConfig extends AbstractConfig {
    private final HashMap<String,CustomRtpContainer> customRtpContainers = new HashMap<>();
    public CustomRtpConfig(JavaPlugin plugin, ErrorHandle errorHandle) {
        super(plugin, errorHandle);
        ConfigurationSection section = sectionProcess(plugin.getConfig(),"CustomRtp");
        ConfigurationSection type_section = sectionProcess(section,"type");
        ConfigurationSection worldBlock_section = sectionProcess(section,"worldBlock");
        ConfigurationSection cooldown_section = sectionProcess(section,"cooldown");


        for(String rtpName: type_section.getKeys(false)){
            int xMin = errorHandle.check(type_section,0,rtpName+".xMin");
            int xMax = errorHandle.check(type_section,0,rtpName+".xMax");
            int zMin = errorHandle.check(type_section,0,rtpName+".zMin");
            int zMax = errorHandle.check(type_section,0,rtpName+".zMax");
            World world = errorHandle.check(type_section,rtpName+".world");
            List<String> worldBlockList = worldBlock_section.getStringList(rtpName);
            ConfigurationSection rtpName_section = sectionProcess(cooldown_section,rtpName);
            HashMap<String,Integer> cooldownMap = new HashMap<>();
            for(String group: rtpName_section.getKeys(false)){
                int cooldownTime = errorHandle.check(rtpName_section,0,group);
                cooldownMap.put(group,cooldownTime);
            }
            CustomRtpContainer customRtpContainer = new CustomRtpContainer(rtpName.toLowerCase(),xMin,xMax,zMin,zMax,world,cooldownMap,worldBlockList);
            customRtpContainers.put(customRtpContainer.getName(),customRtpContainer);
        }
    }

    public HashMap<String,CustomRtpContainer> getCustomRtpList() {
        return customRtpContainers;
    }
}
