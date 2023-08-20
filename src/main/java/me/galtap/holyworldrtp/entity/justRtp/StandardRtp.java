package me.galtap.holyworldrtp.entity.justRtp;

import me.galtap.holyworldrtp.entity.JustAbstractRtp;
import me.galtap.holyworldrtp.manager.configs.StandardRtpConfig;
import me.galtap.holyworldrtp.manager.messages.StandardRtpMessages;
import me.galtap.holyworldrtp.stream.StreamUpdate;
import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.List;

public class StandardRtp extends JustAbstractRtp {
    private final StandardRtpConfig config;
    private final StandardRtpMessages messages;
    private final JavaPlugin plugin;

    public StandardRtp(StandardRtpConfig config, StandardRtpMessages messages, List<PotionEffect> effects, JavaPlugin plugin) {
        super(config, messages, effects);
        this.config = config;
        this.messages = messages;
        this.plugin = plugin;
    }

    @Override
    public HashMap<String, Integer> getGroups() {
        return config.getGroups();
    }

    @Override
    public int getMinX() {
        return config.getxMin();
    }

    @Override
    public int getMinZ() {
        return config.getzMin();
    }

    @Override
    public int getMaxX() {
        return config.getxMax();
    }

    @Override
    public int getMaxZ() {
        return config.getzMax();
    }

    @Override
    public String getCooldownKey() {
        return "standardRtp";
    }

    @Override
    public void teleport(Location location, Player player) {
        if(locationIsNull(location,player)) return;
        String coordinates = TextUtil.remake("{x}","{y}","{z}",messages.getCoordinates(),String.valueOf(location.getBlockX()),String.valueOf(location.getBlockY()),String.valueOf(location.getBlockZ()));
        location.setY(location.getBlockY()+1);
        if(value == 0){
            teleportProcess(location,player);
            return;
        }
        if(config.isEnableCheckMove()) StreamUpdate.checkMove(player.getLocation(),player,messages.getMoveMessage(),plugin);
        StreamUpdate.teleport(value,player,location,config.getNoDamageTime(),effects,messages.getTeleportMessage(),coordinates,plugin);
        player.sendMessage(TextUtil.remake("{sec}",messages.getDelayMessage(),String.valueOf(value)));
        player.sendTitle(messages.getTeleportTitleText(),TextUtil.remake("{sec}",messages.getTeleportTitleSubstring(),String.valueOf(value)),30,60,30);


    }

    @Override
    public int getCooldown() {
        return config.getCooldown();
    }

    @Override
    public String getPermission() {
        return "holy.standard.rtp";
    }

    @Override
    public List<String> getWorldBlockList() {
        return config.getWorldBlockList();
    }

    @Override
    public World getWorld() {
        return config.getWorld();
    }
}
