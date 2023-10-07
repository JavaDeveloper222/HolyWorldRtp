package me.galtap.holyworldrtp.core.impl;

import me.galtap.holyworldrtp.core.UniversalRtp;
import me.galtap.holyworldrtp.factory.SettingsFactory;
import me.galtap.holyworldrtp.model.Title;
import me.galtap.holyworldrtp.settings.*;
import me.galtap.holyworldrtp.utility.LocationUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class PlayerRtp extends UniversalRtp {
    private final PlayerRtpSettings settings;
    private final GeneralSettings generalSettings;
    private final TitleSettings titleSettings;
    private final EffectsSettings effectsSettings;
    private final MessagesSettings messagesSettings;


    public PlayerRtp(SettingsFactory settingsFactory){

        this.settings = settingsFactory.getPlayerRtpSettings();
        this.generalSettings = settingsFactory.getGeneralSettings();
        this.titleSettings = settingsFactory.getTitleSettings();
        this.effectsSettings = settingsFactory.getEffectsSettings();
        this.messagesSettings = settingsFactory.getMessagesSettings();
    }
    @Override
    public Location getSafeLocation(Player player) {
        Title title = titleSettings.getFindPlayerTitle();
        player.sendTitle(title.getText(),title.getSubText(),title.getTime1(),title.getTime2(),title.getTime3());
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        players.remove(player);
        players.removeIf(player1 -> settings.getWorldBlockList().contains(player1.getWorld().getName()));
        int tryFoundPlayer = settings.getTryFoundPlayer();
        int maxDistance = settings.getMaxDistance();
        int minDistance = settings.getMinDistance();
        int maxY = generalSettings.getMaxY();
        Set<Material> blockList = EnumSet.copyOf(generalSettings.getBlockList());
        int tryFindLocation = generalSettings.getTryFindLocation();
        int playersSize = players.size();
        return playerFindProcess(players,playersSize,tryFoundPlayer,maxDistance,minDistance,maxY,blockList,tryFindLocation);
    }
    private static Location playerFindProcess(List<Player> players,int size,int tryFoundPlayer, int maxDistance, int minDistance, int maxY, Set<Material> blockList, int tryFindLocation){
        Location location = null;
        for (int i = 0; i < tryFoundPlayer; i++) {
            if(players.isEmpty()) return null;
            int randomIndex = LocationUtility.rndInt(0, size-1);
            Player target = players.get(randomIndex);
            if (target == null) {
                continue;
            }
            location = LocationUtility.getRandomMaterialSafeLocationInRadius(target.getLocation(), maxDistance, minDistance,
                    maxY, blockList, tryFindLocation);
            if(location == null){
                players.remove(target);
                continue;
            }
            break;
        }
        return location;
    }


    @Override
    public void teleport(Player player,Location location) {
        teleportProcess(player,location,generalSettings.getNoDamageTime(),effectsSettings.getPlayerRtpEffects(),messagesSettings.getTeleportMsg(),messagesSettings.getCoordinatesMsg());
        Title title = titleSettings.getWarningTitle();
        player.sendTitle(title.getText(),title.getSubText(),title.getTime1(),title.getTime2(),title.getTime3());
    }

    @Override
    public int getCooldown() {
        return settings.getCooldown();
    }

    @Override
    public String getCooldownKay() {
        return "playerRtp";
    }

    @Override
    public String getPermission() {
        return "holy.player.rtp";
    }
    public boolean serverHasPlayers(){
        return Bukkit.getOnlinePlayers().size() >= settings.getMinOnlineInServer();
    }
}
