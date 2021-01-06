package com.locydragon.mlgrush.commands;

import com.locydragon.mlgrush.core.RushInstances;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

/**
 * @author LocyDragon
 * @version V1.0
 * Cps检测指令使用类
 */

public class CpsHandler implements CommandExecutor {
    public static HashMap<String,String> map = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length <= 0) {
            sender.sendMessage(RushInstances.RUSH_PREFIX + "使用/cps <玩家ID> 来检测玩家CPS!");
        } else {
            map.put(sender.getName(), args[0]);
            sender.sendMessage(RushInstances.RUSH_PREFIX + "您现在在观测: " + args[0] + " 的cps!");
        }
        return false;
    }
}
