package com.locydragon.mlgrush.core;

import com.locydragon.mlgrush.MLGRush;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author LocyDragon
 * @version V1.0
 * 地图加载类(会在onEnable时被调用)
 */

public class AreaLoader {
    public static HashMap<String,GameArea> gameLauncher = new HashMap<>();

    /**
     * 加载所有已知的地图
     */

    public static void load() {
        if (MLGRush.rushConfig.contains("RushMap")) {
            for (String key : MLGRush.rushConfig.getConfigurationSection("RushMap").getKeys(false)) {
                gameLauncher.put(key, new GameArea(key));
            }
        }
    }

    /**
     * 创建新的空地图模板
     * @param name 地图名称
     * @return 是否成功(如果地图名称已经存在，则返回False)
     */

    public static boolean createArea(@NonNull String name) {
        if (gameLauncher.containsKey(name)) {
            return false;
        }
        gameLauncher.put(name, new GameArea(name));
        return true;
    }

    /**
     * 通过名称(注意大小写)获取某个区域对象
     * @param name 区域名称
     * @return 区域对象
     */

    public static GameArea getArea(@NonNull String name) {
        return gameLauncher.getOrDefault(name, null);
    }

    /**
     * 判断是否已经存在某个区域
     * @param name 区域名称
     * @return 是否存在
     */

    public static boolean containsArea(@NonNull String name) {
        return gameLauncher.containsKey(name);
    }

    /**
     * 判断是否有空的房间供匹配
     * @return 是否有空房间
     */

    public static boolean hasEmptyArea() {
        List<String> okAreas = new ArrayList<>();
        for (String obj : AreaLoader.gameLauncher.keySet()) {
            if (!AreaLoader.gameLauncher.get(obj).isPlaying) {
                okAreas.add(obj);
            }
        }
        if (okAreas.size() == 0) {
            return false;
        }
        return true;
    }
}
