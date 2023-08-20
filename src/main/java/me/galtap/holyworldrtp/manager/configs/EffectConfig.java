package me.galtap.holyworldrtp.manager.configs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class EffectConfig {
    private final List<PotionEffect> standardRtp_effects = new ArrayList<>();
    private final List<PotionEffect> customRtp_effects = new ArrayList<>();
    private final List<PotionEffect> playerRtp_effects = new ArrayList<>();
    private final List<PotionEffect> baseRtp_effects = new ArrayList<>();

    public EffectConfig(ConfigurationSection effectConfigSection){


        ConfigurationSection standardRtp_section = processSection(effectConfigSection,"StandardRtp");
        ConfigurationSection customRtp_section = processSection(effectConfigSection,"CustomRtp");
        ConfigurationSection playerRtp_section = processSection(effectConfigSection,"RtpPlayer");
        ConfigurationSection baseRtp_section = processSection(effectConfigSection,"RtpBase");
        standardRtp_effects.addAll(registerEffects(standardRtp_section));
        customRtp_effects.addAll(registerEffects(customRtp_section));
        playerRtp_effects.addAll(registerEffects(playerRtp_section));
        baseRtp_effects.addAll(registerEffects(baseRtp_section));

    }
    public ConfigurationSection processSection(ConfigurationSection main, String name){
        if(main.contains(name)) return main.getConfigurationSection(name);
        return main.createSection(name);
    }
    public List<PotionEffect> registerEffects(ConfigurationSection section){
        List<PotionEffect> effects = new ArrayList<>();
        for(String effectName: section.getKeys(false)){
            PotionEffectType type = PotionEffectType.getByName(effectName);
            if(type == null){
                Bukkit.getLogger().info(ChatColor.RED+"[HolyWorldRtp] Такого эффекта как - "+effectName+" - не существует");
                continue;
            }
            int level = section.getInt(effectName+".level",-1);
            int time = section.getInt(effectName+".time",-1);
            if(level < 0){
                Bukkit.getLogger().info(ChatColor.RED+"[HolyWorldRtp] Недопустимое значение в пути - "+section.getCurrentPath()+"."+effectName+".level - пропуск");
                continue;
            }
            if(time < 0){
                Bukkit.getLogger().info(ChatColor.RED+"[HolyWorldRtp] Недопустимое значение в пути - "+section.getCurrentPath()+"."+effectName+".time - пропуск");
                continue;
            }
            effects.add(new PotionEffect(type,time*20,level));
        }
        return effects;
    }

    public List<PotionEffect> getStandardRtp_effects() {
        return standardRtp_effects;
    }

    public List<PotionEffect> getCustomRtp_effects() {
        return customRtp_effects;
    }

    public List<PotionEffect> getPlayerRtp_effects() {
        return playerRtp_effects;
    }

    public List<PotionEffect> getBaseRtp_effects() {
        return baseRtp_effects;
    }
}
