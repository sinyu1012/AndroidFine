package com.ycit.parking;
import android.app.Application;
import android.test.ApplicationTestCase;

import com.baidu.mapapi.SDKInitializer;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        //初始化百度地图SDK
        SDKInitializer.initialize(getApplication());
    }
}