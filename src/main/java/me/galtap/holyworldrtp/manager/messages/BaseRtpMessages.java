package me.galtap.holyworldrtp.manager.messages;

import me.galtap.holyworldrtp.manager.AbstractMessages;
import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.configuration.ConfigurationSection;

public class BaseRtpMessages extends AbstractMessages {
    private final String notFound;
    private final String findBaseTitleText;
    private final String findBaseTitleSubtext;
    public BaseRtpMessages(ConfigurationSection messageFileSection, ConfigurationSection titleFileSection) {
        super(messageFileSection);
        notFound = TextUtil.getColorText(messageFileSection.getString("RtpBase.notFound","Обнаружена ошибка в messages.yml исправьте ее"));
        findBaseTitleText = TextUtil.getColorText(titleFileSection.getString("RtpBase.findBaseTitle.text","null"));
        findBaseTitleSubtext = TextUtil.getColorText(titleFileSection.getString("RtpBase.findBaseTitle.subtext","null"));
    }

    public String getNotFound() {
        return notFound;
    }

    public String getFindBaseTitleText() {
        return findBaseTitleText;
    }

    public String getFindBaseTitleSubtext() {
        return findBaseTitleSubtext;
    }
}
