package me.galtap.holyworldrtp.model;

import org.bukkit.World;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CustomRtpContainer {
    private final String name;
    private final int xMin;
    private final int xMax;
    private final int zMax;
    private final int zMin;
    private final World world;
    private final HashMap<String, Integer> cooldownMap;
    private final List<String> worldBlockList;

    public CustomRtpContainer(String name, int xMin, int xMax, int zMin, int zMax, World world, HashMap<String,Integer> cooldownMap, List<String> worldBlockList) {

        this.name = name;
        this.xMin = xMin;
        this.xMax = xMax;
        this.zMin = zMin;
        this.zMax = zMax;
        this.world = world;
        this.cooldownMap = cooldownMap;
        this.worldBlockList = worldBlockList;
    }

    public String getName() {
        return name;
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

    public HashMap<String, Integer> getCooldownMap() {
        return cooldownMap;
    }

    public List<String> getWorldBlockList() {
        return worldBlockList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomRtpContainer that = (CustomRtpContainer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
