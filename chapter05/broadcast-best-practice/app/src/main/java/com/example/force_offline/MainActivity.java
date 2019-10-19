package com.example.force_offline;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.force_offline.Utilities.BroadcastUtils;
import com.example.force_offline.broadcast.ForceOfflineBroadcast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ForceOffline(View view) {
        Intent intent=new Intent(BroadcastUtils.ACTION_FORCE_OFFLINE);
        sendBroadcast(intent);
    }
}
