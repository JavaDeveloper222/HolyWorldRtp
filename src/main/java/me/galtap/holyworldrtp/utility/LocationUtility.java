package me.galtap.holyworldrtp.utility;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public final class LocationUtility {
    private LocationUtility(){
        throw new AssertionError("Utility class LocationUtility cannot be instantiated.");
    }
    public static Location getRandomLocation(int xMin, int xMax, int zMin, int zMax, int yMax, World world, boolean enableNegativeNum) {
        if (world == null || yMax < world.getMinHeight() || yMax > world.getMaxHeight()) {
            return null;
        }

        int x = rndInt(Math.min(xMin, xMax), Math.max(xMin, xMax));
        int z = rndInt(Math.min(zMin, zMax), Math.max(zMin, zMax));

        if (enableNegativeNum) {
            double xChance = Math.random();
            double zChance = Math.random();
            double minusChance = 0.5;
            if (xChance > minusChance) x *= -1;
            if (zChance > minusChance) z *= -1;
        }
        return getValidLocation(world, x, yMax, z);
    }

    public static Location getRandomMaterialSafeLocation(int xMin, int xMax, int zMin, int zMax, int yMax, World world, boolean enableNegativeNum, Set<Material> blockList, int tryFind) {
        if (world == null || tryFind <= 0){
            return null;
        }
        for (; tryFind > 0; tryFind--) {
            Location location = getRandomLocation(xMin, xMax, zMin, zMax,yMax, world, enableNegativeNum);
            if (location == null || blockList.contains(location.getBlock().getType()) || !isInBarrier(location)) {
                continue;
            }
            return location;
        }
        return null;
    }

    public static Location getRandomMaterialSafeLocationInRadius(Location center, int radius, int minDistance, int yMax, Set<Material> blockList, int tryFind) {
        if (center == null || tryFind <= 0) return null;
        World world = center.getWorld();
        int xMin = center.getBlockX() - radius;
        int xMax = center.getBlockX() + radius;
        int zMin = center.getBlockZ() - radius;
        int zMax = center.getBlockZ() + radius;
        for (; tryFind > 0; tryFind--) {
            Location location = getRandomMaterialSafeLocation(xMin, xMax, zMin, zMax, yMax, world, false, blockList, 1);
            if (location == null || location.distance(center) < minDistance) {
                continue;
            }
            return location;
        }
        return null;
    }

    private static Location getValidLocation(World world, int x, int y, int z) {
        int minHeight = world.getMinHeight();
        while (world.getBlockAt(x, y, z).getType().isAir() && y > minHeight-1) {
            y--;
        }
        if (y <= minHeight-1) {
            return null;
        }
        if (!world.isChunkLoaded(x >> 4, z >> 4)) {
            world.loadChunk(x >> 4, z >> 4);
        }
        return new Location(world, x, y, z);
    }

    private static boolean isInBarrier(Location location) {
        if (location == null) return false;
        return Objects.requireNonNull(location.getWorld()).getWorldBorder().isInside(location);
    }
    public static int rndInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
