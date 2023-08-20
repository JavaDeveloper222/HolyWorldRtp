package me.galtap.holyworldrtp.entity;

import me.galtap.holyworldrtp.manager.AbstractConfig;
import me.galtap.holyworldrtp.manager.AbstractMessages;
import me.galtap.holyworldrtp.utility.LocationUtility;
import me.galtap.holyworldrtp.utility.PermissionUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class SpecificAbstractRtp extends AbstractRtp {
    private final List<World> worlds = new ArrayList<>();
    protected SpecificAbstractRtp(AbstractConfig config, AbstractMessages messages, List<PotionEffect> effects) {
        super(config, messages, effects);
    }
    @Override
    public boolean hasNotPermission(Player player){
        setKey(getCooldownKey()+"."+player.getUniqueId());
        if(PermissionUtil.isHavePermission(getPermission(),player)){
            worldFilter();
            return false;
        }
        player.sendMessage(messages.getNo_permission());
        return true;
    }

    public Location getSafeLocation(Location center){
        return LocationUtility.getRandomMaterialSafeLocationInRadius(center,getMaxDistance(),getMinDistance(),config.getMaxY(),config.getBlockList(),config.getTryFindLocation());
    }
    public World getRandmWorld(){
        if(worlds.isEmpty()) return null;
        int randomIndex = LocationUtility.rndInt(0,worlds.size()-1);
        return worlds.get(randomIndex);
    }
    private void worldFilter(){
        for(World world: Bukkit.getWorlds()){
            if(getWorldBlockList().contains(world.getName())){
                continue;
            }
            worlds.add(world);
        }

    }
    protected abstract int getMaxDistance();
    protected abstract int getMinDistance();
    protected abstract String getCooldownKey();
    protected abstract List<String> getWorldBlockList();

}
