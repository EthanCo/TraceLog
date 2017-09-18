package com.ethanco.tracelog.logs;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.ethanco.logbase.ICommonLog;
import com.ethanco.tracelog.abs.IInit;
import com.ethanco.tracelog.utils.Util;

import java.io.File;
import java.util.Date;

/**
 * @Description 日志保存至本地文件
 * Created by EthanCo on 2016/10/12.
 */

public class LocalRecordLog implements ICommonLog, IInit {

    private Context context = null;
    private final String fileSuffix = ".log";
    private String fileName = "TraceLog";
    private String folder = "TraceLog";
    private int maxCacheSize = 1014 * 1024 * 10; //以B为单位
    private boolean useCacheDir = true;
    private String path = null;
    private long lastClearTime = 0;

    /**
     * @param context
     * @param maxCacheSize 日志文件最大缓存，以B为单位
     * @param folder       日志保存文件夹，如果不设置，默认为TraceLog
     * @param fileName     日志文件文件名(不包括后缀)，如果不设置，默认为TraceLog
     * @param useCacheDir  使用缓存文件夹，true表示存储在/data/data/cache文件夹下，false表示存储在外部存储根目录下
     */
    public LocalRecordLog(Context context, int maxCacheSize, String folder, String fileName, boolean useCacheDir) {
        this.context = context;
        this.fileName = fileName;
        this.folder = folder;
        this.maxCacheSize = maxCacheSize;
        this.useCacheDir = useCacheDir;
    }

    public LocalRecordLog(Context context, int maxCacheSize, String folder, String fileName) {
        this.context = context;
        this.maxCacheSize = maxCacheSize;
        this.folder = folder;
        this.fileName = fileName;
    }

    public LocalRecordLog(Context context, String folder, String fileName) {
        this.context = context;
        this.fileName = fileName;
        this.folder = folder;
    }

    public LocalRecordLog(Context context, int maxCacheSize) {
        this.context = context;
        this.maxCacheSize = maxCacheSize;
    }

    public LocalRecordLog(Context context) {
        this.context = context;
    }

    /**
     * @param context
     * @param path    路径，包括文件名和文件名后缀
     */
    public LocalRecordLog(Context context, String path) {
        this.context = context;
        this.path = path;
    }

    @Override
    public void init() {
        if (TextUtils.isEmpty(path)) {
            String filePath = generateDir(context, folder);
            this.path = generatePath(filePath);
        }
        clearCacheIfTimeOut(path);
    }

    @Override
    public void v(String tag, String message) {
        saveLogToFile(tag, message);
    }

    @Override
    public void d(String tag, String message) {
        saveLogToFile(tag, message);
    }

    @Override
    public void i(String tag, String message) {
        saveLogToFile(tag, message);
    }

    @Override
    public void w(String tag, String message) {
        saveLogToFile(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        saveLogToFile(tag, message);
    }

    @Override
    public void postCatchedException(Exception e) {
        saveLogToFile(">> Exception <<", e.getMessage());
    }

    private void preSaveLogToFile() {
        clearCacheIfTimeOut(path);
    }

    private void saveLogToFile(String tag, String message) {
        preSaveLogToFile();

        String time = Util.date2Str(new Date());
        String str = time + " " + tag + ": " + message + "\r\n";

        Util.saveStrToFile(str, path, true);
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

    //如果超过时间，进行清除缓存
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
