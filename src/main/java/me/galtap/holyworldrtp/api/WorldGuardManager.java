package me.galtap.holyworldrtp.api;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.galtap.holyworldrtp.utility.LoggerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;


public class WorldGuardManager {
    private boolean enable;
    public WorldGuardManager(){
        loadPlugin();
    }
    private void loadPlugin(){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if(plugin == null){
            enable = false;
            LoggerManager.WORLD_GUARD_NULL.logError();
            return;
        }
        if(!(plugin instanceof WorldGuardPlugin) || !plugin.isEnabled()){
            enable = false;
            LoggerManager.WORLD_GUARD_NULL.logError();
            return;
        }
        enable = true;
    }
    public Collection<ProtectedRegion> getRegionsFromWold(World world){
        if(world == null || !enable){
            return new ArrayList<>();
        }
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));
        if(regionManager == null){
            return new ArrayList<>();
        }
        return regionManager.getRegions().values();
    }
    public Location getRegionCenter(ProtectedRegion region, World world) {
        int minX = region.getMinimumPoint().getBlockX();
        int minY = region.getMinimumPoint().getBlockY();
        int minZ = region.getMinimumPoint().getBlockZ();

        int maxX = region.getMaximumPoint().getBlockX();
        int maxY = region.getMaximumPoint().getBlockY();
        int maxZ = region.getMaximumPoint().getBlockZ();

        int centerX = (minX + maxX) / 2;
        int centerY = (minY + maxY) / 2;
        int centerZ = (minZ + maxZ) / 2;

        return new Location(world, centerX, centerY, centerZ);
    }

    public boolean isEnable() {
        return enable;
    }
}
