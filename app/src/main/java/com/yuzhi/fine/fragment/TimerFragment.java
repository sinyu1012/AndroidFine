package com.yuzhi.fine.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.j256.ormlite.stmt.query.In;
import com.yuzhi.fine.R;
import com.yuzhi.fine.model.TimeService;
import com.yuzhi.fine.utils.MyContant;
import com.yuzhi.fine.utils.SharedPreferencesUtil;
import com.yuzhi.fine.utils.StringUtils;

/**
 * Created by Sinyu on 2017/10/25.
 */

public class TimerFragment extends Fragment{
    public static String TIME_CHANGED_ACTION = "com.yy.time.TIME_CHANGED_ACTION";
    public static TextView tv_time;
    public static Button btn_start;
    private SharedPreferencesUtil util;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        initView(view);
        return view;
    }
    private void initView(View view){
        tv_time= (TextView) view.findViewById(R.id.tv_time);
        btn_start= (Button) view.findViewById(R.id.btn_start);
        util=new SharedPreferencesUtil(TimerFragment.this.getActivity());
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_start.getText().equals("开始计时")){
                    btn_start.setText("结束计时");
                    util.saveString(MyContant.STARTTIME, StringUtils.gettime());
                    intent=new Intent(TimerFragment.this.getActivity(), TimeService.class);
                    TimerFragment.this.getActivity().startService(intent);
                }else {
                    btn_start.setText("开始计时");
                    TimerFragment.this.getActivity().stopService(intent);
                }
            }
        });

    }

}
