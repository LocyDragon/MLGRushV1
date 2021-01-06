package com.locydragon.mlgrush.listeners;

import com.locydragon.mlgrush.core.AreaLoader;
import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.utils.SetBedState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;

/**
 * @author LocyDragon
 * @version V1.0
 * 服主设置床的坐标的类
 */

public class BedSetter implements Listener {
    public static HashMap<String, SetBedState> states = new HashMap<>();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (states.containsKey(e.getPlayer().getName().toLowerCase())) {
            e.setCancelled(true);
            SetBedState state = states.get(e.getPlayer().getName().toLowerCase());
            states.remove(e.getPlayer().getName().toLowerCase());
            GameArea area = AreaLoader.getArea(state.areaName);
            if (area == null) {
                e.getPlayer().sendMessage(RushInstances.RUSH_PREFIX + "区域不存在.");
            } else {
                switch (state.bedState) {
                    case A_HEAD:
                        area.bedBlock_A.head = e.getBlock().getLocation();
                        break;
                    case A_BUTTOM:
                        area.bedBlock_A.buttom = e.getBlock().getLocation();
                        break;
                    case B_HEAD:
                        area.bedBlock_B.head = e.getBlock().getLocation();
                        break;
                    case B_BUTTOM:
                        area.bedBlock_B.buttom = e.getBlock().getLocation();
                        break;
                    default:
                        break;
                }
                area.saveArea();
                e.getPlayer().sendMessage(RushInstances.RUSH_PREFIX + "设置成功!");
            }
        }
    }
}
