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
        return plugin instanceof WorldGuardPlugin;
    }
    public ProtectedRegion getRandomRegion(World world, boolean blockCheck,List<Material> blockRegions){
        if(world == null) return null;
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));
        if(regionManager == null) return null;
        List<ProtectedRegion> regions = new ArrayList<>(regionManager.getRegions().values());
        if(blockCheck){
            List<ProtectedRegion> newRegions = protectionBlockFilter(regions,world,blockRegions);
            regions.clear();
            regions.addAll(newRegions);
        }
        if(regions.isEmpty()) return null;
        int randomIndex = LocationUtility.rndInt(0,regions.size()-1);
        return regions.get(randomIndex);
    }
    public List<ProtectedRegion> protectionBlockFilter(List<ProtectedRegion> regions, World world, List<Material> blockRegions){
        for(int i = 0;i < regions.size();i++){
            ProtectedRegion region = regions.get(i);
            Location center = getRegionCenter(region,world);
            if(blockRegions.contains(center.getBlock().getType())){
                continue;
            }
            regions.remove(region);
        }
        return regions;
    }
    public Location getRegionCenter(ProtectedRegion region, World world) {
        BlockVector3 minPoint = region.getMinimumPoint();
        BlockVector3 maxPoint = region.getMaximumPoint();

        int centerX =  (minPoint.getX() + maxPoint.getX()) / 2;
        int centerY =  (minPoint.getY() + maxPoint.getY()) / 2;
        int centerZ =  (minPoint.getZ() + maxPoint.getZ()) / 2;

        return new Location(world, centerX, centerY, centerZ);
    }

    public boolean isExists(){
        return enableState;
    }
}
