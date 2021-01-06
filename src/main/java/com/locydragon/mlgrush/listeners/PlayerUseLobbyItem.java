package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.AreaLoader;
import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.GamePlayer;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.core.team.TeamMatching;
import com.locydragon.mlgrush.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import java.util.HashSet;
import java.util.Map;

/**
 * @author LocyDragon
 * @version V1.0
 * 匹配物品使用类
 */

public class PlayerUseLobbyItem implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand() != null) {
            if (e.getPlayer().getItemInHand().equals(RushInstances.WATCHER_ITEM)) {
                Inventory gui = Bukkit.createInventory(null, 27, RushInstances.RUSH_PREFIX + "观战面板");
                HashSet<GameArea> areaList = new HashSet<>();
                for (Map.Entry<String, GamePlayer> obj : InGame.gamePlayerHashMap.entrySet()) {
                    areaList.add(obj.getValue().area);
                }
                int i = 0;
                for (GameArea area : areaList) {
                    int a = area.a_Score;
                    int b = area.b_Score;
                    if (a < b) {
                        a = b;
                    }
                    gui.setItem(i, new ItemBuilder(Material.BED)
                            .setDisplayName("§c§l" + area.A.getName()
                                    + " §e§lV.S. §a§l" + area.B.getName())
                            .addEnchantment(Enchantment.ARROW_FIRE, 1)
                            .setAmount(a).build());
                    i++;
                }
                e.getPlayer().openInventory(gui);
            } else if (e.getPlayer().getItemInHand().equals(RushInstances.MATCHING_ITEM)
                    && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                if (!AreaLoader.hasEmptyArea()) {
                    e.getPlayer().sendMessage(RushInstances.RUSH_PREFIX + "房间已满员……请等候!");
                    return;
                }
                e.getPlayer().sendMessage(RushInstances.RUSH_PREFIX + "已经加入匹配队列!请耐心等候，游戏不久后开始!");
                TeamMatching.addWaiting(e.getPlayer());
                e.getPlayer().sendMessage(RushInstances.RUSH_PREFIX + "当前匹配人数: " + TeamMatching.matchingPlayer.size() + "人(包括你自己).");
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(8, RushInstances.QUIT);
                e.getPlayer().updateInventory();
            } else if (e.getPlayer().getItemInHand().equals(RushInstances.QUIT)) {
                TeamMatching.removeWaiting(e.getPlayer());
                e.getPlayer().sendMessage(RushInstances.RUSH_PREFIX + "已成功退出匹配!");
                e.getPlayer().getInventory().clear();
                e.getPlayer().getInventory().setItem(0, RushInstances.MATCHING_ITEM);
                e.getPlayer().getInventory().setItem(8, RushInstances.WATCHER_ITEM);
                e.getPlayer().updateInventory();
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        TeamMatching.removeWaiting(e.getPlayer());
    }

    @EventHandler
    public void onBeingKick(PlayerKickEvent e) {
        TeamMatching.removeWaiting(e.getPlayer());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals(RushInstances.RUSH_PREFIX + "观战面板")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.BED
            && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta()
            .getDisplayName().contains("V.S.")) {
                String name = ChatColor
                        .stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
                String[] amounts = name.replace("V.S.", "@").split("@");
                Player playerA = Bukkit.getPlayer(amounts[0].trim());
                Player playerB = Bukkit.getPlayer(amounts[1].trim());
                if (!playerA.isOnline() || !playerB.isOnline()) {
                    e.getWhoClicked().sendMessage(RushInstances.RUSH_PREFIX + "该局比赛已经结束.");
                    return;
                }
                if (!InGame.isPlaying(playerA) || !InGame.isPlaying(playerB)) {
                    e.getWhoClicked().sendMessage(RushInstances.RUSH_PREFIX + "该局比赛已经结束.");
                    return;
                }
                GameArea areaA = InGame.get(playerA).area;
                GameArea areaB = InGame.get(playerB).area;
                if (!areaA.areaName.equals(areaB.areaName)) {
                    e.getWhoClicked().sendMessage(RushInstances.RUSH_PREFIX + "该局比赛已经结束.");
                    return;
                }
                e.getWhoClicked().teleport(areaA.MONITOR);
                Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
                    e.getWhoClicked().setGameMode(GameMode.SPECTATOR);
                }, 10);
                e.getWhoClicked().sendMessage(RushInstances.RUSH_PREFIX + "您已经进入观战模式.");
                e.getWhoClicked().sendMessage(RushInstances.RUSH_PREFIX + "使用 /spawn 来退出观战!");
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if (!e.getFrom().getWorld().getName().equals(e.getTo().getWorld().getName())
        && e.getTo().getWorld().getName().equals(RushInstances.RUSH_LOBBY.getWorld().getName())) {
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
            InGame.quit(e.getPlayer());
        }
    }
}
