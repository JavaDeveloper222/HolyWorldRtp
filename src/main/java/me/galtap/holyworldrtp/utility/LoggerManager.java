package me.galtap.holyworldrtp.utility;

import me.galtap.holyworldrtp.HolyWorldRtp;

public enum LoggerManager {
    MATERIAL_NULL("Такого материала как < %s > не существует"),
    EFFECT_NULL("Такого эффекта как < %s > не существует"),
    MESSAGE_NULL("Обнаружена ошибка в message.yml"),
    WORLD_GUARD_NULL("Внимание для полноценной работы плагина нужно установить на сервер worldGuard и worldEdit"),
    PROTECTION_STONES_NULL("Внимание для работы с блоками привата нужно установить на сервер плагин к дополнению worldGuard -> ProtectionStones");

    private static final java.util.logging.Logger LOGGER = HolyWorldRtp.getInstance().getLogger();
    private final String message;

    LoggerManager(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public void logError(Object... objects){
        String updatedMessage = String.format(message,objects);
        LOGGER.severe(updatedMessage);
    }
    
}
