package me.galtap.holyworldrtp.factory;

import me.galtap.holyworldrtp.core.impl.BaseRtp;
import me.galtap.holyworldrtp.core.impl.CustomRtp;
import me.galtap.holyworldrtp.core.impl.PlayerRtp;
import me.galtap.holyworldrtp.core.impl.StandardRtp;

public class RtpFactory {
    private final StandardRtp standardRtp;
    private final CustomRtp customRtp;
    private final PlayerRtp playerRtp;
    private final BaseRtp baseRtp;
    public RtpFactory(SettingsFactory settingsFactory, ApiFactory apiFactory){
        standardRtp = new StandardRtp(settingsFactory);
        customRtp = new CustomRtp(settingsFactory);
        playerRtp = new PlayerRtp(settingsFactory);
        baseRtp = new BaseRtp(settingsFactory,apiFactory);
    }

    public StandardRtp getStandardRtp() {
        return standardRtp;
    }

    public CustomRtp getCustomRtp() {
        return customRtp;
    }

    public PlayerRtp getPlayerRtp() {
        return playerRtp;
    }

    public BaseRtp getBaseRtp() {
        return baseRtp;
    }
}
