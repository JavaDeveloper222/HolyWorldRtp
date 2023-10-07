package me.galtap.holyworldrtp.api;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import dev.espi.protectionstones.PSRegion;
import me.galtap.holyworldrtp.utility.LoggerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.Collection;

public class ProtectionStonesManager {
    private boolean enable;
    public ProtectionStonesManager(){
        loadPlugin();
    }
    private void loadPlugin(){
        Plugin plugin = Bukkit.getPluginManager().getPlugin("ProtectionStones");
        if(plugin == null || !plugin.isEnabled()){
            enable = false;
            LoggerManager.PROTECTION_STONES_NULL.logError();
            return;
        }
        enable = true;
    }
    public boolean isSafePSRegion(ProtectedRegion region, World world, Collection<Material> protectedBlocks){
        if(!enable) return false;
        PSRegion psRegion = PSRegion.fromWGRegion(world,region);
        if(psRegion == null) return false;
        Material material = psRegion.getProtectBlock().getType();
        return protectedBlocks.contains(material);
    }

    public boolean isNotEnabled() {
        return !enable;
    }
}
