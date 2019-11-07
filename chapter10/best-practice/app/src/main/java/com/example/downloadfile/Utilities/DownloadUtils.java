package com.example.downloadfile.Utilities;

public class DownloadUtils {

    public static final int BYTE_SIZE = 1024;
    public static final int PROGRESS_START = 0;
    public static final int PROGRESS_MAX = 100;
    public static final int PROGRESS_NONE = -1;
    public static final int ID_PROGRESS_NOTIFICATION = 1;

    public interface DownloadCallback {
        void onProgress(int progress);

        void onSuccess();

        void onFailure();

        void onPause();

        void onCancel();
    }

}
