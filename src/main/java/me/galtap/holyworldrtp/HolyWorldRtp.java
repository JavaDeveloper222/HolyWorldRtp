package me.galtap.holyworldrtp;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.galtap.holyworldrtp.command.RtpCMD;
import me.galtap.holyworldrtp.manager.configs.EffectConfig;
import me.galtap.holyworldrtp.rtpFactory.RtpFactory;
import me.galtap.holyworldrtp.utility.DefaultConfig;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class HolyWorldRtp extends JavaPlugin {
    @Override
    public void onEnable() {
        //загрузка файлов и их manager классов
        DefaultConfig messageConfig = new DefaultConfig(this,"messages.yml");
        DefaultConfig effects = new DefaultConfig(this,"effects.yml");
        DefaultConfig titleConfig = new DefaultConfig(this,"titles.yml");
        messageConfig.saveConfig();
        effects.saveConfig();
        titleConfig.saveConfig();
        saveDefaultConfig();

        EffectConfig effectManager = new EffectConfig(effects.getConfig());

        FileConfiguration messageSection = messageConfig.getConfig();
        FileConfiguration titleSection = titleConfig.getConfig();
        ErrorHandle errorHandle = new ErrorHandle("config.yml", "HolyWorldRtp");

        //проверка присутствия api
        boolean guardApiState = guardApiExists();
        if(!guardApiState){
            Bukkit.getLogger().warning("[HolyWorldRtp] Внимания команда /rtp base  не будут доступна скачайте worldGuard");
        }

        RtpFactory rtpFactory = new RtpFactory(this,errorHandle,messageSection,titleSection,effectManager);
        new RtpCMD(this,rtpFactory,guardApiState);





    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private boolean guardApiExists(){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if(!(plugin instanceof WorldGuardPlugin)) return false;
        return plugin.isEnabled();
    }
}
