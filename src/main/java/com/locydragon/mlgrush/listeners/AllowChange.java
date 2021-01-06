package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.InGame;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LocyDragon
 * @version V1.0
 * 控制玩家是否允许改变手上物品
 */

public class AllowChange implements Listener {
    public static List<String> fixes = new ArrayList<>();

    public static void addChange(@NonNull Player target, boolean allow) {
        if (allow) {
            fixes.remove(target.getName());
        } else {
            fixes.add(target.getName());
        }
    }

    @EventHandler
    public void onMove(PlayerItemHeldEvent e) {
        if (!InGame.isPlaying(e.getPlayer())) {
            return;
        }
    }
}
