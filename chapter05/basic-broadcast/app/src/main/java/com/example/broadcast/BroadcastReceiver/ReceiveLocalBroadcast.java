package com.example.broadcast.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.broadcast.data.BroadcastIntentData;

public class ReceiveLocalBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String broadcast = intent.getStringExtra(BroadcastIntentData.KEY_MY_LOCAL_BROADCAST);
        Toast.makeText(context, "Yeah! I have received it, what you send is as following:\n" + broadcast, Toast.LENGTH_LONG).show();
    }
}
