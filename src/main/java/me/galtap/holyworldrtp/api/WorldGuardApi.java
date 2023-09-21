package me.galtap.holyworldrtp.api;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.galtap.holyworldrtp.utility.LocationUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class WorldGuardApi {
    private final boolean enableState;
    public WorldGuardApi(){
        enableState = checkPlugin();
    }
    private boolean checkPlugin(){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("WorldGuard");
        return plugin instanceof WorldGuardPlugin && plugin.isEnabled();
    }
    public ProtectedRegion getRandomRegion(List<ProtectedRegion> regions){
        int randomIndex = LocationUtility.rndInt(0,regions.size()-1);
        return regions.get(randomIndex);
    }
    public Location getRegionCenter(ProtectedRegion region, World world) {
        BlockVector3 minPoint = region.getMinimumPoint();
        BlockVector3 maxPoint = region.getMaximumPoint();

        int centerX =  (minPoint.getX() + maxPoint.getX()) / 2;
        int centerY =  (minPoint.getY() + maxPoint.getY()) / 2;
        int centerZ =  (minPoint.getZ() + maxPoint.getZ()) / 2;

        return new Location(world, centerX, centerY, centerZ);
    }
    public List<ProtectedRegion> gerAllRegionsInWorld(World world){
        List<ProtectedRegion> allRegions = new ArrayList<>();
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));
        if(regionManager == null) return allRegions;
        allRegions.addAll(regionManager.getRegions().values());
        return allRegions;
    }

    public List<ProtectedRegion> protectionStonesRegionFilter(List<Material> PSBlocks, ProtectionStonesAPI protectionStonesAPI, List<ProtectedRegion> regions, World world){
        List<ProtectedRegion> psRegions = new ArrayList<>();
        for(ProtectedRegion region: regions){
            if(protectionStonesAPI.isPSRegion(world,PSBlocks,region)){
                psRegions.add(region);
            }
        }
        return psRegions;
    }

    public boolean isNotExists(){
        return !enableState;
    }
}
