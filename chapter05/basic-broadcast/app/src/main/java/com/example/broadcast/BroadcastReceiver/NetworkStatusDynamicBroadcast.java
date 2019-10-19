package com.example.broadcast.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkStatusDynamicBroadcast extends BroadcastReceiver {

    private Toast mToast;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (mToast != null)
            mToast.cancel();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            mToast = Toast.makeText(context, "network is connected", Toast.LENGTH_LONG);
        else
            mToast = Toast.makeText(context, "network is unconnected", Toast.LENGTH_LONG);
        mToast.show();
    }
}
