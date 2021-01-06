package com.locydragon.mlgrush.handlers;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.RushInstances;
import org.bukkit.Location;

/**
 * @author LocyDragon
 * @version V1.0
 * 加载地图的玩意儿
 */

public class ConfigLoader {
    public static void loadSettings() {
        RushInstances.RUSH_LOBBY = (Location) MLGRush.rushConfig.get("Lobby");
    }
}
