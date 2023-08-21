package me.galtap.holyworldrtp;

import me.galtap.holyworldrtp.command.RtpCMD;
import me.galtap.holyworldrtp.factory.ApiFactory;
import me.galtap.holyworldrtp.factory.ConfigFactory;
import me.galtap.holyworldrtp.factory.RtpFactory;
import me.galtap.holyworldrtp.manager.configs.EffectConfig;
import me.galtap.holyworldrtp.utility.Cooldown;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HolyWorldRtp extends JavaPlugin {
    @Override
    public void onEnable() {
        ConfigFactory configFactory = new ConfigFactory(this);
        ApiFactory apiFactory = new ApiFactory();
        ErrorHandle errorHandle = new ErrorHandle("config.yml", "HolyWorldRtp");
        EffectConfig effectManager = new EffectConfig(configFactory.getEffectsConfig().getConfig());
        RtpFactory rtpFactory = new RtpFactory(this,errorHandle,configFactory.getMessageConfig().getConfig(),configFactory.getTitleConfig().getConfig(),effectManager);
        if(!apiFactory.getGuardApi().isExists()) Bukkit.getLogger().warning("[HolyWorldRtp] внимание команда /rtp base не будет работать установите на сервер плагин worldGuard");
        new RtpCMD(this,rtpFactory,apiFactory);
        Cooldown.create();





    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
