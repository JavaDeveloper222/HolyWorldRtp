package me.galtap.holyworldrtp.entity.specificAbstractRtp;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.galtap.holyworldrtp.entity.SpecificAbstractRtp;
import me.galtap.holyworldrtp.manager.configs.BaseRtpConfig;
import me.galtap.holyworldrtp.manager.messages.BaseRtpMessages;
import me.galtap.holyworldrtp.utility.LocationUtility;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class BaseRtp extends SpecificAbstractRtp {
    private final BaseRtpConfig config;
    private final BaseRtpMessages messages;
    public BaseRtp(BaseRtpConfig config, BaseRtpMessages messages, List<PotionEffect> effects) {
        super(config, messages, effects);
        this.config = config;
        this.messages = messages;

    }
    public Location getRandomRegion(World world,Player player){
        if(world == null){
            player.sendMessage(messages.getNotFound());
            return null;
        }
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));
        if(regionManager == null){
            player.sendMessage(messages.getNotFound());
            return null;
        }
        List<ProtectedRegion> regions = new ArrayList<>(regionManager.getRegions().values());
        if(config.isEnableBlockCheck()){
            List<ProtectedRegion> newRegions = protectionBlockFilter(regions,world);
            regions.clear();
            regions.addAll(newRegions);
        }
        System.out.println(regions);
        if(regions.isEmpty()){
            player.sendMessage(messages.getNotFound());
            return null;
        }
        int index = LocationUtility.rndInt(0,regions.size()-1);
        ProtectedRegion region = regions.get(index);
        return getRegionCenter(region,world);

    }
    private List<ProtectedRegion> protectionBlockFilter(List<ProtectedRegion> regions, World world){
        for(int i = 0;i < regions.size();i++){
            ProtectedRegion region = regions.get(i);
            Location center = getRegionCenter(region,world);
            if(config.getRegionBlocks().contains(center.getBlock().getType())){
                continue;
            }
            regions.remove(region);
        }
        return regions;
    }
    public static Location getRegionCenter(ProtectedRegion region, World world) {
        BlockVector3 minPoint = region.getMinimumPoint();
        BlockVector3 maxPoint = region.getMaximumPoint();

        int centerX =  (minPoint.getX() + maxPoint.getX()) / 2;
        int centerY =  (minPoint.getY() + maxPoint.getY()) / 2;
        int centerZ =  (minPoint.getZ() + maxPoint.getZ()) / 2;

        return new Location(world, centerX, centerY, centerZ);
    }

    @Override
    public void teleport(Location location, Player player) {
        if(locationIsNull(location,player))return;
        player.sendTitle(messages.getFindBaseTitleText(),messages.getFindBaseTitleSubtext(),30,60,30);
        teleportProcess(location,player);
    }

    @Override
    protected int getCooldown() {
        return config.getCooldown();
    }

    @Override
    protected String getPermission() {
        return "holy.base.rtp";
    }

    @Override
    protected List<String> getWorldBlockList() {
        if(config == null){
            System.out.println("null");
            return new ArrayList<>();
        }
        return config.getWorldBlockList();
    }

    @Override
    protected int getMaxDistance() {
        return config.getMaxDistance();
    }

    @Override
    protected int getMinDistance() {
        return config.getMinDistance();
    }

    @Override
    protected String getCooldownKey() {
        return "baseRtp";
    }
    public BaseRtpMessages getMessages(){
        return messages;
    }
}
