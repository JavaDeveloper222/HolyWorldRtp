package me.galtap.holyworldrtp.factory;

import me.galtap.holyworldrtp.api.ProtectionStonesManager;
import me.galtap.holyworldrtp.api.WorldGuardManager;

public class ApiFactory {
    private final WorldGuardManager worldGuardManager;
    private final ProtectionStonesManager protectionStonesManager;
    public ApiFactory(){
        worldGuardManager = new WorldGuardManager();
        protectionStonesManager = new ProtectionStonesManager();
    }

    public WorldGuardManager getWorldGuardManager() {
        return worldGuardManager;
    }

    public ProtectionStonesManager getProtectionStonesManager() {
        return protectionStonesManager;
    }
}
