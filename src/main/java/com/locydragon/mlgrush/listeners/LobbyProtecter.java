package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.core.RushInstances;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 大厅物品/方块建筑保护
 */

public class LobbyProtecter implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getWorld().getName().equals(RushInstances.RUSH_LOBBY.getWorld().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockPlaceEvent e) {
        if (e.getBlock().getWorld().getName().equals(RushInstances.RUSH_LOBBY.getWorld().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(final WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(final FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getEntity().getWorld()
                .getName().equals(RushInstances.RUSH_LOBBY.getWorld().getName())) {
            Player target = (Player)e.getEntity();
            e.setDamage(0);
            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                if (RushInstances.RUSH_LOBBY != null) {
                    target.teleport(RushInstances.RUSH_LOBBY);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
}
