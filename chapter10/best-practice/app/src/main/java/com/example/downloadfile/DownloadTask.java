package com.example.downloadfile;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;

import com.example.downloadfile.Utilities.DownloadUtils.DownloadCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.downloadfile.Utilities.DownloadUtils.BYTE_SIZE;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private static final int STATUS_SUCCESS = 1;
    private static final int STATUS_FAILURE = 2;
    private static final int STATUS_PAUSE = 3;
    private static final int STATUS_CANCEL = 4;

    private boolean isPaused = false;
    private boolean isCanceled = false;

    private int lastProgress = 0;
    private DownloadCallback mDownloadListener;

    DownloadTask(DownloadCallback listener) {
        mDownloadListener = listener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        //已下载文件大小
        long fileDownloadSize = 0;
        //需要下载的文件的大小
        long fileContentLength;
        //下载资源的url
        String urlString = strings[0];
        //读写文件
        InputStream in = null;
        RandomAccessFile randomAccessFile = null;

        //下载位置在SD卡的Download文件夹里，文件名为urlString的最后一个/后面的字符串
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() +
                urlString.substring(urlString.lastIndexOf("/")));
        if (file.exists())
            fileDownloadSize = file.length();
        fileContentLength = getUrlContentLength(urlString);
        if (fileContentLength == 0)
            return STATUS_FAILURE;
        else if (fileDownloadSize == fileContentLength)
            return STATUS_SUCCESS;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //开始访问url并下载
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("RANGE", "bytes=" + fileDownloadSize + "-");
                in = connection.getInputStream();

                randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(fileDownloadSize);

                byte[] b = new byte[BYTE_SIZE];
                int len;
                int total = 0;
                while ((len = in.read(b)) != -1) {
                    if (isPaused)
                        return STATUS_PAUSE;
                    else if (isCanceled)
                        return STATUS_CANCEL;

                    randomAccessFile.write(b, 0, len);
                    total += len;
                    int progress = (int) ((total + fileDownloadSize) * 100 / fileContentLength);
                    mDownloadListener.onProgress(progress);
                }
                connection.disconnect();
                return STATUS_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null)
                        in.close();
                    if (randomAccessFile != null)
                        randomAccessFile.close();
                    if (isCanceled)
                        file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //OkHttp需要Android5.0及以上
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //断点下载，从上一次下载的位置开始
                    .addHeader("RANGE", "bytes=" + fileDownloadSize + "-")
                    .url(urlString)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                in = response.body().byteStream();

                randomAccessFile = new RandomAccessFile(file, "rw");
                //从上一次位置开始下载
                randomAccessFile.seek(fileDownloadSize);
                byte[] b = new byte[BYTE_SIZE];
                int len;
                int total = 0;
                while ((len = in.read(b)) != -1) {

                    if (isPaused)
                        return STATUS_PAUSE;
                    else if (isCanceled)
                        return STATUS_CANCEL;

                    randomAccessFile.write(b, 0, len);
                    total += len;
                    int progress = (int) ((total + fileDownloadSize) * 100 / fileContentLength);
                    publishProgress(progress);
                }
                response.close();
                return STATUS_SUCCESS;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null)
                        in.close();
                    if (randomAccessFile != null)
                        randomAccessFile.close();
                    if (isCanceled)
                        file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return STATUS_FAILURE;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            mDownloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case STATUS_SUCCESS:
                mDownloadListener.onSuccess();
                break;
            case STATUS_FAILURE:
                mDownloadListener.onFailure();
                break;
            case STATUS_PAUSE:
                mDownloadListener.onPause();
                break;
            case STATUS_CANCEL:
                mDownloadListener.onCancel();
                break;
            default:
                throw new IllegalArgumentException("Unknown status");
        }
    }

    /**
     * @param urlString：需要下载的文件的url
     * @return 需要下载的文件的大小
     **/
    private long getUrlContentLength(String urlString) {
        long fileSize = 0;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                fileSize = (long) connection.getContentLength();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //OkHttp需要Android5.0及以上
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(urlString).build();
            try {
                Response response = client.newCall(request).execute();
                fileSize = response.body().contentLength();
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileSize;
    }

    //DownloadTask提供的公共方法
    void pauseDownload() {
        isPaused = true;
    }

    void cancelDownload() {
        isCanceled = true;
    }

}
