package com.locydragon.mlgrush.listeners;

import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LocyDragon
 * @version V1.0
 * 控制玩家是否允许移动
 */

public class AllowMove implements Listener {
    public static List<String> fixes = new ArrayList<>();

    public static void addMove(@NonNull Player target, boolean allow) {
        if (allow) {
            fixes.remove(target.getName());
        } else {
            fixes.add(target.getName());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!fixes.contains(e.getPlayer().getName())) {
            return;
        }
        if (((int)e.getFrom().getX() != (int)e.getTo().getX()) || ((int)e.getFrom().getY() != (int)e.getTo().getY())
        || ((int)e.getFrom().getZ() != (int)e.getTo().getZ())) {
            e.setCancelled(true);
        }
    }
}
