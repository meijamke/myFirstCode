package com.example.broadcast.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiveOrderedBroadcast02 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Oh no, my priority is lower than ReceiveOrderedBroadcast01, and the broadcast is aborted by ReceiveOrderedBroadcast01, I can't get the broadcast", Toast.LENGTH_LONG).show();
    }
}
