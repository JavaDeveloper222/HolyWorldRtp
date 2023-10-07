package me.galtap.holyworldrtp.settings;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.utility.DefaultConfig;
import me.galtap.holyworldrtp.utility.LoggerManager;
import me.galtap.holyworldrtp.utility.SimpleUtil;
import org.bukkit.configuration.ConfigurationSection;

public class MessagesSettings {
    private final String noPermissionsMsg;
    private final String cooldownMsg;
    private final String teleportMsg;
    private final String coordinatesMsg;
    private final String errorMsg;
    private final String worldBlockMsg;
    private final String helpMsg;
    private final String delayMsg;
    private final String moveMsg;
    private final String minPlayersInServerMsg;
    public MessagesSettings(){
        HolyWorldRtp plugin = HolyWorldRtp.getInstance();
        DefaultConfig messageConfig = new DefaultConfig(plugin,"messages.yml");
        messageConfig.saveConfig();
        ConfigurationSection section = messageConfig.getConfig();
        String errorMessage = LoggerManager.MESSAGE_NULL.getMessage();
        noPermissionsMsg = SimpleUtil.getColorText( section.getString("No_permission",errorMessage));
        cooldownMsg = SimpleUtil.getColorText(section.getString("Cooldown",errorMessage));
        teleportMsg = SimpleUtil.getColorText(section.getString("Teleport",errorMessage));
        coordinatesMsg = SimpleUtil.getColorText(section.getString("Coordinates",errorMessage));
        errorMsg = SimpleUtil.getColorText(section.getString("Error",errorMessage));
        worldBlockMsg = SimpleUtil.getColorText(section.getString("WorldBlock",errorMessage));
        helpMsg = SimpleUtil.getColorText(section.getString("Help",errorMessage));
        delayMsg = SimpleUtil.getColorText(section.getString("Delay",errorMessage));
        moveMsg = SimpleUtil.getColorText(section.getString("Move",errorMessage));
        minPlayersInServerMsg = SimpleUtil.getColorText(section.getString("MinPlayerInServer",errorMessage));
    }

    public String getNoPermissionsMsg() {
        return noPermissionsMsg;
    }

    public String getCooldownMsg() {
        return cooldownMsg;
    }

    public String getTeleportMsg() {
        return teleportMsg;
    }

    public String getCoordinatesMsg() {
        return coordinatesMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getWorldBlockMsg() {
        return worldBlockMsg;
    }

    public String getHelpMsg() {
        return helpMsg;
    }

    public String getDelayMsg() {
        return delayMsg;
    }

    public String getMoveMsg() {
        return moveMsg;
    }

    public String getMinPlayersInServerMsg() {
        return minPlayersInServerMsg;
    }
}
