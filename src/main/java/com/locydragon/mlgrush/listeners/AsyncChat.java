package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.MLGRush;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author LocyDragon
 * @version V1.0
 * 修改玩家聊天格式
 */

public class AsyncChat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setFormat("§f[§d" + MLGRush.serverName + "§f][§e☆§b" + e.getPlayer().getName()
                + "§e☆§7>>> " + e.getMessage());
    }
}
