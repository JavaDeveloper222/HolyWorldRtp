package me.galtap.holyworldrtp.utility;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DefaultConfig {
    private final JavaPlugin plugin;
    private FileConfiguration section = null;
    private File file = null;
    private final String name;

    public DefaultConfig(JavaPlugin plugin, String name) {
        this.name = name;
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), this.name);
        }

        this.section = YamlConfiguration.loadConfiguration(this.file);
        InputStream defaultSteam = this.plugin.getResource(this.name);
        if (defaultSteam != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultSteam));
            this.section.setDefaults(defaultConfig);
        }

    }
    public FileConfiguration getConfig() {
        if (this.section == null) {
            this.reloadConfig();
        }

        return this.section;
    }
    public void saveConfig() {
        if (this.section != null && this.file != null) {
            try {
                this.getConfig().save(this.file);
            } catch (IOException e) {
                String errorMessage = String.format("Failed to save the file %s. Reason: %s", this.file.getName(), e.getMessage());
                throw new RuntimeException(ChatColor.RED + errorMessage, e);
            }
        }
    }

    public void saveDefaultConfig() {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), this.name);
        }

        if (!this.file.exists()) {
            this.plugin.saveResource(this.name, false);
        }

    }
}
