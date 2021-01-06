package com.locydragon.mlgrush.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author LocyDragon
 * @version V1.0
 * ActionBar发送类,别问缩进为什么这么奇怪,问就是很久之前写的
 */

public class ActionBar {
    /**
     * 给玩家发送ActionBar
     * @param user 玩家对象
     * @param msg 消息
     * @return 是否发送成功
     */
  public static boolean sendActionBar(@NonNull Player user, @NonNull String msg) {
    if (msg == null) {
         throw new NullPointerException("Msg cannot be null.");
    }
    msg = ChatColor.translateAlternateColorCodes('&', msg);
    Class chatPacket = getNMSClass("PacketPlayOutChat");
    Class iChatBaseClass = getNMSClass("IChatBaseComponent");
    try {
        Method iChatBaseClass_a = iChatBaseClass.getDeclaredClasses()[0].getMethod("a", new Class[] { String.class });
        iChatBaseClass_a.setAccessible(true);
      try {
            Object iChatBase = iChatBaseClass_a.invoke(null, new Object[] { "{\"text\":\"" + msg + "\"}" });
            Constructor packetConstructor = chatPacket.getDeclaredConstructor(new Class[] { iChatBaseClass, Byte.TYPE });
            packetConstructor.setAccessible(true);
        try {
            Object packet = packetConstructor.newInstance(new Object[] { iChatBase, Byte.valueOf((byte)2) });
            sendPacket(user, packet);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
      } catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
            e.printStackTrace();
      }
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    } catch (SecurityException e) {
        e.printStackTrace();
    }
     return true;
  }

  public static Class<?> getNMSClass(@NonNull String name) {
    String version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    try {
         return Class.forName("net.minecraft.server." + version + "." + name);
    } catch (ClassNotFoundException e) {
         e.printStackTrace();
    }
    return null;
  }

  public static void sendPacket(@NonNull Player player, @NonNull Object packet) {
    try {
        Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
        Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
        playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}