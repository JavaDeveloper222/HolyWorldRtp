package me.galtap.holyworldrtp.manager;

import me.galtap.holyworldrtp.utility.ErrorHandle;
import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractConfig {
    protected final int maxY;
    protected final int noDamageTime;
    protected int cooldown;
    private final int tryFindLocation;
    protected final Set<Material> blockList = new HashSet<>();
    private final List<String> helpList;

    protected AbstractConfig(JavaPlugin plugin, ErrorHandle errorHandle){
        ConfigurationSection section = plugin.getConfig();
        maxY = errorHandle.check(section,120,"MaxY");
        noDamageTime = errorHandle.check(section,2,"NoDamageTime");
        tryFindLocation = errorHandle.check(section,25,"TryFoundLocation");
        helpList = TextUtil.getColorText(section.getStringList("RtpHelpList"));
        List<String> materialNames = section.getStringList("blockList");
        for(String materialName: materialNames){
            Material material = Material.getMaterial(materialName);
            if(material == null){
                Bukkit.getLogger().info(ChatColor.RED+"[HolyWorldRtp] Такого материала как - "+materialName+" не существует.");
                continue;
            }
            blockList.add(material);
        }
    }
    protected ConfigurationSection sectionProcess(ConfigurationSection mainSection, String name){
        if(mainSection.contains(name)) return mainSection.getConfigurationSection(name);
        return mainSection.createSection(name);
    }

    public int getMaxY() {
        return maxY;
    }

    public int getNoDamageTime() {
        return noDamageTime;
    }
    public Set<Material> getBlockList(){
        return blockList;
    }
    public int getCooldown(){
        return cooldown;
    }

    public int getTryFindLocation() {
        return tryFindLocation;
    }

    public List<String> getHelpList() {
        return helpList;
    }
}
