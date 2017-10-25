package com.yuzhi.fine.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yuzhi.fine.fragment.TimerFragment;

/**
 * Created by Sinyu on 2017/10/25.
 */

public class UITimeReceiver extends BroadcastReceiver {
    private TimerFragment dUIActivity = new TimerFragment();
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(TimerFragment.TIME_CHANGED_ACTION.equals(action)){
            String strtime = intent.getStringExtra("time");
            //此处实现不够优雅，为了在UITimeReceiver中使用DynamicUIActivity中的TextView组件time，而将其设置为public类型，
            //更好的实现是将UITimeReceiver作为DynamicUIActivity的内部类
            dUIActivity.tv_time.setText(strtime);
        }
    }
}