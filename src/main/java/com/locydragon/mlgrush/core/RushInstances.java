package com.locydragon.mlgrush.core;

import com.locydragon.mlgrush.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * @author LocyDragon
 * @version V1.0
 * 一些常量
 */

public class RushInstances {
    public static String RUSH_PREFIX = "§7[§d§l§m§n☆MLGRush☆§7] ";
    public static String APPLE_PREFIX = "§7[§d§l§m§n☆MLGRush☆§7] ";
    public static String BROAD_CAST = "§4§l→§b ☆MLGRush☆ §7| §e☆Rush New Century☆ §4§l←";
    public static Location RUSH_LOBBY = null;
    public static ItemStack MATCHING_ITEM = null;
    public static ItemStack WATCHER_ITEM = null;
    public static ItemStack QUIT = null;
    public static ItemStack STICK = null;
    public static ItemStack GLASS = null;
    public static ItemStack AXE = null;

    /**
     * 预加载常量(在onEnable被调用)
     */

    public static void preLoad() {
        MATCHING_ITEM = new ItemBuilder(Material.GOLD_AXE)
                .setDisplayName("§b§l→右键——即刻匹配←").addEnchantment(Enchantment.KNOCKBACK, 5).build();
        WATCHER_ITEM = new ItemBuilder(Material.BED)
                .setDisplayName("§a§l→即刻观战←").addEnchantment(Enchantment.ARROW_FIRE,5).build();
        QUIT = new ItemBuilder(Material.PAPER)
                .setDisplayName("§c§l→取消匹配←").addEnchantment(Enchantment.ARROW_FIRE,5).build();
        STICK = new ItemBuilder(Material.STICK)
                .setDisplayName("§f击退棒").addEnchantment(Enchantment.KNOCKBACK, 1).build();
        GLASS = new ItemBuilder(Material.HARD_CLAY)
                .setDisplayName("§f方块").addEnchantment(Enchantment.ARROW_FIRE, 1)
                .setAmount(64).build();
        AXE = new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setDisplayName("§f挖掘").addEnchantment(Enchantment.DIG_SPEED, 5)
                .setAmount(1).build();
    }
}
