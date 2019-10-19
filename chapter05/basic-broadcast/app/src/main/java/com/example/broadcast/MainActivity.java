package com.example.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.broadcast.BroadcastReceiver.NetworkStatusDynamicBroadcast;
import com.example.broadcast.BroadcastReceiver.ReceiveLocalBroadcast;
import com.example.broadcast.data.BroadcastIntentData;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class MainActivity extends AppCompatActivity {

    private NetworkStatusDynamicBroadcast networkStatusDynamicBroadcast;

    private LocalBroadcastManager manager;
    private ReceiveLocalBroadcast receiveLocalBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dynamic broadcast
        IntentFilter intentFilter = new IntentFilter(CONNECTIVITY_ACTION);
        networkStatusDynamicBroadcast = new NetworkStatusDynamicBroadcast();
        registerReceiver(networkStatusDynamicBroadcast, intentFilter);

        //local broadcast
        manager = LocalBroadcastManager.getInstance(this);

        IntentFilter localIntentFilter = new IntentFilter(BroadcastIntentData.ACTION_MY_LOCAL_BROADCAST);
        receiveLocalBroadcast = new ReceiveLocalBroadcast();
        manager.registerReceiver(receiveLocalBroadcast, localIntentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStatusDynamicBroadcast);
        manager.unregisterReceiver(receiveLocalBroadcast);
    }

    public void sendMyStandardBroadcast(View view) {
        Intent intent = new Intent(BroadcastIntentData.ACTION_MY_STANDARD_BROADCAST);
        String myStandardBroadcastMessage = "Hi~I have send a self defied standard broadcast, did you have receive it?";
        intent.putExtra(BroadcastIntentData.KEY_MY_STANDARD_BROADCAST, myStandardBroadcastMessage);
        sendBroadcast(intent);
    }

    public void sendMyOrderedBroadcast(View view) {
        Intent intent = new Intent(BroadcastIntentData.ACTION_MY_ORDERED_BROADCAST);
        String myOrderedBroadcastMessage = "Hi~I have send a self defied ordered broadcast, did you have receive it?";
        intent.putExtra(BroadcastIntentData.KEY_MY_ORDERED_BROADCAST, myOrderedBroadcastMessage);
        sendOrderedBroadcast(intent, null);
    }

    public void sendMyLocalBroadcast(View view) {
        Intent intent = new Intent(BroadcastIntentData.ACTION_MY_LOCAL_BROADCAST);
        String myLocalBroadcastMessage = "Hi~I have send a self defied ordered broadcast, did you have receive it?";
        intent.putExtra(BroadcastIntentData.KEY_MY_LOCAL_BROADCAST, myLocalBroadcastMessage);
        manager.sendBroadcast(intent);
    }
}
