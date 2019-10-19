package com.example.force_offline.Utilities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollectorUtils{

    private static List<Activity> mAllActivities=new ArrayList<>();

    public static void addActivity(Activity activity){
        mAllActivities.add(activity);
    }

    public static void removeActivity(Activity activity){
        mAllActivities.remove(activity);
        activity.finish();
    }

    public static void finishiAll(){
        for (Activity activity:mAllActivities)
            if (activity!=null)
                activity.finish();
    }
}
