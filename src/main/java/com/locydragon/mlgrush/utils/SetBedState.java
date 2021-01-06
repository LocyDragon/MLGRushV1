package com.locydragon.mlgrush.utils;

/**
 * @author LocyDragon
 * @version V1.0
 * 床方块信息
 */

public class SetBedState {
    public SetBedEnum bedState;
    public String areaName;
    public SetBedState(SetBedEnum bedState, String areaName) {
        this.bedState = bedState;
        this.areaName = areaName;
    }
}
