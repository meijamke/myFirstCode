package com.example.force_offline.Utilities;

import android.content.Context;
import android.content.Intent;

import com.example.force_offline.LoginActivity;
import com.example.force_offline.MainActivity;

public class ExplicitIntentUtils {

    public static void intentToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void intentToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
