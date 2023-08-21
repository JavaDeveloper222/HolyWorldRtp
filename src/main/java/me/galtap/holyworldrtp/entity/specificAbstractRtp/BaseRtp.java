package me.galtap.holyworldrtp.entity.specificAbstractRtp;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.galtap.holyworldrtp.api.WorldGuardApi;
import me.galtap.holyworldrtp.entity.SpecificAbstractRtp;
import me.galtap.holyworldrtp.manager.configs.BaseRtpConfig;
import me.galtap.holyworldrtp.manager.messages.BaseRtpMessages;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class BaseRtp extends SpecificAbstractRtp {
    private final BaseRtpConfig config;
    private final BaseRtpMessages messages;
    public BaseRtp(BaseRtpConfig config, BaseRtpMessages messages, List<PotionEffect> effects) {
        super(config, messages, effects);
        this.config = config;
        this.messages = messages;
    }
    public Location getRandomCenterRegion(WorldGuardApi worldGuardApi, Player player){
        World world = getRandmWorld();
        if(world == null){
            player.sendMessage(messages.getNotFound());
            return null;
        }
        ProtectedRegion region = worldGuardApi.getRandomRegion(world,config.isEnableBlockCheck(),config.getRegionBlocks());
        if(region == null){
            player.sendMessage(messages.getNotFound());
            return null;
        }
        Location center = worldGuardApi.getRegionCenter(region,world);
        if(center == null){
            player.sendMessage(messages.getNotFound());
            return null;
        }
        return center;
    }

    @Override
    public void teleport(Location location, Player player) {
        if(locationIsNull(location,player))return;
        location.setY(location.getBlockY()+1);
        teleportProcess(location,player);
    }

    @Override
    protected int getCooldown() {
        return config.getCooldown();
    }

    @Override
    protected String getPermission() {
        return "holy.base.rtp";
    }

    @Override
    protected List<String> getWorldBlockList() {
        return config.getWorldBlockList();
    }

    @Override
    protected int getMaxDistance() {
        return config.getMaxDistance();
    }

    @Override
    protected int getMinDistance() {
        return config.getMinDistance();
    }

    @Override
    protected String getCooldownKey() {
        return "baseRtp";
    }
    public BaseRtpConfig getConfig(){
        return config;
    }
    public BaseRtpMessages getMessages(){
        return messages;
    }
}
