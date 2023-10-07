package me.galtap.holyworldrtp.model;

import java.util.Objects;

public class Title {
    private final String text;
    private final String subText;
    private final int time1;
    private final int time2;
    private final int time3;

    public Title(String text, String subText, int time1, int time2, int time3){

        this.text = text;
        this.subText = subText;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
    }

    public String getText() {
        return text;
    }

    public String getSubText() {
        return subText;
    }

    public int getTime1() {
        return time1;
    }

    public int getTime2() {
        return time2;
    }

    public int getTime3() {
        return time3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Title title = (Title) o;
        return time1 == title.time1 && time2 == title.time2 && time3 == title.time3 && Objects.equals(text, title.text) && Objects.equals(subText, title.subText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, subText, time1, time2, time3);
    }
}
