package com.locydragon.mlgrush.core.data;

import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.listeners.AllowMove;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LocyDragon
 * @version V1.0
 * 游戏总控制类
 */

public class InGame {
    public static List<String> inGamePlayers = new ArrayList<>();
    public static HashMap<String,GamePlayer> gamePlayerHashMap = new HashMap<>();

    /**
     * 添加一个 游戏中的玩家
     * 该方法不该被外部插件调用!
     * @param instance 目标玩家
     * @param area 区域
     */
    public static void play(@NonNull Player instance, @NonNull GameArea area) {
        inGamePlayers.add(instance.getName());
        gamePlayerHashMap.put(instance.getName(), new GamePlayer(instance, area));
    }

    /**
     * 获取GamePlayer对象
     * @param instance 目标玩家
     * @return GamePlayer对象
     */

    public static GamePlayer get(@NonNull Player instance) {
        if (!isPlaying(instance)) {
            return null;
        }
        return gamePlayerHashMap.get(instance.getName());
    }

    /**
     * 判断某个玩家是否在游戏中
     * @param instance 目标玩家
     * @return 是否在游玩
     */

    public static boolean isPlaying(@NonNull Player instance) {
        return inGamePlayers.contains(instance.getName());
    }

    /**
     * 使某个玩家退出游戏
     * Tip: 这个退出会使游戏双方均退出游戏
     * @param player 目标玩家
     */

    public static void quit(@NonNull Player player) {
        if (isPlaying(player)) {
            AllowMove.addMove(player, true);
            inGamePlayers.remove(player.getName());
            GamePlayer gamePlayer = gamePlayerHashMap.get(player.getName());
            MapReseter.reset(gamePlayer.area);
            gamePlayer.area.isPlaying = false;
            gamePlayer.area.a_Score = 0;
            gamePlayer.area.b_Score = 0;
            Player targetA = gamePlayer.area.A;
            Player targetB = gamePlayer.area.B;
            if (targetA != null) {
                AllowMove.addMove(targetA, true);
                inGamePlayers.remove(targetA.getName());
                gamePlayerHashMap.get(targetA.getName()).info = false;
                gamePlayerHashMap.remove(targetA.getName());
                targetA.teleport(RushInstances.RUSH_LOBBY);
                targetA.getInventory().clear();
                targetA.getInventory().setItem(0, RushInstances.MATCHING_ITEM);
                targetA.getInventory().setItem(8, RushInstances.WATCHER_ITEM);
                targetA.updateInventory();
            }
            if (targetB != null) {
                AllowMove.addMove(targetB, true);
                inGamePlayers.remove(targetB.getName());
                gamePlayerHashMap.get(targetB.getName()).info = false;
                gamePlayerHashMap.remove(targetB.getName());
                targetB.teleport(RushInstances.RUSH_LOBBY);
                targetB.getInventory().clear();
                targetB.getInventory().setItem(0, RushInstances.MATCHING_ITEM);
                targetB.getInventory().setItem(8, RushInstances.WATCHER_ITEM);
                targetB.updateInventory();
            }
            gamePlayer.area.A = null;
            gamePlayer.area.B = null;
            targetA.setCustomName(targetA.getName());
            targetB.setCustomName(targetB.getName());
        }
    }
}
