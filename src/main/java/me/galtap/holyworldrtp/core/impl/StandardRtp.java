package me.galtap.holyworldrtp.core.impl;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.core.PrimitiveRtp;
import me.galtap.holyworldrtp.factory.SettingsFactory;
import me.galtap.holyworldrtp.model.Title;
import me.galtap.holyworldrtp.settings.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class StandardRtp extends PrimitiveRtp {
    private final StandardRtpSettings settings;
    private final EffectsSettings effectsSettings;
    private final MessagesSettings messagesSettings;
    private final TitleSettings titleSettings;
    private final GeneralSettings generalSettings;
    private int moveTask;
    private int teleportTask;

    public StandardRtp(SettingsFactory settingsFactory) {
        super(settingsFactory.getGeneralSettings());
        this.settings = settingsFactory.getStandardRtpSettings();
        this.effectsSettings = settingsFactory.getEffectsSettings();
        this.messagesSettings = settingsFactory.getMessagesSettings();
        this.titleSettings = settingsFactory.getTitleSettings();
        this.generalSettings = settingsFactory.getGeneralSettings();
    }

    @Override
    public void teleport(Player player,Location location){
        String world = player.getWorld().getName();
        String spawnWorld = settings.getSpawnWorld().getName();
        if(world.equals(spawnWorld) || value == 0){
            teleportProcess(player,location,generalSettings.getNoDamageTime(),effectsSettings.getStandardRtpEffects(),messagesSettings.getTeleportMsg(),messagesSettings.getCoordinatesMsg());
            return;
        }
        Title title = titleSettings.getTeleportTitle();
        String subtext = title.getSubText();
        String delayMessage = messagesSettings.getDelayMsg();

        player.sendMessage(delayMessage.replace("{sec}",String.valueOf(value)));
        player.sendTitle(title.getText(),subtext.replace("{sec}",String.valueOf(value)),title.getTime1(),title.getTime2(),title.getTime3());

        teleportWithDelay(player,location);
        if(settings.isEnableMove()){
            moveCheckTimer(player,messagesSettings.getMoveMsg());
        }
    }

    @Override
    protected Map<String, Integer> getGroupData() {
        return settings.getDelayGroupData();
    }

    @Override
    public String getPermission() {
        return "holy.rtp";
    }

    @Override
    public List<String> getWorldBlockList() {
        return settings.getWorldBlockNames();
    }

    @Override
    protected int getMinX() {
        return settings.getxMin();
    }

    @Override
    protected int getMaxX() {
        return settings.getxMax();
    }

    @Override
    protected int getMinZ() {
        return settings.getzMin();
    }

    @Override
    protected int getMaxZ() {
        return settings.getzMax();
    }

    @Override
    public String getCooldownKay() {
        return "standardRtp";
    }

    @Override
    protected World getWorld() {
        return settings.getWorld();
    }

    @Override
    public int getCooldown() {
        return settings.getCooldown();
    }


    private void moveCheckTimer(Player player,String moveMsg){
        Location location = player.getLocation();
        int xStand = location.getBlockX();
        int yStand = location.getBlockY();
        int zStand = location.getBlockZ();
        String worldName = player.getWorld().getName();
        moveTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(HolyWorldRtp.getInstance(), () -> {
            Location newLocation = player.getLocation();
            int newX = newLocation.getBlockX();
            int newY = newLocation.getBlockY();
            int newZ = newLocation.getBlockZ();
            String worldNow = player.getWorld().getName();
            if(newX == xStand && newY == yStand && newZ == zStand && worldNow.equals(worldName)){
                return;
            }
            Bukkit.getScheduler().cancelTask(teleportTask);
            player.sendMessage(moveMsg);
            Bukkit.getScheduler().cancelTask(moveTask);

        },0,20);
    }
    private void teleportWithDelay(Player player, Location location){
        if(player == null || location == null){
            return;
        }
        teleportTask = Bukkit.getScheduler().scheduleSyncDelayedTask(HolyWorldRtp.getInstance(), () -> {
            Bukkit.getScheduler().cancelTask(moveTask);
            teleportProcess(player,location,generalSettings.getNoDamageTime(),effectsSettings.getStandardRtpEffects(),messagesSettings.getTeleportMsg(),messagesSettings.getCoordinatesMsg());
        },value*20L);
    }
}
