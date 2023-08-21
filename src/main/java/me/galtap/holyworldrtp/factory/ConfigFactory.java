package me.galtap.holyworldrtp.factory;

import me.galtap.holyworldrtp.utility.DefaultConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigFactory {
    private final DefaultConfig messageConfig;
    private final DefaultConfig effectsConfig;
    private final DefaultConfig titleConfig;
    public ConfigFactory(JavaPlugin plugin){
        messageConfig = new DefaultConfig(plugin,"messages.yml");
        effectsConfig = new DefaultConfig(plugin,"effects.yml");
        titleConfig = new DefaultConfig(plugin,"titles.yml");
        messageConfig.saveConfig();
        effectsConfig.saveConfig();
        titleConfig.saveConfig();

    }

    public DefaultConfig getMessageConfig() {
        return messageConfig;
    }

    public DefaultConfig getEffectsConfig() {
        return effectsConfig;
    }

    public DefaultConfig getTitleConfig() {
        return titleConfig;
    }
}
