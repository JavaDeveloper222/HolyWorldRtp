package me.galtap.holyworldrtp.settings;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.model.Title;
import me.galtap.holyworldrtp.utility.DefaultConfig;
import me.galtap.holyworldrtp.utility.ErrorHandle;
import me.galtap.holyworldrtp.utility.SimpleUtil;
import org.bukkit.configuration.ConfigurationSection;

public class TitleSettings {
    private final ErrorHandle errorHandle;
    private final Title teleportTitle;
    private final Title findPlayerTitle;
    private final Title warningTitle;
    private final Title findBaseTitle;
    public TitleSettings(ErrorHandle errorHandle){
        this.errorHandle = errorHandle;
        DefaultConfig config = new DefaultConfig(HolyWorldRtp.getInstance(),"titles.yml");
        config.saveConfig();
        ConfigurationSection section = config.getConfig();
        ConfigurationSection teleportTitleSection = SimpleUtil.processSection(section,"StandardRtp.teleportTitle");
        ConfigurationSection findPlayerTitleSection = SimpleUtil.processSection(section,"RtpPlayer.findPlayerTitle");
        ConfigurationSection warningTitleSection = SimpleUtil.processSection(section,"RtpPlayer.warningTitle");
        ConfigurationSection findBaseTitleSection = SimpleUtil.processSection(section,"RtpBase.findBaseTitle");
        teleportTitle = loadTitle(teleportTitleSection);
        findBaseTitle = loadTitle(findBaseTitleSection);
        warningTitle = loadTitle(warningTitleSection);
        findPlayerTitle = loadTitle(findPlayerTitleSection);

    }
    private Title loadTitle(ConfigurationSection section){
        String text = section.getString("text");
        String subText = section.getString("subtext");
        int time1 = errorHandle.check(section,30,"time_1");
        int time2 = errorHandle.check(section,60,"time_2");
        int time3 = errorHandle.check(section,30,"time_3");
        return new Title(text,subText,time1,time2,time3);
    }
    public Title getTeleportTitle() {
        return teleportTitle;
    }

    public Title getFindPlayerTitle() {
        return findPlayerTitle;
    }

    public Title getWarningTitle() {
        return warningTitle;
    }

    public Title getFindBaseTitle() {
        return findBaseTitle;
    }
}
