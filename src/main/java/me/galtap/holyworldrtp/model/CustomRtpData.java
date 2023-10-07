package me.galtap.holyworldrtp.model;

import org.bukkit.World;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomRtpData {
    private final String type;
    private final int xMin;
    private final int xMax;
    private final int zMin;
    private final int zMax;
    private final World world;
    private final List<String> worldBlockList;
    private final Map<String, Integer> groupCooldownData;

    public CustomRtpData(String type, int xMin, int xMax, int zMin, int zMax, World world, List<String> worldBlockList, Map<String,Integer> groupCooldownData){

        this.type = type;
        this.xMin = xMin;
        this.xMax = xMax;
        this.zMin = zMin;
        this.zMax = zMax;
        this.world = world;
        this.worldBlockList = List.copyOf(worldBlockList);
        this.groupCooldownData = Map.copyOf(groupCooldownData);
    }

    public String getType() {
        return type;
    }

    public int getxMin() {
        return xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public int getzMin() {
        return zMin;
    }

    public int getzMax() {
        return zMax;
    }

    public World getWorld() {
        return world;
    }

    public List<String> getWorldBlockList() {
        return List.copyOf(worldBlockList);
    }

    public Map<String, Integer> getGroupCooldownData() {
        return Map.copyOf(groupCooldownData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomRtpData that = (CustomRtpData) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
