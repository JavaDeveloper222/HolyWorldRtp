package me.galtap.holyworldrtp.factory;

import me.galtap.holyworldrtp.entity.justRtp.CustomRtp;
import me.galtap.holyworldrtp.entity.justRtp.StandardRtp;
import me.galtap.holyworldrtp.entity.specificAbstractRtp.BaseRtp;
import me.galtap.holyworldrtp.entity.specificAbstractRtp.PlayerRtp;
import me.galtap.holyworldrtp.manager.configs.*;
import me.galtap.holyworldrtp.manager.messages.BaseRtpMessages;
import me.galtap.holyworldrtp.manager.messages.CustomRtpMessages;
import me.galtap.holyworldrtp.manager.messages.PlayerRtpMessages;
import me.galtap.holyworldrtp.manager.messages.StandardRtpMessages;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class RtpFactory {
    private final StandardRtp standardRtp;
    private final CustomRtp customRtp;
    private final PlayerRtp playerRtp;
    private final BaseRtp baseRtp;
    private final List<String> helpList;
    private final String errorArgs;
    public RtpFactory(JavaPlugin plugin, ErrorHandle errorHandle, ConfigurationSection messageFileSection, ConfigurationSection titleFileSection, EffectConfig effectConfig){
        standardRtp = new StandardRtp(new StandardRtpConfig(plugin,errorHandle), new StandardRtpMessages(messageFileSection,titleFileSection),effectConfig.getStandardRtp_effects(),plugin);
        customRtp = new CustomRtp(new CustomRtpConfig(plugin,errorHandle), new CustomRtpMessages(messageFileSection),effectConfig.getCustomRtp_effects());
        playerRtp = new PlayerRtp(new PlayerRtpConfig(plugin,errorHandle), new PlayerRtpMessages(messageFileSection,titleFileSection),effectConfig.getPlayerRtp_effects());
        baseRtp = new BaseRtp(new BaseRtpConfig(plugin,errorHandle),new BaseRtpMessages(messageFileSection,titleFileSection),effectConfig.getBaseRtp_effects());
        helpList = baseRtp.getConfig().getHelpList();
        errorArgs = baseRtp.getMessages().getErrorArgs();
    }

    public StandardRtp getStandardRtp() {
        return standardRtp;
    }

    public CustomRtp getCustomRtp() {
        return customRtp;
    }

    public PlayerRtp getPlayerRtp() {
        return playerRtp;
    }

    public BaseRtp getBaseRtp() {
        return baseRtp;
    }

    public List<String> getHelpList() {
        return helpList;
    }

    public String getErrorArgs() {
        return errorArgs;
    }
}
