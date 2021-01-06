package com.locydragon.mlgrush.utils;

import lombok.NonNull;
import org.bukkit.Location;

/**
 * @author LocyDragon
 * @version V1.0
 * 坐标比较类
 */

public class LocationUtil {
    /**
     * 坐标比较
     * @param loc 第一个坐标
     * @param location 第二个坐标
     * @return 坐标是否重合
     */
    public static boolean equals(@NonNull Location loc, @NonNull Location location) {
        return loc.getBlockX() == location.getBlockX() && loc.getBlockY() == location.getBlockY()
                && loc.getBlockZ() == location.getBlockZ()
                && loc.getWorld().getName().equals(location.getWorld().getName());
    }
}
