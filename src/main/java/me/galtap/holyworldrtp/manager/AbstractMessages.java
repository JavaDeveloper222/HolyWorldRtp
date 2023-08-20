package me.galtap.holyworldrtp.manager;

import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public abstract class AbstractMessages {
    private final String no_permission;
    private final String cooldownMessage;
    private final String teleportMessage;
    private final String coordinates;
    private final String notFoundPlace;
    private final String worldBlockMessage;
    private final String errorArgs;
    private final List<String> helpList;
    public AbstractMessages(ConfigurationSection section){
        no_permission = TextUtil.getColorText(section.getString("No_permission","Обнаружена ошибка в messages.yml исправьте ее"));
        cooldownMessage = TextUtil.getColorText(section.getString("Cooldown","Обнаружена ошибка в messages.yml исправьте ее"));
        teleportMessage = TextUtil.getColorText(section.getString("Teleport","Обнаружена ошибка в messages.yml исправьте ее"));
        coordinates = TextUtil.getColorText(section.getString("Coordinates","Обнаружена ошибка в messages.yml исправьте ее"));
        notFoundPlace = TextUtil.getColorText(section.getString("NoFoundPlace","Обнаружена ошибка в messages.yml исправьте ее"));
        worldBlockMessage = TextUtil.getColorText(section.getString("WorldBlock","Обнаружена ошибка в messages.yml исправьте ее"));
        errorArgs = TextUtil.getColorText(section.getString("ErrorArgs","Обнаружена ошибка в messages.yml исправьте ее"));
        helpList = TextUtil.getColorText(section.getStringList("RtpHelpList"));
    }
    protected ConfigurationSection sectionProcess(ConfigurationSection mainSection, String name){
        if(mainSection.contains(name)) return mainSection.getConfigurationSection(name);
        return mainSection.createSection(name);
    }

    public String getNo_permission() {
        return no_permission;
    }

    public String getCooldownMessage() {
        return cooldownMessage;
    }

    public String getTeleportMessage() {
        return teleportMessage;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getNotFoundPlace() {
        return notFoundPlace;
    }

    public String getWorldBlockMessage() {
        return worldBlockMessage;
    }

    public String getErrorArgs() {
        return errorArgs;
    }

    public List<String> getHelpList() {
        return helpList;
    }
}
