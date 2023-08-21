package me.galtap.holyworldrtp.command;

import me.galtap.holyworldrtp.entity.justRtp.CustomRtp;
import me.galtap.holyworldrtp.entity.justRtp.StandardRtp;
import me.galtap.holyworldrtp.entity.specificAbstractRtp.BaseRtp;
import me.galtap.holyworldrtp.entity.specificAbstractRtp.PlayerRtp;
import me.galtap.holyworldrtp.factory.ApiFactory;
import me.galtap.holyworldrtp.factory.RtpFactory;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RtpCMD extends AbstractCommand{


    private final RtpFactory rtpFactory;
    private final ApiFactory apiFactory;

    public RtpCMD(JavaPlugin plugin, RtpFactory rtpFactory, ApiFactory apiFactory) {
        super("rtp", plugin);
        this.rtpFactory = rtpFactory;
        this.apiFactory = apiFactory;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) return;
        Player player = (Player) sender;
        if(args.length == 0){
            StandardRtp rtp = rtpFactory.getStandardRtp();
            if(rtp.hasNotPermission(player)) return;
            if(rtp.isNotForbiddenWorld(player)) return;
            if(rtp.hasCooldown(player)) return;
            Location location = rtp.getSafeLocation();
            rtp.teleport(location,player);
            return;
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("player")){
                PlayerRtp rtp = rtpFactory.getPlayerRtp();
                if(rtp.hasNotPermission(player)) return;
                if(rtp.hasCooldown(player)) return;
                if(!rtp.serverHasPlayers(player)) return;
                World world = rtp.getRandmWorld();
                if(!rtp.worldHasPlayers(world,player)) return;
                Player target = rtp.getRandomPlayer(world,player);
                if(target == null) return;
                Location location = rtp.getSafeLocation(target.getLocation());
                rtp.teleport(location,player);
                return;
            }
            if(args[0].equalsIgnoreCase("base")){
                if(!apiFactory.getGuardApi().isExists()) return;
                BaseRtp baseRtp = rtpFactory.getBaseRtp();
                if(baseRtp.hasNotPermission(player)) return;
                if(baseRtp.hasCooldown(player)) return;
                Location centerRegion = baseRtp.getRandomCenterRegion(apiFactory.getGuardApi(),player);
                if(centerRegion == null) return;
                Location location = baseRtp.getSafeLocation(centerRegion);
                if(location == null) return;
                baseRtp.teleport(location,player);
                return;
            }
            if(args[0].equalsIgnoreCase("help")){
                for(String text: rtpFactory.getHelpList()){
                    player.sendMessage(text);
                }
                return;
            }
            String rtpType = args[0];
            CustomRtp customRtp = rtpFactory.getCustomRtp();
            if(!customRtp.isTrueType(rtpType,player)) return;
            if(customRtp.hasNotPermission(player)) return;
            if(customRtp.isNotForbiddenWorld(player)) return;
            if(customRtp.hasCooldown(player)) return;
            Location location = customRtp.getSafeLocation();
            customRtp.teleport(location,player);
            return;
        }
        player.sendMessage(rtpFactory.getErrorArgs());
    }
    @Override
    public List<String> complete(CommandSender sender, String[] args){
        if(!(sender instanceof Player)) return Collections.emptyList();
        List<String> list = new ArrayList<>();
        if(args.length == 1){
            list.add("help");
            return list;
        }
        return Collections.emptyList();
    }
}
