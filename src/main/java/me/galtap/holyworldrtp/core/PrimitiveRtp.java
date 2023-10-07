package me.galtap.holyworldrtp.core;

import me.galtap.holyworldrtp.settings.GeneralSettings;
import me.galtap.holyworldrtp.utility.LocationUtility;
import me.galtap.holyworldrtp.utility.SimpleUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Map;

public abstract class PrimitiveRtp implements IRtp {
    private final GeneralSettings settings;
    protected int value;
    protected PrimitiveRtp(GeneralSettings generalSettings){
        this.settings = generalSettings;
    }
    @Override
    public boolean isHasNotPermission(Player player) {
        int highestPriority = Integer.MIN_VALUE;
        String highestPriorityGroup = null;
        for(Map.Entry<String,Integer> entry: getGroupData().entrySet()){
            String groupName = entry.getKey();
            String permission = getPermission()+"."+groupName;

            if(SimpleUtil.isHavePermission(permission,player)){
                try {
                    int priority = Integer.parseInt(permission.substring(permission.lastIndexOf(".") + 1));
                    if(priority > highestPriority){
                        highestPriority = priority;
                        highestPriorityGroup = groupName;
                    }
                }catch (NumberFormatException ignored){
                }
            }
        }
        if(highestPriorityGroup != null){
            value = getGroupData().getOrDefault(highestPriorityGroup,0);
            return false;
        }
        return true;

    }

    public boolean isSafeWorld(Entity player) {
        return !getWorldBlockList().contains(player.getWorld().getName());
    }

    @Override
    public boolean isHasCooldown(Player player) {
        return SimpleUtil.isHasCooldown(player.getUniqueId()+"."+getCooldownKay(),getCooldown());
    }

    @Override
    public Location getSafeLocation(Player player) {
        return LocationUtility.getRandomMaterialSafeLocation(getMinX(),getMaxX(),getMinZ(),getMaxZ(), settings.getMaxY(),getWorld(),true, settings.getBlockList(), settings.getTryFindLocation());
    }
    @Override
    public void teleportProcess(Player player,Location location, int notDamageTime, List<PotionEffect> effects, String teleportMsg, String coordinateMsg){
        SimpleUtil.teleportProcess(player,location,notDamageTime,effects,teleportMsg,coordinateMsg);
    }
    protected abstract Map<String, Integer> getGroupData();
    protected abstract int getMinX();
    protected abstract int getMaxX();
    protected abstract int getMinZ();
    protected abstract int getMaxZ();
    protected abstract World getWorld();
    protected abstract List<String> getWorldBlockList();
}
