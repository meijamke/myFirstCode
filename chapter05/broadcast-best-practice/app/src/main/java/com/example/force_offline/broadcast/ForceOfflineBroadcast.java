package com.example.force_offline.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.example.force_offline.LoginActivity;
import com.example.force_offline.Utilities.ActivityCollectorUtils;
import com.example.force_offline.Utilities.ExplicitIntentUtils;

public class ForceOfflineBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle("Warning")
                .setMessage("You are forced to be offline. Please try to login again.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCollectorUtils.finishiAll();
                        ExplicitIntentUtils.intentToLoginActivity(context);
                    }
                });
        alertDialog.show();
    }
}
