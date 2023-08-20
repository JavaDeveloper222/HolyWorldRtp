package me.galtap.holyworldrtp.entity.specificAbstractRtp;

import me.galtap.holyworldrtp.entity.SpecificAbstractRtp;
import me.galtap.holyworldrtp.manager.configs.PlayerRtpConfig;
import me.galtap.holyworldrtp.manager.messages.PlayerRtpMessages;
import me.galtap.holyworldrtp.utility.LocationUtility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class PlayerRtp extends SpecificAbstractRtp {
    private final PlayerRtpConfig config;
    private final PlayerRtpMessages messages;
    public PlayerRtp(PlayerRtpConfig config, PlayerRtpMessages messages, List<PotionEffect> effects) {
        super(config, messages, effects);
        this.config = config;
        this.messages = messages;
    }
    public boolean serverHasPlayers(Player player){
        if(Bukkit.getOnlinePlayers().size() < config.getMinOnlineInServer()){
            player.sendMessage(messages.getMinPlayerInServer());
            return false;
        }
        return true;
    }
    public boolean worldHasPlayers(World world, Player player){
        if(world == null){
            player.sendMessage(messages.getError());
            return false;
        }
        if(world.getPlayers().size() < config.getMinOnlineInWorld()){
            player.sendMessage(messages.getMinPlayerInWorld());
            return false;
        }
        return true;
    }
    public Player getRandomPlayer(World world,Player player){
        List<Player> players = world.getPlayers();
        players.remove(player);
        if(players.isEmpty()){
            player.sendMessage(messages.getError());
            return null;
        }
        int randomIndex = LocationUtility.rndInt(0,players.size()-1);
        return players.get(randomIndex);
    }
    @Override
    public void teleport(Location location, Player player) {
        if(player == null) return;
        if(locationIsNull(location,player))return;
        player.sendTitle(messages.getFindTitleText(),messages.getFindTitleSubtext(),30,60,30);
        location.setY(location.getBlockY()+1);
        teleportProcess(location,player);
        player.sendTitle(messages.getWarningTitleText(),messages.getWarningTitleSubtext(),30,60,30);
    }



    @Override
    protected int getCooldown() {
        return config.getCooldown();
    }

    @Override
    protected String getPermission() {
        return "holy.player.rtp";
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
        return "playerRtp";
    }
}
