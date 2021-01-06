package com.locydragon.mlgrush.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 物品保护类
 */

public class ItemProtecter implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && !(e.getWhoClicked().isOp())) {
            e.setCancelled(true);
        }
    }
}
