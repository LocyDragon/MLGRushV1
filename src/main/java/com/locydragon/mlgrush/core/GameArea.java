package com.locydragon.mlgrush.core;

import com.locydragon.mlgrush.MLGRush;
import com.locydragon.mlgrush.core.data.MapReseter;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LocyDragon
 * @version V1.0
 * 游戏区域控制类
 */

public class GameArea {
    public Location POS_A = null;
    public Location POS_B = null;
    public Location MONITOR = null;
    public Location SPAWN_A = null;
    public Location SPAWN_B = null;
    public BedBlock bedBlock_A = new BedBlock();
    public BedBlock bedBlock_B = new BedBlock();
    public String areaName;
    public boolean isPlaying = false;
    public Player A = null;;
    public Player B = null;
    public List<Location> blockSetLists = new ArrayList<>();
    public int a_Score = 0;
    public int b_Score = 0;

    /**
     * 重置该地图(这个方法不该被外部插件调用)
     */
    public void re() {
        A.teleport(SPAWN_A);
        B.teleport(SPAWN_B);
        MapReseter.reset(this);
    }

    /**
     * 判断某个坐标是否有 玩家放置的 方块并移除
     * @param location 坐标
     * @return 是否存在玩家放置的方块
     */

    public boolean containsBlockAndRemove(@NonNull Location location) {
        for (int i = 0; i < blockSetLists.size(); i++) {
            Location loc = blockSetLists.get(i);
            if (loc.getBlockX() == location.getBlockX() && loc.getBlockY() == location.getBlockY()
                    && loc.getBlockZ() == location.getBlockZ()
            && loc.getWorld().getName().equals(location.getWorld().getName())) {
                blockSetLists.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * 载入区域(该方法不改被外部插件调用)
     * @param name 区域名
     */

    public GameArea(@NonNull String name) {
        areaName = name;
        if (MLGRush.rushConfig.contains("RushMap." + name)) {
            POS_A = (Location) MLGRush.rushConfig.get("RushMap." + name + ".POS_A");
            POS_B = (Location) MLGRush.rushConfig.get("RushMap." + name + ".POS_B");
            MONITOR = (Location) MLGRush.rushConfig.get("RushMap." + name + ".MONITOR");
            SPAWN_A = (Location) MLGRush.rushConfig.get("RushMap." + name + ".SPAWN_A");
            SPAWN_B = (Location) MLGRush.rushConfig.get("RushMap." + name + ".SPAWN_B");
            bedBlock_A.head = (Location) MLGRush.rushConfig.get("RushMap." + name + ".BEDBLOCK_A.HEAD");
            bedBlock_A.buttom = (Location) MLGRush.rushConfig.get("RushMap." + name + ".BEDBLOCK_A.BUTTOM");
            bedBlock_B.head = (Location) MLGRush.rushConfig.get("RushMap." + name + ".BEDBLOCK_B.HEAD");
            bedBlock_B.buttom = (Location) MLGRush.rushConfig.get("RushMap." + name + ".BEDBLOCK_B.BUTTOM");
        }
    }

    /**
     * 保存区域设置信息
     */

    public void saveArea() {
        if (this.POS_A != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".POS_A", this.POS_A);
        }
        if (this.POS_B != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".POS_B", this.POS_B);
        }
        if (this.MONITOR != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".MONITOR", this.MONITOR);
        }
        if (this.SPAWN_A != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".SPAWN_A", this.SPAWN_A);
        }
        if (this.SPAWN_B != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".SPAWN_B", this.SPAWN_B);
        }
        if (this.bedBlock_A.head != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".BEDBLOCK_A.HEAD", this.bedBlock_A.head);
        }
        if (this.bedBlock_A.buttom != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".BEDBLOCK_A.BUTTOM", this.bedBlock_A.buttom);
        }
        if (this.bedBlock_B.head != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".BEDBLOCK_B.HEAD", this.bedBlock_B.head);
        }
        if (this.bedBlock_B.buttom != null) {
            MLGRush.rushConfig.set("RushMap." + this.areaName + ".BEDBLOCK_B.BUTTOM", this.bedBlock_B.buttom);
        }
        MLGRush.saveSettings();
    }

    /**
     * 判断某个坐标是否在游戏区域内
     * @param loc 坐标
     * @return 是否存在
     */

    public boolean isInArea(Location loc) {
        int xMax = POS_A.getBlockX();
        int xMin = POS_B.getBlockX();
        if (xMax < xMin) {
            xMax = POS_B.getBlockX();
            xMin = POS_A.getBlockX();
        }
        int YMax = POS_A.getBlockY();
        int YMin = POS_B.getBlockY();
        if (YMax < YMin) {
            YMax = POS_B.getBlockY();
            YMin = POS_A.getBlockY();
        }
        int ZMax = POS_A.getBlockZ();
        int ZMin = POS_B.getBlockZ();
        if (ZMax < ZMin) {
            ZMax = POS_B.getBlockZ();
            ZMin = POS_A.getBlockZ();
        }
        return xMax > loc.getBlockX() && xMin < loc.getBlockX() &&
                YMax > loc.getBlockY() && YMin < loc.getBlockY() &&
                ZMax > loc.getBlockZ() && ZMin < loc.getBlockZ();
    }

    /**
     * 判断所有的设置是否完成，可以进入玩家
     * @return 是否完成
     */

    public boolean isOK() {
        return POS_A != null && POS_B != null &&
                MONITOR != null && SPAWN_A != null && SPAWN_B != null
                && bedBlock_A.isOK() && bedBlock_B.isOK();
    }

    public void setSPAWN_A(@NonNull Location SPAWN_A) {
        this.SPAWN_A = SPAWN_A;
    }

    public void setSPAWN_B(@NonNull Location SPAWN_B) {
        this.SPAWN_B = SPAWN_B;
    }

    public void setBedBlock_A(@NonNull BedBlock bedBlock_A) {
        this.bedBlock_A = bedBlock_A;
    }

    public void setBedBlock_B(@NonNull BedBlock bedBlock_B) {
        this.bedBlock_B = bedBlock_B;
    }

    public void setMONITOR(@NonNull Location MONITOR) {
        this.MONITOR = MONITOR;
    }

    public void setPOS_A(@NonNull Location POS_A) {
        this.POS_A = POS_A;
    }

    public void setPOS_B(@NonNull Location POS_B) {
        this.POS_B = POS_B;
    }

    public BedBlock getBedBlock_A() {
        return bedBlock_A;
    }

    public BedBlock getBedBlock_B() {
        return bedBlock_B;
    }

    public Location getMONITOR() {
        return MONITOR;
    }

    public Location getPOS_A() {
        return POS_A;
    }

    public Location getPOS_B() {
        return POS_B;
    }

    public Location getSPAWN_A() {
        return SPAWN_A;
    }

    public Location getSPAWN_B() {
        return SPAWN_B;
    }
}
