package com.locydragon.mlgrush.commands;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.AreaLoader;
import com.locydragon.mlgrush.core.GameArea;
import com.locydragon.mlgrush.core.RushInstances;
import com.locydragon.mlgrush.listeners.BedSetter;
import com.locydragon.mlgrush.utils.SetBedEnum;
import com.locydragon.mlgrush.utils.SetBedState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author LocyDragon
 * @version V1.0
 * 游戏设置/配置-总指令类
 */

public class RushCmdHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "只有玩家才能使用这个指令……");
            return false;
        }
        if (args.length <= 0) {
            sender.sendMessage(RushInstances.RUSH_PREFIX + "请输入正确的参数!");
            return false;
        }
        Player player = (Player)sender;
        if (args[0].equalsIgnoreCase("lobby") && player.isOp()) {
            RushInstances.RUSH_LOBBY = player.getLocation();
            MLGRush.rushConfig.set("Lobby", player.getLocation());
            MLGRush.saveSettings();
            sender.sendMessage(RushInstances.RUSH_PREFIX + "已经设置大厅了!");
        } else if (args[0].equalsIgnoreCase("info") && player.isOp()) {
            String areaName = args[1];
            GameArea area = AreaLoader.getArea(areaName);
            if (area == null) {
                sender.sendMessage(RushInstances.RUSH_PREFIX + "找不到该区域!");
            } else {
                if (area.MONITOR != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "观察者: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "观察者: ×");
                }
                if (area.POS_A != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标①: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标①: ×");
                }
                if (area.POS_B != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标②: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标②: ×");
                }
                if (area.SPAWN_A != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点①: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点①: ×");
                }
                if (area.SPAWN_B != null) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点②: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点②: ×");
                }
                if (area.bedBlock_A.isOK()) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "床①: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "床①: ×");
                }
                if (area.bedBlock_B.isOK()) {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "床②: √");
                } else {
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "床②: ×");
                }
                sender.sendMessage(RushInstances.RUSH_PREFIX + "这是 " + area.areaName + " 的所有信息.");
            }
        } else if (args[0].equalsIgnoreCase("set") && player.isOp()) {
            String areaName = args[1];
            String job = args[2];
            if (!AreaLoader.containsArea(areaName)) {
                sender.sendMessage(RushInstances.RUSH_PREFIX + "不存在这个区域");
                return false;
            }
            GameArea area = AreaLoader.getArea(areaName);
            switch (job) {
                case "MONITOR":
                    area.MONITOR = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "观察者: √");
                    break;
                case "POS_A" :
                    area.POS_A = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标①: √");
                    break;
                case "POS_B" :
                    area.POS_B = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "坐标②: √");
                    break;
                case "SPAWN_A" :
                    area.SPAWN_A = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点①: √");
                    break;
                case "SPAWN_B" :
                    area.SPAWN_B = player.getLocation();
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "出生点②: √");
                    break;
                case "BED_A_HEAD" :
                    BedSetter.states.put(player.getName().toLowerCase()
                            , new SetBedState(SetBedEnum.A_HEAD, areaName));
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "请破坏一个 床头 来设置!");
                    break;
                case "BED_A_BUTTOM" :
                    BedSetter.states.put(player.getName().toLowerCase()
                            , new SetBedState(SetBedEnum.A_BUTTOM, areaName));
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "请破坏一个 床尾 来设置!");
                    break;
                case "BED_B_HEAD" :
                    BedSetter.states.put(player.getName().toLowerCase()
                            , new SetBedState(SetBedEnum.B_HEAD, areaName));
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "请破坏一个 床头 来设置!");
                    break;
                case "BED_B_BUTTOM" :
                    BedSetter.states.put(player.getName().toLowerCase()
                            , new SetBedState(SetBedEnum.B_BUTTOM, areaName));
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "请破坏一个 床尾 来设置!");
                    break;
                default :
                    sender.sendMessage(RushInstances.RUSH_PREFIX + "参数错误!");
                    break;
            }
            area.saveArea();
        } else if (args[0].equalsIgnoreCase("create") && player.isOp()) {
            if (args[1] == null) {
                return false;
            }
            if (AreaLoader.createArea(args[1])) {
                sender.sendMessage(RushInstances.RUSH_PREFIX + "创建成功!");
            } else {
                sender.sendMessage(RushInstances.RUSH_PREFIX + "这个区域名已经被使用了!");
            }
        } else if (args[0].equalsIgnoreCase("shutdown") && player.isOp()) {
            for (Player ps : Bukkit.getOnlinePlayers()) {
                ps.kickPlayer("");
            }
            Bukkit.getScheduler().runTaskLater(MLGRush.instance, () -> {
                Bukkit.shutdown();
            }, 20);
        }
        return false;
    }
}
