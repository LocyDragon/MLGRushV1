package com.locydragon.mlgrush.core.data;

import com.locydragon.mlgrush.core.GameArea;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;

/**
 * @author LocyDragon
 * @version V1.0
 * 地图重置类
 */

public class MapReseter {
    /**
     * 重置一个目标区域
     * @param area 区域对象
     */
    public static void reset(@NonNull GameArea area) {
        for (Location loc : area.blockSetLists) {
            loc.getBlock().setType(Material.AIR);
        }
        area.blockSetLists = new ArrayList<>();
    }
}
