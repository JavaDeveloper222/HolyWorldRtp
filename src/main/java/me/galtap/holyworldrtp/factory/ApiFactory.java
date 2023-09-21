package me.galtap.holyworldrtp.factory;

import me.galtap.holyworldrtp.api.ProtectionStonesAPI;
import me.galtap.holyworldrtp.api.WorldGuardApi;

public class ApiFactory {
    private final WorldGuardApi guardApi;
    private final ProtectionStonesAPI protectionStonesAPI;
    public ApiFactory(){
        guardApi = new WorldGuardApi();
        protectionStonesAPI = new ProtectionStonesAPI();
    }

    public WorldGuardApi getGuardApi() {
        return guardApi;
    }

    public ProtectionStonesAPI getProtectionStonesAPI() {
        return protectionStonesAPI;
    }
}
