package me.galtap.holyworldrtp.settings;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.utility.DefaultConfig;
import me.galtap.holyworldrtp.utility.LoggerManager;
import me.galtap.holyworldrtp.utility.SimpleUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EffectsSettings {
    private final List<PotionEffect> standardRtpEffects = new ArrayList<>();
    private final List<PotionEffect> customRtpEffects = new ArrayList<>();
    private final List<PotionEffect> playerRtpEffects = new ArrayList<>();
    private final List<PotionEffect> baseRtpEffects = new ArrayList<>();
    public EffectsSettings(){
        DefaultConfig effectConfig = new DefaultConfig(HolyWorldRtp.getInstance(),"effects.yml");
        effectConfig.saveConfig();

        ConfigurationSection section = effectConfig.getConfig();
        ConfigurationSection standardRtpSection = SimpleUtil.processSection(section,"StandardRtp");
        ConfigurationSection customRtpSection = SimpleUtil.processSection(section,"CustomRtp");
        ConfigurationSection playerRtpSection = SimpleUtil.processSection(section,"RtpPlayer");
        ConfigurationSection baseRtpSection = SimpleUtil.processSection(section,"RtpBase");

        standardRtpEffects.addAll(loadEffectFromSection(standardRtpSection));
        customRtpEffects.addAll(loadEffectFromSection(customRtpSection));
        playerRtpEffects.addAll(loadEffectFromSection(playerRtpSection));
        baseRtpEffects.addAll(loadEffectFromSection(baseRtpSection));

    }
    private static List<PotionEffect> loadEffectFromSection(ConfigurationSection section){
        final List<PotionEffect> effects = new ArrayList<>();
        for(String effectTypeName: section.getKeys(false)){
            int level = Math.abs(section.getInt(effectTypeName+".level"));
            int time = Math.abs(section.getInt(effectTypeName+".time"));
            PotionEffectType type = PotionEffectType.getByName(effectTypeName.toUpperCase(Locale.ENGLISH));
            if(type == null){
                LoggerManager.EFFECT_NULL.logError(effectTypeName);
                continue;
            }
            effects.add(new PotionEffect(type,time*20,level));
        }
        return effects;
    }

    public List<PotionEffect> getStandardRtpEffects() {
        return List.copyOf(standardRtpEffects);
    }

    public List<PotionEffect> getCustomRtpEffects() {
        return List.copyOf(customRtpEffects);
    }

    public List<PotionEffect> getPlayerRtpEffects() {
        return List.copyOf(playerRtpEffects);
    }

    public List<PotionEffect> getBaseRtpEffects() {
        return List.copyOf(baseRtpEffects);
    }
}
