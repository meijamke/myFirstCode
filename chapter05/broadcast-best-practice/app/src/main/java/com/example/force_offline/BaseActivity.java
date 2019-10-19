package com.example.force_offline;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.force_offline.Utilities.ActivityCollectorUtils;
import com.example.force_offline.Utilities.BroadcastUtils;
import com.example.force_offline.broadcast.ForceOfflineBroadcast;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineBroadcast forceOfflineBroadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollectorUtils.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtils.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        forceOfflineBroadcast = new ForceOfflineBroadcast();
        IntentFilter intentFilter = new IntentFilter(BroadcastUtils.ACTION_FORCE_OFFLINE);
        registerReceiver(forceOfflineBroadcast, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (forceOfflineBroadcast != null) {
            unregisterReceiver(forceOfflineBroadcast);
            forceOfflineBroadcast = null;
        }
    }
}
