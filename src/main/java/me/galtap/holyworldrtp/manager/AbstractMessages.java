package me.galtap.holyworldrtp.manager;

import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.configuration.ConfigurationSection;

public abstract class AbstractMessages {
    private final String no_permission;
    private final String cooldownMessage;
    private final String teleportMessage;
    private final String coordinates;
    private final String notFoundPlace;
    private final String worldBlockMessage;
    private final String errorArgs;
    protected AbstractMessages(ConfigurationSection section){
        String errorMessage = "Обнаружена ошибка в messages.yml исправьте ее";
        no_permission = TextUtil.getColorText(section.getString("No_permission",errorMessage));
        cooldownMessage = TextUtil.getColorText(section.getString("Cooldown",errorMessage));
        teleportMessage = TextUtil.getColorText(section.getString("Teleport",errorMessage));
        coordinates = TextUtil.getColorText(section.getString("Coordinates",errorMessage));
        notFoundPlace = TextUtil.getColorText(section.getString("NoFoundPlace",errorMessage));
        worldBlockMessage = TextUtil.getColorText(section.getString("WorldBlock",errorMessage));
        errorArgs = TextUtil.getColorText(section.getString("ErrorArgs",errorMessage));
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
}
