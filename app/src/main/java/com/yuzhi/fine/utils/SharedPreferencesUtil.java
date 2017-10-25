package com.yuzhi.fine.utils;

import android.content.Context;

import android.content.SharedPreferences;

/**
 * Created by Sinyu on 2017/10/25.
 */

public class SharedPreferencesUtil {


    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharedPreferencesUtil(Context context) {
        sp = context.getSharedPreferences(MyContant.SPNAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public boolean saveString(String key, String value) {
        editor.putString(key, value);
        // 亿万不要忘了加commit呐~~~！！！！
        return editor.commit();
    }

    public String readString(String key) {
        String str = null;
        str = sp.getString(key, null);
        return str;
    }
}
