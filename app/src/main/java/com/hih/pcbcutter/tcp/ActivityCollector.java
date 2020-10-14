package com.hih.pcbcutter.tcp;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likeye on 2020/9/22 14:13.
 **/
public class ActivityCollector {

    private static final List<Activity> activitys = new ArrayList<>();

    /**
     * 向List中添加一个活动
     *
     * @param activity 活动
     */
    public static void addActivity(Activity activity) {

        activitys.add(activity);
    }

    /**
     * 从List中移除活动
     *
     * @param activity 活动
     */
    public static void removeActivity(Activity activity) {

        activitys.remove(activity);
    }

    /**
     * 将List中存储的活动全部销毁掉
     */
    public static void finishAll() {

        for (Activity activity : activitys) {

            if (!activity.isFinishing()) {

                activity.finish();
            }
        }
    }
}