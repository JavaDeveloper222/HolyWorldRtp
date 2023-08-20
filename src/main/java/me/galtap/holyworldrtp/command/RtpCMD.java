package me.galtap.holyworldrtp.command;

import me.galtap.holyworldrtp.entity.justRtp.CustomRtp;
import me.galtap.holyworldrtp.entity.justRtp.StandardRtp;
import me.galtap.holyworldrtp.entity.specificAbstractRtp.BaseRtp;
import me.galtap.holyworldrtp.entity.specificAbstractRtp.PlayerRtp;
import me.galtap.holyworldrtp.rtpFactory.RtpFactory;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RtpCMD extends AbstractCommand{


    private final RtpFactory rtpFactory;
    private final boolean worldGuardExists;

    public RtpCMD(JavaPlugin plugin, RtpFactory rtpFactory, boolean worldGuardExists) {
        super("rtp", plugin);
        this.rtpFactory = rtpFactory;
        this.worldGuardExists = worldGuardExists;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) return;
        Player player = (Player) sender;
        if(args.length == 0){
            StandardRtp rtp = rtpFactory.getStandardRtp();
            if(!rtp.hasPermission(player)) return;
            if(rtp.isNotForbiddenWorld(player)) return;
            if(rtp.hasCooldown(player)) return;
            Location location = rtp.getSafeLocation();
            rtp.teleport(location,player);
            return;
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("player")){
                PlayerRtp rtp = rtpFactory.getPlayerRtp();
                if(!rtp.hasPermission(player)) return;
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
                if(!worldGuardExists){
                    if(player.isOp()){
                        player.sendMessage(ChatColor.RED+"Чтобы использовать данную команду нужен плагин worldGuard");
                        return;
                    }
                    return;
                }
                BaseRtp rtp = rtpFactory.getBaseRtp();
                if(!rtp.hasPermission(player)) return;
                if(rtp.hasCooldown(player)) return;
                World world = rtp.getRandmWorld();
                Location center = rtp.getRandomRegion(world,player);
                if(center == null) return;
                Location location = rtp.getSafeLocation(center);
                rtp.teleport(location,player);
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
            if(!customRtp.hasPermission(player)) return;
            if(customRtp.isNotForbiddenWorld(player)) return;
            if(customRtp.hasCooldown(player)) return;
            Location location = customRtp.getSafeLocation();
            customRtp.teleport(location,player);
            return;
        }
        player.sendMessage(rtpFactory.getErrorArgs());
    }
}
