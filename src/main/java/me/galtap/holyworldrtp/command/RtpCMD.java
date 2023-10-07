package me.galtap.holyworldrtp.command;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.api.ProtectionStonesManager;
import me.galtap.holyworldrtp.api.WorldGuardManager;
import me.galtap.holyworldrtp.core.IRtp;
import me.galtap.holyworldrtp.core.PrimitiveRtp;
import me.galtap.holyworldrtp.core.impl.BaseRtp;
import me.galtap.holyworldrtp.core.impl.CustomRtp;
import me.galtap.holyworldrtp.core.impl.PlayerRtp;
import me.galtap.holyworldrtp.factory.ApiFactory;
import me.galtap.holyworldrtp.factory.RtpFactory;
import me.galtap.holyworldrtp.settings.GeneralSettings;
import me.galtap.holyworldrtp.settings.MessagesSettings;
import me.galtap.holyworldrtp.utility.Cooldown;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class RtpCMD extends AbstractCommand{
    private final MessagesSettings messagesSettings;
    private final GeneralSettings generalSettings;
    private final RtpFactory rtpFactory;
    private final WorldGuardManager worldGuardManager;
    private final ProtectionStonesManager protectionStonesManager;

    public RtpCMD(MessagesSettings messagesSettings, GeneralSettings generalSettings, RtpFactory rtpFactory, ApiFactory apiFactory) {
        super("rtp", HolyWorldRtp.getInstance());
        this.messagesSettings = messagesSettings;
        this.generalSettings = generalSettings;
        this.rtpFactory = rtpFactory;
        worldGuardManager = apiFactory.getWorldGuardManager();
        protectionStonesManager = apiFactory.getProtectionStonesManager();
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) return;

        Player player = (Player) sender;
        if(args.length == 0){
            primitiveRtpCommandProcess(rtpFactory.getStandardRtp(),player);
            return;
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("player")){
                PlayerRtp rtp = rtpFactory.getPlayerRtp();
                if(rtp.isHasNotPermission(player)){
                    player.sendMessage(messagesSettings.getNoPermissionsMsg());
                    return;
                }
                if(!rtp.serverHasPlayers()){
                    player.sendMessage(messagesSettings.getMinPlayersInServerMsg());
                    return;
                }
                universalRtpCommandProcess(rtp,player);
                return;
            }
            if(args[0].equalsIgnoreCase("base")){
                if(!worldGuardManager.isEnable()){
                    return;
                }
                BaseRtp baseRtp = rtpFactory.getBaseRtp();
                if(worldGuardManager.isEnable() && baseRtp.getSettings().isRegionBlocksEnable() && protectionStonesManager.isNotEnabled()) {
                    return;
                }
                if(baseRtp.isHasNotPermission(player)){
                    player.sendMessage(messagesSettings.getNoPermissionsMsg());
                    return;
                }
                universalRtpCommandProcess(baseRtp,player);
                return;
            }
            if(args[0].equalsIgnoreCase("help")){
                generalSettings.getHelpList().forEach(player::sendMessage);
                return;
            }
            CustomRtp customRtp = rtpFactory.getCustomRtp();
            if(!customRtp.isTrueType(args[0])){
                player.sendMessage(messagesSettings.getHelpMsg());
                return;
            }
            primitiveRtpCommandProcess(customRtp,player);
            return;

        }
        player.sendMessage(messagesSettings.getHelpMsg());
    }


    private void primitiveRtpCommandProcess(PrimitiveRtp rtp, Player player){
        if(rtp.isHasNotPermission(player)){
            player.sendMessage(messagesSettings.getNoPermissionsMsg());
            return;
        }
        if(!rtp.isSafeWorld(player)){
            String message = messagesSettings.getWorldBlockMsg();
            player.sendMessage(message.replace("{world}",player.getWorld().getName()));
            return;
        }
        if(rtp.isHasCooldown(player)){
            String cooldownMsg = messagesSettings.getCooldownMsg();
            player.sendMessage(cooldownMsg.replace("{sec}", String.valueOf(Cooldown.getDelay())));
            return;
        }
        Location location = rtp.getSafeLocation(player);
        if(location == null){
            player.sendMessage(messagesSettings.getErrorMsg());
            return;
        }
        rtp.teleport(player,location);
    }
    public void universalRtpCommandProcess(IRtp rtp, Player player){
        if(rtp.isHasCooldown(player)){
            String message = messagesSettings.getCooldownMsg();
            player.sendMessage(message.replace("{sec}",String.valueOf(Cooldown.getDelay())));
            return;
        }
        Location location = rtp.getSafeLocation(player);
        if(location == null){
            player.sendMessage(messagesSettings.getErrorMsg());
            return;
        }
        player.teleport(location);
    }
    @Override
    public List<String> complete(CommandSender sender, String[] args){
        if(args.length == 1){
            return List.of("help");
        }
        return null;
    }

}
