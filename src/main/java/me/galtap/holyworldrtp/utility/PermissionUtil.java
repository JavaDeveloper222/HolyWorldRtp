package me.galtap.holyworldrtp.utility;

import org.bukkit.entity.Player;

public class PermissionUtil {
    private PermissionUtil(){
        throw new AssertionError("Utility class PermissionUtil cannot be instantiated.");
    }

    public static boolean isHavePermission(String text, Player player){
        return player.getEffectivePermissions()
                .stream()
                .anyMatch(info -> info.getPermission().startsWith(text));
    }
}

