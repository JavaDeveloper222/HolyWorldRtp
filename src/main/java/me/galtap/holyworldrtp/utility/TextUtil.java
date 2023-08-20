package me.galtap.holyworldrtp.utility;



import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class TextUtil {
    public static String getColorText(String text){
        if(text == null) return "";
        return ChatColor.translateAlternateColorCodes('&',text);
    }
    public static String remake(String substring,String text,String value){
        if(text == null) return "";
        if(substring == null) return text;
        return text.replace(substring,value);
    }
    public static List<String> getColorText(List<String> list){
        return list.stream().map(TextUtil::getColorText).collect(Collectors.toList());
    }
    public static String remake(String x, String y, String z, String text, String valueX, String valueY, String valueZ){
        text = remake(x,text,valueX);
        text = remake(y,text,valueY);
        text = remake(z,text,valueZ);
        return text;
    }
}
