package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.GamePlayer;
import com.locydragon.mlgrush.core.data.InGame;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 存储玩家放置方块数据
 */

public class BlockStorey implements Listener {

    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }
        if (InGame.isPlaying(e.getPlayer())) {
            if (e.getBlock().getType() == Material.BED_BLOCK ||
                    e.getBlock().getType() == Material.BED) {
                return;
            }
            GamePlayer player = InGame.get(e.getPlayer());
            if (!player.area.isInArea(e.getBlock().getLocation())) {
                e.setCancelled(true);
            } else {
                if (player.area.containsBlockAndRemove(e.getBlock().getLocation())) {
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) {
        if (InGame.isPlaying(e.getPlayer())) {
            if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName()
            .equals(RushInstances.GLASS.getItemMeta().getDisplayName())) {
                e.getPlayer().getItemInHand().setAmount(64);
            }
            GamePlayer player = InGame.get(e.getPlayer());
            if (!player.area.isInArea(e.getBlock().getLocation())) {
                e.setCancelled(true);
            } else {
                int a = player.area.POS_A.getBlockY();
                int b = player.area.POS_B.getBlockY();
                int max = 0;
                if (a > b) {
                    max = a;
                } else {
                    max = b;
                }
                if (e.getBlock().getLocation().getBlockY() >= (max - 2)) {
                    e.getPlayer().sendMessage(RushInstances.RUSH_PREFIX + "已经到达最高建筑高度了!");
                    e.setCancelled(true);
                    return;
                }
                player.area.blockSetLists.add(e.getBlock().getLocation());
                e.setCancelled(false);
            }
        }
    }
}
