package me.galtap.holyworldrtp.factory;

import me.galtap.holyworldrtp.api.WorldGuardApi;

public class ApiFactory {
    private final WorldGuardApi guardApi;
    public ApiFactory(){
        guardApi = new WorldGuardApi();
    }

    public WorldGuardApi getGuardApi() {
        return guardApi;
    }
}
