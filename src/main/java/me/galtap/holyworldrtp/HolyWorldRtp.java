package me.galtap.holyworldrtp;

import me.galtap.holyworldrtp.command.RtpCMD;
import me.galtap.holyworldrtp.factory.ApiFactory;
import me.galtap.holyworldrtp.factory.RtpFactory;
import me.galtap.holyworldrtp.factory.SettingsFactory;
import me.galtap.holyworldrtp.utility.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HolyWorldRtp extends JavaPlugin {
    private static HolyWorldRtp instance;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        SettingsFactory settingsFactory = new SettingsFactory();
        ApiFactory apiFactory = new ApiFactory();
        RtpFactory rtpFactory = new RtpFactory(settingsFactory,apiFactory);
        Cooldown.create();
        new RtpCMD(settingsFactory.getMessagesSettings(), settingsFactory.getGeneralSettings(), rtpFactory,apiFactory);
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static HolyWorldRtp getInstance() {
        return instance;
    }

}
