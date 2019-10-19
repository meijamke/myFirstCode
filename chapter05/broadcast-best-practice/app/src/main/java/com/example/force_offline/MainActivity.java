package com.example.force_offline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.force_offline.Utilities.BroadcastUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ForceOffline(View view) {
        Intent intent = new Intent(BroadcastUtils.ACTION_FORCE_OFFLINE);
        sendBroadcast(intent);
    }
}
