package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.core.RushInstances;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 趣味击打音效类
 */

public class LobbyHitSound implements Listener {
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player target = (Player)e.getDamager();
            if (target.getItemInHand().equals(RushInstances.MATCHING_ITEM)
            && target.getWorld().getName().equals(RushInstances.RUSH_LOBBY.getWorld().getName())) {
                target.getWorld().playSound(target.getLocation(), Sound.ITEM_BREAK, 1, 1);
            }
        }
    }
}
