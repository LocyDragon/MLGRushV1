package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.core.BedBlock;
import com.locydragon.mlgrush.core.data.GamePlayer;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 挖掘床以获得分数的控制类
 */

public class BedScorer implements Listener {

    @EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e) {
        if (InGame.isPlaying(e.getPlayer()) &&
                (e.getBlock().getType() == Material.BED
                        || e.getBlock().getType() == Material.BED_BLOCK)) {
            e.setCancelled(true);
            GamePlayer player = InGame.get(e.getPlayer());
            BedBlock bedBlock_A = player.area.bedBlock_A;
            BedBlock bedBlock_B = player.area.bedBlock_B;
            if (LocationUtil.equals(bedBlock_A.head, e.getBlock().getLocation()) ||
                    LocationUtil.equals(bedBlock_A.buttom, e.getBlock().getLocation())) {
                player.area.re();
                int points = player.area.b_Score;
                points++;
                player.area.b_Score = points;
                if (points >= 10) {
                    Bukkit.broadcastMessage("§7§l§m§n==========================");
                    Bukkit.broadcastMessage("§4§l→ §eRush比赛: §b" + player.area.A.getName()
                            + " §c§lV.S. §b" + player.area.B.getName() + " §4§l←");
                    Bukkit.broadcastMessage("§4§l→ §e战绩——§b" +
                            player.area.B.getName() + "§e胜出!§a(" + player.area.b_Score + "/" + player.area.a_Score + ") §4§l←");
                    Bukkit.broadcastMessage("§7§l§m§n==========================");
                    InGame.quit(e.getPlayer());
                }
                return;
            }
            if (LocationUtil.equals(bedBlock_B.head, e.getBlock().getLocation()) ||
                    LocationUtil.equals(bedBlock_B.buttom, e.getBlock().getLocation())) {
                player.area.re();
                int points = player.area.a_Score;
                points++;
                player.area.a_Score = points;
                if (points >= 10) {
                    Bukkit.broadcastMessage("§7§l§m§n==========================");
                    Bukkit.broadcastMessage("§4§l→ §eRush比赛: §b" + player.area.A.getName()
                            + " §c§lV.S. §b" + player.area.B.getName() + " §4§l←");
                    Bukkit.broadcastMessage("§4§l→ §e战绩——§b" +
                            player.area.A.getName() + "§e胜出!§a("+ player.area.a_Score + "/" + player.area.b_Score + ") §4§l←");
                    Bukkit.broadcastMessage("§7§l§m§n==========================");
                    InGame.quit(e.getPlayer());
                }
                return;
            }
        }
    }
}
