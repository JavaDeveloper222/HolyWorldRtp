package me.galtap.holyworldrtp.utility;

import org.bukkit.entity.Player;

public class PermissionUtil {

    public static boolean isHavePermission(String text, Player player){
        try {
            return player.getEffectivePermissions() .stream() .anyMatch(info -> info.getPermission().startsWith(text));
        }catch (NullPointerException e){
            throw new RuntimeException(e);
        }
    }


}
