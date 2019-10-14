package com.example.exercise_chapter02;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector extends AppCompatActivity {

    static List<Activity> activities= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        //结束活动列表中的所有活动
        for (Activity activity:activities)
            if (!activity.isFinishing())
                activity.finish();
        //杀掉当前应用的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
