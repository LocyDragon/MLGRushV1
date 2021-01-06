package com.locydragon.mlgrush.listeners;

import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LocyDragon
 * @version V1.0
 * 控制玩家是否允许攻击
 */

public class AllowHit implements Listener {
    public static List<String> fixes = new ArrayList<>();

    public static void addHit(@NonNull Player target, boolean allow) {
        if (allow) {
            fixes.remove(target.getName());
        } else {
            fixes.add(target.getName());
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (fixes.contains(e.getDamager().getName())) {
            e.setCancelled(true);
        }
    }
}
