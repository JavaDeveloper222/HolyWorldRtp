package me.galtap.holyworldrtp.utility;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class ErrorHandle {
    private final String fileName;
    private final JavaPlugin plugin;

    public ErrorHandle(String fileName, JavaPlugin plugin){

        this.fileName = fileName;
        this.plugin = plugin;
    }

    public int check(ConfigurationSection section,int def, String path){
        int value = section.getInt(path,-1);
        if(value == -1){
            reportError(section.getCurrentPath()+"."+path,def);
            return def;
        }
        return value;
    }
    public World check(ConfigurationSection section, String path){
        World world = Bukkit.getWorld(section.getString(path,"isNull"));
        if(world == null){
            reportError(path,Bukkit.getWorlds().get(0).getName());
            return Bukkit.getWorlds().get(0);
        }
        return world;
    }
    public void reportError(String path, Object def) {
        String message = String.format("Обнаружена ошибка в %s. Недопустимое значение в пути: %s. Исправьте ошибку. Переход в первоначальное значение: %s", fileName, path, def);
        plugin.getLogger().severe(message);
    }
}
