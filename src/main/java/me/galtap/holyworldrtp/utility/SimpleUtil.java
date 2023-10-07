package me.galtap.holyworldrtp.utility;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.permissions.Permissible;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.stream.Collectors;

public final class SimpleUtil {
    private SimpleUtil(){}
    public static ConfigurationSection processSection( ConfigurationSection section, String name){
        if(section.contains(name)){
            return section.getConfigurationSection(name);
        }
        return section.createSection(name);
    }
    public static String getColorText(String text) {
        if (text == null){
            return null;
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static List<String> getColorText(List<String> list) {
        return list.stream().map(SimpleUtil::getColorText).collect(Collectors.toList());
    }
    public static boolean isHavePermission(String text, Permissible player){
        return player.getEffectivePermissions()
                .stream()
                .anyMatch(info -> info.getPermission().startsWith(text));
    }
    public static boolean isHasCooldown(String key, int cooldown){
        if(Cooldown.isHasCooldown(key)){
            return true;
        }
        Cooldown.setCooldown(key,cooldown);
        return false;
    }
    public static void teleportProcess(LivingEntity player, Location location, int notDamageTime, List<PotionEffect> effects, String teleportMsg, String coordinateMsg){
        player.teleport(location);
        player.setNoDamageTicks(notDamageTime*20);
        player.addPotionEffects(effects);
        player.sendMessage(teleportMsg);
        player.sendMessage(coordinateMsg.replace("{x}",String.valueOf(location.getBlockX()))
                .replace("{y}",String.valueOf(location.getBlockY()))
                .replace("{z}",String.valueOf(location.getBlockZ())));
    }
}
