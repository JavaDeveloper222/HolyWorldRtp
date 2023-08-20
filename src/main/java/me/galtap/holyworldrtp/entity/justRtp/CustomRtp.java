package me.galtap.holyworldrtp.entity.justRtp;

import me.galtap.holyworldrtp.entity.JustAbstractRtp;
import me.galtap.holyworldrtp.manager.configs.CustomRtpConfig;
import me.galtap.holyworldrtp.manager.messages.CustomRtpMessages;
import me.galtap.holyworldrtp.model.CustomRtpContainer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.List;

public class CustomRtp extends JustAbstractRtp {
    private final HashMap<String, CustomRtpContainer> customRtpContainer;
    private CustomRtpContainer customRtpType;

    public CustomRtp(CustomRtpConfig config, CustomRtpMessages messages, List<PotionEffect> effects) {
        super(config, messages, effects);
        customRtpContainer = config.getCustomRtpList();
    }
    public boolean isTrueType(String rtpType,Player player){
        if(!customRtpContainer.containsKey(rtpType.toLowerCase())){
            player.sendMessage(messages.getErrorArgs());
            return false;
        }
        customRtpType = customRtpContainer.get(rtpType.toLowerCase());
        return true;
    }

    @Override
    public void teleport(Location location, Player player) {
        if(locationIsNull(location,player)) return;
        location.setY(location.getBlockY()+1);
        teleportProcess(location,player);
    }

    @Override
    public int getCooldown() {
        return value;
    }

    @Override
    public String getPermission() {
        return "holy.custom.rtp."+customRtpType.getName();
    }

    @Override
    public List<String> getWorldBlockList() {
        return customRtpType.getWorldBlockList();
    }

    @Override
    public World getWorld() {
        return customRtpType.getWorld();
    }

    @Override
    public HashMap<String, Integer> getGroups() {
        return customRtpType.getCooldownMap();
    }

    @Override
    public int getMinX() {
        return customRtpType.getxMin();
    }

    @Override
    public int getMinZ() {
        return customRtpType.getzMin();
    }

    @Override
    public int getMaxX() {
        return customRtpType.getxMax();
    }

    @Override
    public int getMaxZ() {
        return customRtpType.getzMax();
    }

    @Override
    public String getCooldownKey() {
        return "customRtp."+customRtpType.getName();
    }
}
