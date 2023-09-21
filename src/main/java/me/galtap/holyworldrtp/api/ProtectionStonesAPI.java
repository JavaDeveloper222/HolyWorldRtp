package me.galtap.holyworldrtp.api;


import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import dev.espi.protectionstones.PSRegion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ProtectionStonesAPI {
    private boolean isEnable = false;
    public ProtectionStonesAPI(){
        loadPlugin();
    }

    private void loadPlugin(){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("ProtectionStones");
        if(plugin == null || !plugin.isEnabled()){
            isEnable = false;
            return;
        }
        isEnable = true;
    }
    public boolean isPSRegion(World world, List<Material> psRegionBlocks, ProtectedRegion region){
        if(!isEnable || world == null || region == null) return false;
        PSRegion psRegion = PSRegion.fromWGRegion(world,region);
        if(psRegion == null) return false;
        return psRegionBlocks.contains(psRegion.getProtectBlock().getType());
    }

    public boolean isNotExists() {
        return !isEnable;
    }
}
