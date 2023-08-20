package me.galtap.holyworldrtp.manager.messages;

import me.galtap.holyworldrtp.manager.AbstractMessages;
import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.configuration.ConfigurationSection;

public class PlayerRtpMessages extends AbstractMessages {
    private final String minPlayerInServer;
    private final String minPlayerInWorld;
    private final String error;
    private final String findTitleText;
    private final String findTitleSubtext;
    private final String warningTitleText;
    private final String warningTitleSubtext;
    public PlayerRtpMessages(ConfigurationSection messageFileSection, ConfigurationSection titleFileSection) {
        super(messageFileSection);
        ConfigurationSection section = sectionProcess(messageFileSection,"RtpPlayer");
        ConfigurationSection titleSection = sectionProcess(titleFileSection,"RtpPlayer");
        minPlayerInServer = TextUtil.getColorText(section.getString("minPlayerInServer","Обнаружена ошибка в messages.yml исправьте ее"));
        minPlayerInWorld = TextUtil.getColorText(section.getString("minPlayerInWorld","Обнаружена ошибка в messages.yml исправьте ее"));
        error = TextUtil.getColorText(section.getString("error","Обнаружена ошибка в messages.yml исправьте ее"));
        findTitleText = TextUtil.getColorText(titleSection.getString("findPlayerTitle.text","null"));
        findTitleSubtext = TextUtil.getColorText(titleSection.getString("findPlayerTitle.subtext","null"));
        warningTitleText = TextUtil.getColorText(titleSection.getString("warningTitle.text","null"));
        warningTitleSubtext = TextUtil.getColorText(titleSection.getString("warningTitle.subtext","null"));
    }

    public String getMinPlayerInServer() {
        return minPlayerInServer;
    }

    public String getMinPlayerInWorld() {
        return minPlayerInWorld;
    }

    public String getError() {
        return error;
    }

    public String getFindTitleText() {
        return findTitleText;
    }

    public String getFindTitleSubtext() {
        return findTitleSubtext;
    }

    public String getWarningTitleText() {
        return warningTitleText;
    }

    public String getWarningTitleSubtext() {
        return warningTitleSubtext;
    }
}
