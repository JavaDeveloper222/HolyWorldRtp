package me.galtap.holyworldrtp.utility;

import java.util.concurrent.ConcurrentHashMap;

public final class Cooldown {
    private static ConcurrentHashMap<String, Long> cool;
    private static long delay;
    private static final long MILLISECONDS = 1000;
    private Cooldown(){}

    public static void create() {
        cool = new ConcurrentHashMap<>();
    }

    public static void setCooldown(String cmd, int second) {
        cool.put(cmd, System.currentTimeMillis() + second * MILLISECONDS);
    }

    public static boolean isHasCooldown(String cmd) {
        if (cool.containsKey(cmd) && cool.get(cmd) > System.currentTimeMillis()) {
            delay = (cool.get(cmd) - System.currentTimeMillis()) / MILLISECONDS;
            return true;
        }
        return false;
    }

    public static long getDelay() {
        return delay;
    }
}
