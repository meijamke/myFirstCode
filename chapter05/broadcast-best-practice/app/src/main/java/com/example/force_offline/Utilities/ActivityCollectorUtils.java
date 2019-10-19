package com.example.force_offline.Utilities;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollectorUtils {

    private static List<Activity> mAllActivities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        mAllActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mAllActivities.remove(activity);
        activity.finish();
    }

    public static void finishiAll() {
        for (Activity activity : mAllActivities)
            if (activity != null)
                activity.finish();
    }
}
