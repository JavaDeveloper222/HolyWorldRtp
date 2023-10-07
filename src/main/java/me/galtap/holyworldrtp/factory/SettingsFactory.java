package me.galtap.holyworldrtp.factory;

import me.galtap.holyworldrtp.HolyWorldRtp;
import me.galtap.holyworldrtp.settings.*;
import me.galtap.holyworldrtp.utility.ErrorHandle;

public class SettingsFactory {
    private final StandardRtpSettings standardRtpSettings;
    private final PlayerRtpSettings playerRtpSettings;
    private final CustomRtpSettings customRtpSettings;
    private final BaseRtpSettings baseRtpSettings;
    private final MessagesSettings messagesSettings;
    private final EffectsSettings effectsSettings;
    private final GeneralSettings generalSettings;
    private final TitleSettings titleSettings;
    public SettingsFactory(){
        ErrorHandle configErrorHandle = new ErrorHandle("config.yml", HolyWorldRtp.getInstance());
        ErrorHandle titleErrorHandle = new ErrorHandle("titles.yml",HolyWorldRtp.getInstance());

        standardRtpSettings = new StandardRtpSettings(configErrorHandle);
        playerRtpSettings = new PlayerRtpSettings(configErrorHandle);
        customRtpSettings = new CustomRtpSettings(configErrorHandle);
        baseRtpSettings = new BaseRtpSettings(configErrorHandle);
        messagesSettings = new MessagesSettings();
        effectsSettings = new EffectsSettings();
        generalSettings = new GeneralSettings(configErrorHandle);
        titleSettings = new TitleSettings(titleErrorHandle);

    }

    public StandardRtpSettings getStandardRtpSettings() {
        return standardRtpSettings;
    }

    public MessagesSettings getMessagesSettings() {
        return messagesSettings;
    }

    public EffectsSettings getEffectsSettings() {
        return effectsSettings;
    }

    public GeneralSettings getGeneralSettings() {
        return generalSettings;
    }

    public TitleSettings getTitleSettings() {
        return titleSettings;
    }

    public CustomRtpSettings getCustomRtpSettings() {
        return customRtpSettings;
    }

    public PlayerRtpSettings getPlayerRtpSettings() {
        return playerRtpSettings;
    }

    public BaseRtpSettings getBaseRtpSettings() {
        return baseRtpSettings;
    }
}
