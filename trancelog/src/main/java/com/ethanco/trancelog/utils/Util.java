package com.ethanco.trancelog.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO
 * Created by EthanCo on 2016/11/1.
 */

public class Util {
    /**
     * 关闭Closeable对象
     *
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断给定的字符串是否为null或者是空的
     *
     * @param string 给定的字符串
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }


    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    /**
     * 删除目录下所有文件和文件夹
     *
     * @param path
     */
    public static void deleteAll(String path) {
        File file = new File(path);
        deleteAll(file);
    }

    /**
     * 删除目录下所有文件和文件夹
     *
     * @param file
     */
    public static void deleteAll(File file) {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteAll(files[i]);
                files[i].delete();
            }

            //如果文件本身就是目录 ，就要删除目录
            if (file.exists())
                file.delete();
        }
    }

    /**
     * 保存文本
     *
     * @param fileName 文件名字
     * @param str      保存字符串
     * @param append   是否累加
     * @return 是否成功
     */
    public static boolean saveStrToFile(String str, String fileName, boolean append) {
        try {
            File file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, append);
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 将字符串以charsetName编码保存到文件中
     *
     * @param str         保存的字符串
     * @param fileName    文件的名字
     * @param charsetName 字符串编码
     * @return 是否保存成功
     */
    public static boolean saveStrToFile(String str, String fileName, String charsetName) {

        if (str == null || "".equals(str)) {
            return false;
        }

        FileOutputStream stream = null;
        try {
            File file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            byte[] b = null;
            if (charsetName != null && !"".equals(charsetName)) {
                b = str.getBytes(charsetName);
            } else {
                b = str.getBytes();
            }

            stream = new FileOutputStream(file);
            stream.write(b, 0, b.length);
            stream.flush();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                    stream = null;
                } catch (Exception e) {
                }
            }
        }
    }


    /**
     * 根据文件路径，检查文件或文件夹内的文件 是否不大于指定大小
     *
     * @param filepath 文件路径
     * @param maxSize  最大 以B为单位
     * @return 是否小于
     */
    public static boolean checkDirSize(String filepath, int maxSize) {
        File file = new File(filepath);
        long size = getDirSize(file);
        if (size <= maxSize) {
            return true;
        } else {
            return false;
        }
    }

    public static long getDirSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//如果是文件则直接返回其大小,以KB为单位
                long size = file.length();
                return size;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0;
        }
    }


}
