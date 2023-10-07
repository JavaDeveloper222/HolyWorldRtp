package me.galtap.holyworldrtp.utility;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DefaultConfig {
    private final JavaPlugin plugin;
    private FileConfiguration section;
    private File file;
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
            var defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultSteam, StandardCharsets.UTF_8));
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
                var errorMessage = String.format("Возникла ошибка при сохранении файла: %s. Причина -> %s", this.file.getName(), e.getMessage());
                Bukkit.getLogger().severe(errorMessage);
            }
        }
    }

    public final void saveDefaultConfig() {
        if (this.file == null) {
            this.file = new File(this.plugin.getDataFolder(), this.name);
        }

        if (!this.file.exists()) {
            this.plugin.saveResource(this.name, false);
        }

    }
}
