package com.locydragon.mlgrush.core;

import org.bukkit.Location;

/**
 * @author LocyDragon
 * @version V1.0
 * 介于床方块有两个，通过BedBlock类来控制
 */

public class BedBlock {
    public Location head = null;
    public Location buttom = null;

    public boolean isOK () { return head != null && buttom != null; }
}
