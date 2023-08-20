package me.galtap.holyworldrtp.entity;

import me.galtap.holyworldrtp.manager.AbstractConfig;
import me.galtap.holyworldrtp.manager.AbstractMessages;
import me.galtap.holyworldrtp.utility.LocationUtility;
import me.galtap.holyworldrtp.utility.PermissionUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.List;

public abstract class JustAbstractRtp extends AbstractRtp {
    protected int value;
    public JustAbstractRtp(AbstractConfig config, AbstractMessages messages, List<PotionEffect> effects) {
        super(config, messages, effects);
    }
    @Override
    public boolean hasPermission(Player player){
        for(String group: getGroups().keySet()){
            if(PermissionUtil.isHavePermission(getPermission()+"."+group,player)){
                value = getGroups().get(group);
                setKey(getCooldownKey()+"."+player.getUniqueId());
                return true;
            }
        }
        player.sendMessage(messages.getNo_permission());
        return false;
    }
    public boolean isNotForbiddenWorld(Player player){
        if(getWorldBlockList().contains(player.getWorld().getName())){
            player.sendMessage(messages.getWorldBlockMessage());
            return true;
        }
        return false;
    }
    public Location getSafeLocation(){
        return LocationUtility.getRandomMaterialSafeLocation(getMinX(),getMaxX(),getMinZ(),getMaxZ(),config.getMaxY(),getWorld(),true,config.getBlockList(),config.getTryFindLocation());
    }

    protected abstract HashMap<String,Integer> getGroups();
    protected abstract int getMinX();
    protected abstract int getMinZ();
    protected abstract int getMaxX();
    protected abstract int getMaxZ();
    protected abstract String getCooldownKey();
    protected abstract World getWorld();

}
