package com.example.broadcast.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.broadcast.data.BroadcastIntentData;

public class ReceiveOrderedBroadcast01 extends BroadcastReceiver {

    private static final String TAG = "ReceiveOrderedBroadcast01";

    @Override
    public void onReceive(Context context, Intent intent) {
        String broadcastData = intent.getStringExtra(BroadcastIntentData.KEY_MY_ORDERED_BROADCAST);
        Toast.makeText(context, "Yeah! I(" + TAG + ") get your broadcast, which is as following:\n+" + broadcastData +
                ". \nSince I set a higher priority in intent-filter tag of manifest file, I am the first one get it, and I don't want others get it, so I call abortBroadcast to abort it", Toast.LENGTH_LONG).show();
        abortBroadcast();
    }
}
