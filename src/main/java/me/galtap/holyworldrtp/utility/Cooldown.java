package me.galtap.holyworldrtp.utility;

import java.util.concurrent.ConcurrentHashMap;

public class Cooldown {
    private static ConcurrentHashMap<String, Long> cool;
    private static long delay;

    public static void create() {
        cool = new ConcurrentHashMap<>();
    }

    public static void setCooldown(String cmd, int second) {
        cool.put(cmd, System.currentTimeMillis() + (long)second * 1000L);
    }

    public static boolean checkCooldown(String cmd) {
        if (cool.containsKey(cmd) && cool.get(cmd) > System.currentTimeMillis()) {
            delay = (cool.get(cmd) - System.currentTimeMillis()) / 1000L;
            return true;
        }
        return false;
    }

    public static long getDelay() {
        return delay;
    }
}
