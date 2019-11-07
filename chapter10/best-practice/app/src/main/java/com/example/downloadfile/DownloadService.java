package com.example.downloadfile;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.example.downloadfile.Utilities.DownloadUtils.DownloadCallback;

import java.io.File;

import static com.example.downloadfile.Utilities.DownloadUtils.ID_PROGRESS_NOTIFICATION;
import static com.example.downloadfile.Utilities.DownloadUtils.PROGRESS_MAX;
import static com.example.downloadfile.Utilities.DownloadUtils.PROGRESS_NONE;
import static com.example.downloadfile.Utilities.DownloadUtils.PROGRESS_START;

public class DownloadService extends Service {

    private String fileUrlString;
    private DownloadTask mDownloadTask;
    private DownloadBinder mBinder = new DownloadBinder();


    //实现DownloadCallback的五个抽象方法的具体功能，用以实例化DownloadTask
    private DownloadCallback downloadListener = new DownloadCallback() {
        @Override
        public void onProgress(int progress) {
            NotificationManagerCompat.from(getApplicationContext()).notify(ID_PROGRESS_NOTIFICATION, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            mDownloadTask = null;
            //停止前台服务，同时移除通知
            stopForeground(true);
            //显示下载成功的通知
            NotificationManagerCompat.from(getApplicationContext()).notify(ID_PROGRESS_NOTIFICATION, getNotification("Download Success", PROGRESS_NONE));
        }

        @Override
        public void onFailure() {
            mDownloadTask = null;
            //停止前台服务，同时移除通知
            stopForeground(true);
            //显示下载失败的通知
            NotificationManagerCompat.from(getApplicationContext()).notify(ID_PROGRESS_NOTIFICATION, getNotification("Download Failed", PROGRESS_NONE));
        }

        @Override
        public void onPause() {
            mDownloadTask = null;
            NotificationManagerCompat.from(getApplicationContext()).notify(ID_PROGRESS_NOTIFICATION, getNotification("Download Paused", PROGRESS_NONE));
        }

        @Override
        public void onCancel() {
            mDownloadTask = null;
            //停止前台服务，同时移除通知
            stopForeground(true);
            NotificationManagerCompat.from(getApplicationContext()).notify(ID_PROGRESS_NOTIFICATION, getNotification("Download Canceled", PROGRESS_NONE));
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Notification getNotification(String title, int progress) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_file_download_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_file_download_24dp))
                .setAutoCancel(true);
        if (progress >= 0) {
            notification.setContentText(progress + "%");
            notification.setProgress(PROGRESS_MAX, progress, false);
        }
        return notification.build();
    }

    //新建类继承自Binder，用以创建IBinder
    class DownloadBinder extends Binder {

        void startDownload(String urlString) {
            fileUrlString = urlString;
            if (mDownloadTask == null) {
                mDownloadTask = new DownloadTask(downloadListener);
                mDownloadTask.execute(fileUrlString);
                //启动前台服务
                startForeground(ID_PROGRESS_NOTIFICATION, getNotification("Downloading...", PROGRESS_START));
            }
        }

        void pauseDownload() {
            if (mDownloadTask != null)
                mDownloadTask.pauseDownload();
        }

        void cancelDownload() {
            if (mDownloadTask != null)
                mDownloadTask.cancelDownload();
            else if (TextUtils.isEmpty(fileUrlString)) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() +
                        fileUrlString.substring(fileUrlString.lastIndexOf("/")));
                if (file.exists())
                    file.delete();
                NotificationManagerCompat.from(getApplicationContext()).cancel(ID_PROGRESS_NOTIFICATION);
                stopForeground(true);
            }
        }
    }
}
