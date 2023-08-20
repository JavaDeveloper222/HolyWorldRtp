package me.galtap.holyworldrtp.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class ErrorHandle {
    private final String fileName;
    private final String pluginName;

    public ErrorHandle(String fileName, String pluginName){

        this.fileName = fileName;
        this.pluginName = pluginName;
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
        World world = Bukkit.getWorld(section.getString(path,"null"));
        if(world == null){
            reportError(section.getCurrentPath()+"."+path,Bukkit.getWorlds().get(0).getName());
            return Bukkit.getWorlds().get(0);
        }
        return world;
    }
    public void reportError(String path,Object def){
        Bukkit.getLogger().info(ChatColor.RED + "[" + pluginName + "] Обнаружена ошибка в " + fileName + ". Недопустимое значение в пути - " + path + " - исправьте ошибку. Переход в первоначальное значение - "+def);
    }
}
