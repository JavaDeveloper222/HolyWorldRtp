package me.galtap.holyworldrtp.entity;

import me.galtap.holyworldrtp.manager.AbstractConfig;
import me.galtap.holyworldrtp.manager.AbstractMessages;
import me.galtap.holyworldrtp.utility.Cooldown;
import me.galtap.holyworldrtp.utility.TextUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public abstract class AbstractRtp {
    protected final AbstractConfig config;
    protected final AbstractMessages messages;
    protected final List<PotionEffect> effects;
    private String key;


    protected AbstractRtp(AbstractConfig config, AbstractMessages messages, List<PotionEffect> effects){

        this.config = config;
        this.messages = messages;
        this.effects = effects;
    }
    public abstract boolean hasNotPermission(Player player);
    public abstract void teleport(Location location, Player player);
    public boolean hasCooldown(Player player){
        if(key== null) return false;
        int cooldown = getCooldown();
        if(Cooldown.checkCooldown(key)){
            player.sendMessage(TextUtil.remake("{sec}",messages.getCooldownMessage(),String.valueOf(Cooldown.getDelay())));
            return true;
        }
        Cooldown.setCooldown(key,cooldown);
        return false;

    }
    protected void teleportProcess(Location location, Player player){
        String coordinates = TextUtil.remake("{x}","{y}","{z}",messages.getCoordinates(),String.valueOf(location.getBlockX()),String.valueOf(location.getBlockY()),String.valueOf(location.getBlockZ()));
        player.teleport(location);
        player.addPotionEffects(effects);
        player.setNoDamageTicks(config.getNoDamageTime()*20);
        player.sendMessage(messages.getTeleportMessage());
        player.sendMessage(coordinates);
    }
    protected boolean locationIsNull(Location location,Player player){
        if(location == null){
            player.sendMessage(messages.getNotFoundPlace());
            return true;
        }
        return false;
    }
    protected abstract int getCooldown();
    protected abstract String getPermission();
    protected abstract List<String> getWorldBlockList();

    protected void setKey(String key) {
        this.key = key;
    }
}
