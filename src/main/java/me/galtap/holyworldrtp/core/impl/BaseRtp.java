package me.galtap.holyworldrtp.core.impl;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.galtap.holyworldrtp.api.ProtectionStonesManager;
import me.galtap.holyworldrtp.api.WorldGuardManager;
import me.galtap.holyworldrtp.core.UniversalRtp;
import me.galtap.holyworldrtp.factory.ApiFactory;
import me.galtap.holyworldrtp.factory.SettingsFactory;
import me.galtap.holyworldrtp.model.Title;
import me.galtap.holyworldrtp.settings.*;
import me.galtap.holyworldrtp.utility.LocationUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class BaseRtp extends UniversalRtp {
    private final BaseRtpSettings settings;
    private final MessagesSettings messagesSettings;
    private final EffectsSettings effectsSettings;
    private final TitleSettings titleSettings;
    private final GeneralSettings generalSettings;
    private final WorldGuardManager worldGuardManager;
    private final ProtectionStonesManager protectionStonesManager;
    private final List<World> filterWorlds = new ArrayList<>();
    public BaseRtp(SettingsFactory settingsFactory, ApiFactory apiFactory){
        this.settings = settingsFactory.getBaseRtpSettings();
        this.messagesSettings = settingsFactory.getMessagesSettings();
        this.effectsSettings = settingsFactory.getEffectsSettings();
        this.titleSettings = settingsFactory.getTitleSettings();
        this.generalSettings = settingsFactory.getGeneralSettings();
        this.worldGuardManager = apiFactory.getWorldGuardManager();
        this.protectionStonesManager = apiFactory.getProtectionStonesManager();
        filterWorlds.addAll(Bukkit.getWorlds().stream().filter(world -> !settings.getWorldBlockList().contains(world.getName())).collect(Collectors.toList()));
    }
    @Override
    public Location getSafeLocation(Player player) {
        Title title = titleSettings.getFindBaseTitle();
        player.sendTitle(title.getText(),title.getSubText(),title.getTime1(),title.getTime2(),title.getTime3());
        Map<ProtectedRegion,Location> regionLocationMap = regionFilter();
        if(regionLocationMap.isEmpty()) return null;
        return getRandomRegionLocation(regionLocationMap,settings.getMinDistance(),settings.getMaxDistance(),generalSettings.getMaxY(),new HashSet<>(generalSettings.getBlockList()),generalSettings.getTryFindLocation());
    }

    private Location getRandomRegionLocation(Map<ProtectedRegion,Location> regionLocationMap, int radius, int minDistance, int yMax, Set<Material> blockList, int tryFind){
        List<ProtectedRegion> regions = new ArrayList<>(regionLocationMap.keySet());
        Location location = null;
        for(int i = 0;i < settings.getTryFoundBase();i++){
            if(regions.isEmpty()) return null;
            int size = regions.size();
            int randomIndex = LocationUtility.rndInt(0, size-1);
            ProtectedRegion region = regions.get(randomIndex);
            Location center = regionLocationMap.get(region);
            location = LocationUtility.getRandomMaterialSafeLocationInRadius(center,radius,minDistance,yMax,blockList,tryFind);
            if(location == null){
                regions.remove(region);
                continue;
            }
            break;
        }
        return location;
    }
    private Map<ProtectedRegion, Location> regionFilter() {
        Map<ProtectedRegion, Location> regionLocationMap = new HashMap<>();
        if (filterWorlds.isEmpty() || (settings.isRegionBlocksEnable() && protectionStonesManager.isNotEnabled())) {
            return regionLocationMap;
        }
        boolean isRegionBlocksEnable = settings.isRegionBlocksEnable();

        for (World world : filterWorlds) {
            List<ProtectedRegion> regions = new ArrayList<>(worldGuardManager.getRegionsFromWold(world));
            if (regions.isEmpty()) {
                continue;
            }
            if (isRegionBlocksEnable) {
                regions.removeIf(region -> !protectionStonesManager.isSafePSRegion(region, world, settings.getRegionBlocks()));
            }
            if (regions.isEmpty()){
                continue;
            }
            for (ProtectedRegion region : regions) {
                Location location = worldGuardManager.getRegionCenter(region, world);
                regionLocationMap.put(region, location);
            }
        }
        return regionLocationMap;
    }




    @Override
    public void teleport(Player player, Location location) {
        teleportProcess(player,location,generalSettings.getNoDamageTime(),effectsSettings.getBaseRtpEffects(),messagesSettings.getTeleportMsg(),messagesSettings.getCoordinatesMsg());
    }

    @Override
    public int getCooldown() {
        return settings.getCooldown();
    }

    @Override
    public String getCooldownKay() {
        return "baseRtp";
    }

    @Override
    public String getPermission() {
        return "holy.base.rtp";
    }

    public BaseRtpSettings getSettings() {
        return settings;
    }
}
