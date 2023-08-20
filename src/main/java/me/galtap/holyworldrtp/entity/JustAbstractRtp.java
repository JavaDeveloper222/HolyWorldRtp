package me.galtap.holyworldrtp.entity;

import me.galtap.holyworldrtp.manager.AbstractConfig;
import me.galtap.holyworldrtp.manager.AbstractMessages;
import me.galtap.holyworldrtp.utility.LocationUtility;
import me.galtap.holyworldrtp.utility.PermissionUtil;
import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.List;

public abstract class JustAbstractRtp extends AbstractRtp {
    protected int value;
    protected JustAbstractRtp(AbstractConfig config, AbstractMessages messages, List<PotionEffect> effects) {
        super(config, messages, effects);
    }
    @Override
    public boolean hasNotPermission(Player player){
        for(String group: getGroups().keySet()){
            if(PermissionUtil.isHavePermission(getPermission()+"."+group,player)){
                value = getGroups().get(group);
                setKey(getCooldownKey()+"."+player.getUniqueId());
                return false;
            }
        }
        player.sendMessage(messages.getNo_permission());
        return true;
    }
    public boolean isNotForbiddenWorld(Player player){
        if(getWorldBlockList().contains(player.getWorld().getName())){
            player.sendMessage(TextUtil.remake("{world}",messages.getWorldBlockMessage(),player.getWorld().getName()));
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
