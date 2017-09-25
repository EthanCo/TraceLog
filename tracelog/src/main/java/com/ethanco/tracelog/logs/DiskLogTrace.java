package com.ethanco.tracelog.logs;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ethanco.logbase.Trace;
import com.ethanco.tracelog.utils.Util;

import java.io.File;
import java.util.Date;

/**
 * @Description 日志保存至本地文件
 * Created by EthanCo on 2016/10/12.
 */

public class DiskLogTrace implements Trace {

    private Context context = null;
    private final String fileSuffix = ".log";
    private String fileName = "TraceLog";
    private String folder = "TraceLog";
    private int maxCacheSize = 1024 * 1024 * 10; //以B为单位
    private boolean useCacheDir = true;
    private String path = null;
    private WriteHandler writeHandler;

    /**
     * @param context
     * @param maxCacheSize 日志文件最大缓存，以B为单位
     * @param folder       日志保存文件夹，如果不设置，默认为TraceLog
     * @param fileName     日志文件文件名(不包括后缀)，如果不设置，默认为TraceLog
     * @param useCacheDir  使用缓存文件夹，true表示存储在/data/data/cache文件夹下，false表示存储在外部存储根目录下
     */
    public DiskLogTrace(Context context, int maxCacheSize, String folder, String fileName, boolean useCacheDir) {
        this.context = context;
        this.fileName = fileName;
        this.folder = folder;
        this.maxCacheSize = maxCacheSize;
        this.useCacheDir = useCacheDir;
        init();
    }

    public DiskLogTrace(Context context, int maxCacheSize, String folder, String fileName) {
        this.context = context;
        this.maxCacheSize = maxCacheSize;
        this.folder = folder;
        this.fileName = fileName;
        init();
    }

    public DiskLogTrace(Context context, String folder, String fileName) {
        this.context = context;
        this.fileName = fileName;
        this.folder = folder;
        init();
    }

    public DiskLogTrace(Context context, int maxCacheSize) {
        this.context = context;
        this.maxCacheSize = maxCacheSize;
        init();
    }

    public DiskLogTrace(Context context) {
        this.context = context;
        init();
    }

    /**
     * @param context
     * @param path    路径，包括文件名和文件名后缀
     */
    public DiskLogTrace(Context context, String path) {
        this.context = context;
        this.path = path;
        init();
    }

    private void init() {
        if (TextUtils.isEmpty(path)) {
            String filePath = generateDir(context, folder);
            this.path = generatePath(filePath);
        }

        HandlerThread ht = new HandlerThread("TraceLog." + System.currentTimeMillis());
        ht.start();
        writeHandler = new WriteHandler(ht.getLooper(), path, maxCacheSize);
    }

    private String generatePath(String dir) {
        if (TextUtils.isEmpty(path)) {
            path = dir + File.separator + fileName + fileSuffix;
        }
        return path;
    }

    private String generateDir(Context context, String folder) {
        String dir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (useCacheDir) {
                dir = context.getExternalCacheDir() + File.separator + folder;
            } else {
                dir = Environment.getExternalStorageDirectory() + File.separator + folder;
                //dir = context.getExternalFilesDir(folder).getPath();
            }
        } else {
            dir = context.getCacheDir().getPath() + File.separator + folder;
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return dir;
    }

    private void saveLogToFile(String tag, String message) {
        StringBuilder sb = generateLog(tag, message);
        Message msg = Message.obtain();
        msg.obj = sb.toString();
        writeHandler.sendMessage(msg);
    }

    @NonNull
    private StringBuilder generateLog(String tag, String message) {
        String time = Util.date2Str(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(time);
        sb.append(" ");
        sb.append(tag);
        sb.append(":");
        sb.append(message);
        sb.append("\r\n");
        return sb;
    }

    @Override
    public boolean isLoggable(String tag, int priority) {
        return true;
    }

    @Override
    public void log(int priority, String tag, String message) {
        if (!TextUtils.isEmpty(message)) {
            saveLogToFile(tag, message);
        }
    }

    private static class WriteHandler extends Handler {
        private long lastClearTime = 0;
        private final int maxCacheSize;
        private final String path;

        public WriteHandler(Looper looper, String path, int maxCacheSize) {
            super(looper);
            this.path = path;
            this.maxCacheSize = maxCacheSize;
            clearCacheIfTimeOut(path);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            saveLogToFile((String) msg.obj, path);
        }

        private void saveLogToFile(String message, String path) {
            preSaveLogToFile(path);
            Util.saveStrToFile(message, path, true);
        }

        private void preSaveLogToFile(String path) {
            clearCacheIfTimeOut(path);
        }

        //如果超过时间，判断缓存大小，如果大于则清除日志
        private void clearCacheIfTimeOut(String filePath) {
            if (System.currentTimeMillis() - lastClearTime > 3600000) { //1000*60*60
                clearCache(filePath);
                lastClearTime = System.currentTimeMillis();
            }
        }

        //如果缓存大于指定值，进行清除
        private void clearCache(String filePath) {
            if (!Util.checkDirSize(filePath, maxCacheSize)) {
                Util.deleteAll(filePath);
            }
        }
    }
}
