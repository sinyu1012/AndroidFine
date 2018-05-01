package com.ycit.parking.common;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sinyu on 2017/11/4.
 */

public class MapSingle {
    private  static Map<String,String> maps=new HashMap<String,String>();//封装路径信息
    private  static Map<String,String> mapsEvent=new HashMap<String,String>();//封装路径信息
    public static  void init(){
        maps.put("H030","63/36");
        maps.put("G128","113/181");

    }
    public static  Map<String,String> getMaps(){
        init();
        return maps;
    }
    public static   Map<String,String> getMapsEvent(){
        init();
        return mapsEvent;
    }
}
