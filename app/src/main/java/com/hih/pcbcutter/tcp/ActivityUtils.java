package com.hih.pcbcutter.tcp;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likeye on 2020/9/22 13:49.
 **/
class ActivityUtils{

    private ActivityUtils() {
    }

    private static final List<Activity> activityStack = new ArrayList<>();

    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if ( activityStack.get(i)!=null) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
