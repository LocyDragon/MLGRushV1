package com.locydragon.mlgrush.handlers;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.listeners.*;
import org.bukkit.Bukkit;

/**
 * @author LocyDragon
 * @version V1.0
 * 注册事件的玩意儿
 */

public class ListenerRegister {
    public static void register() {
        Bukkit.getPluginManager().registerEvents(new LobbySendListener(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new LobbyProtecter(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new ItemProtecter(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new LobbyHitSound(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new BedSetter(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new PlayerUseLobbyItem(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new AllowMove(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new QuitListener(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new OutOfArea(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new BlockStorey(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new BedScorer(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new AsyncChat(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new AllowHit(), MLGRush.instance);
        Bukkit.getPluginManager().registerEvents(new AllowChange(), MLGRush.instance);
    }
}
