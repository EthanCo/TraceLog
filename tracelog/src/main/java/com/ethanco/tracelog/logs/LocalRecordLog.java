package com.ethanco.tracelog.logs;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.ethanco.tracelog.abs.IInit;
import com.ethanco.tracelog.abs.ILog;
import com.ethanco.tracelog.abs.IRecord;
import com.ethanco.tracelog.utils.Util;

import java.io.File;
import java.util.Date;

/**
 * @Description 日志保存至本地文件
 * Created by EthanCo on 2016/10/12.
 */

public class LocalRecordLog implements ILog, IRecord, IInit {

    private Context context = null;
    private final String fileSuffix = ".log";
    private String fileName = "TraceLog";
    private String folder = "TraceLog";
    private int maxCacheSize = 1014 * 1024 * 10; //以B为单位
    private String path;
    private long lastClearTime = 0;

    @Override
    public void init(Application application) {
        this.context = application;
        //this.filePath = getFileDir(context, folder);
        String filePath = getDir(context, folder);
        this.path = getPath(filePath);
        clearCacheIfTimeOut(filePath);
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

    private String getPath(String dir) {
        if (TextUtils.isEmpty(path)) {
            path = dir + File.separator + fileName + fileSuffix;
        }
        return path;
    }

    private String getDir(Context context, String folder) {
        String dir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = Environment.getExternalStorageDirectory() + File.separator + folder;
        } else {
            dir = getFileDir(context, folder);
        }
        return dir;
    }

    private String getFileDir(Context context, String folder) {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = context.getExternalFilesDir(folder).getPath();
        } else {
            path = context.getFilesDir().getPath() + File.separator + folder;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return path;
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

    @Override
    public void setMaxFileCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void setFolder(String folder) {
        this.folder = folder;
    }
}
