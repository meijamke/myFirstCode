package com.example.downloadfile;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.downloadfile.DownloadService.DownloadBinder;
import com.example.downloadfile.Utilities.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    private DownloadBinder mBinder;
    private boolean mBound;
    //监控与服务的连接
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (DownloadBinder) service;
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请写SD卡的危险权限
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE);
        }

        //启动和绑定服务
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    //重写申请权限的回调方法onRequestPermissionsResult，根据申请结果处理事务
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You deny the write external storage permission", Toast.LENGTH_LONG).show();
                    finish();
                }
        }
    }


    //按钮点击事件
    //开始下载
    public void startDownload(View view) {
        if (mBinder != null)
            mBinder.startDownload("http://dldir1.qq.com/weixin/android/weixin708android1540.apk");
    }

    //暂停下载
    public void pauseDownload(View view) {
        if (mBinder != null) {
            mBinder.pauseDownload();
        }
    }

    //取消下载
    public void cancelDownload(View view) {
        if (mBinder != null) {
            mBinder.cancelDownload();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        unbindService(connection);
    }
}
