package me.galtap.holyworldrtp.manager.messages;

import me.galtap.holyworldrtp.manager.AbstractMessages;
import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.configuration.ConfigurationSection;

public class StandardRtpMessages extends AbstractMessages {
    private final String delayMessage;
    private final String moveMessage;
    private final String teleportTitleText;
    private final String teleportTitleSubstring;
    public StandardRtpMessages(ConfigurationSection messageFileSection, ConfigurationSection titleFileSection) {
        super(messageFileSection);
        ConfigurationSection section = sectionProcess(messageFileSection,"StandardRtp");
        ConfigurationSection titleSection = sectionProcess(titleFileSection,"StandardRtp");
        delayMessage = TextUtil.getColorText(section.getString("delay","Обнаружена ошибка в messages.yml исправьте ее"));
        moveMessage = TextUtil.getColorText(section.getString("move","Обнаружена ошибка в messages.yml исправьте ее"));
        teleportTitleText = TextUtil.getColorText(titleSection.getString("teleportTitle.text","null"));
        teleportTitleSubstring = TextUtil.getColorText(titleSection.getString("teleportTitle.subtext","null"));
    }

    public String getDelayMessage() {
        return delayMessage;
    }

    public String getMoveMessage() {
        return moveMessage;
    }

    public String getTeleportTitleText() {
        return teleportTitleText;
    }

    public String getTeleportTitleSubstring() {
        return teleportTitleSubstring;
    }
}
