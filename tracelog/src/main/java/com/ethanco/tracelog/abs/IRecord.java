package com.ethanco.tracelog.abs;

/**
 * @Description 记录
 * Created by EthanCo on 2016/11/1.
 */

public interface IRecord {
    //设置最大缓存 以B为单位
    void setMaxFileCacheSize(int maxCacheSize);

    void setFileName(String fileName);

    void setFolder(String folder);
}
