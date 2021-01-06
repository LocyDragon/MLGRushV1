package com.locydragon.mlgrush.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LocyDragon
 * @version V1.0
 * CPS检测类
 */

public class CPSListener implements Listener {
    public Map<String, Integer> cpsLeft = new HashMap();
    public Map<String, Integer> cpsRight = new HashMap();

    /**
     * 清除cps数据
     */

    public void clearCps() {
        this.cpsRight.clear();
        this.cpsLeft.clear();
    }

    /**
     * 获取cps数据
     * @param playerName 玩家名称
     * @return cps
     */

    public int getLeft(String playerName) {
        int cps = this.cpsLeft.getOrDefault(playerName.toLowerCase(), -1);
        if (cps == -1) {
            return 0;
        }
        return cps;
    }

    /**
     * 获取cps数据
     * @param playerName 玩家名称
     * @return cps
     */

    public int getRight(String playerName) {
        int cps = this.cpsRight.getOrDefault(playerName.toLowerCase(), -1);
        if (cps == -1) {
            return 0;
        }
        return cps;
    }

    /**
     * 放入cps数据
     * @param playerName 玩家名称
     */

    public void putLeft(String playerName) {
        if (cpsLeft.getOrDefault(playerName, -1) == -1) {
            cpsLeft.put(playerName, 1);
        } else {
            cpsLeft.put(playerName, this.cpsLeft.get(playerName) + 1);
        }
    }

    /**
     * 放入cps数据
     * @param playerName 玩家名称
     */

    public void putRight(String playerName) {
        if (cpsRight.getOrDefault(playerName, -1) == -1) {
            cpsRight.put(playerName, 1);
        } else {
            cpsRight.put(playerName, this.cpsRight.get(playerName) + 1);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Action ac = e.getAction();

        if ((ac == Action.LEFT_CLICK_AIR) || (ac == Action.LEFT_CLICK_BLOCK)) {
            putLeft(e.getPlayer().getName().toLowerCase());
        } else if (((ac == Action.RIGHT_CLICK_AIR) || (ac == Action.RIGHT_CLICK_BLOCK))) {
            putRight(e.getPlayer().getName().toLowerCase());
        }
    }
}
