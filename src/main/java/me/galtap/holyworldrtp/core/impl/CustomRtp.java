package me.galtap.holyworldrtp.core.impl;

import me.galtap.holyworldrtp.core.PrimitiveRtp;
import me.galtap.holyworldrtp.factory.SettingsFactory;
import me.galtap.holyworldrtp.model.CustomRtpData;
import me.galtap.holyworldrtp.settings.*;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class CustomRtp extends PrimitiveRtp {
    private final CustomRtpSettings settings;
    private final MessagesSettings messagesSettings;
    private final EffectsSettings effectsSettings;
    private final GeneralSettings generalSettings;
    private CustomRtpData rtpData;
    public CustomRtp(SettingsFactory settingsFactory) {
        super(settingsFactory.getGeneralSettings());
        this.settings = settingsFactory.getCustomRtpSettings();
        this.messagesSettings = settingsFactory.getMessagesSettings();
        this.effectsSettings = settingsFactory.getEffectsSettings();
        this.generalSettings = settingsFactory.getGeneralSettings();
    }

    @Override
    public void teleport(Player player,Location location) {
        teleportProcess(player,location,generalSettings.getNoDamageTime(),effectsSettings.getCustomRtpEffects(),messagesSettings.getTeleportMsg(),messagesSettings.getCoordinatesMsg());
    }

    @Override
    protected Map<String, Integer> getGroupData() {
        return rtpData.getGroupCooldownData();
    }

    @Override
    public String getPermission() {
        return "holy.custom.rtp."+rtpData.getType();
    }

    @Override
    public List<String> getWorldBlockList() {
        return rtpData.getWorldBlockList();
    }

    @Override
    protected int getMinX() {
        return rtpData.getxMin();
    }

    @Override
    protected int getMaxX() {
        return rtpData.getxMax();
    }

    @Override
    protected int getMinZ() {
        return rtpData.getzMin();
    }

    @Override
    protected int getMaxZ() {
        return rtpData.getzMax();
    }


    @Override
    protected World getWorld() {
        return rtpData.getWorld();
    }

    @Override
    public int getCooldown() {
        return value;
    }

    @Override
    public String getCooldownKay() {
        return "customRtp."+rtpData.getType();
    }

    public boolean isTrueType(String type){
        rtpData = settings.getRtpData(type);
        return rtpData != null;
    }
}
