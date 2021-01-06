package com.locydragon.mlgrush.handlers;

import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.core.data.GamePlayer;
import com.locydragon.mlgrush.core.data.InGame;
import com.locydragon.mlgrush.utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author LocyDragon
 * @version V1.0
 * 自动公告发送类
 */

public class BroadCastHandler extends BukkitRunnable {
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (InGame.isPlaying(p)) {
                GamePlayer player = InGame.gamePlayerHashMap.get(p.getName());
                ActionBar.sendActionBar(p,
                        "§4§l→ §c§l" + player.area.A.getName() + ": §b"
                                + player.area.a_Score +
                                "/10 §7分 | §a§l" + player.area.B.getName()
                                + ": §b" + player.area.b_Score + "/10 §7分 §4§l←");
            } else {
                ActionBar.sendActionBar(p, RushInstances.BROAD_CAST);
            }
        }
    }
}
