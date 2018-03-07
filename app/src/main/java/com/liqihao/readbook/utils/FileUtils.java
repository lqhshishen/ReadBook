package com.liqihao.readbook.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.liqihao.readbook.contents.Constant;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by liqihao on 2018/3/7.
 */

public class FileUtils {

    public static String getChapterPath(String bookId, String chapter) {
        return Constant.PATH_TXT + bookId + File.separator + chapter + ".txt";
    }

    public static File getChapterFile(String bookId, String chapter) {
        File file = new File(getChapterPath(bookId,chapter));
        if (!file.exists()) createFile(file);
        return file;
    }


    public static String getCharset(String fileName) {
        BufferedInputStream bis = null;
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(fileName));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8";
                checked = true;
            }
            bis.mark(0);
            if (!checked) {
                while ((read = bis.read()) != -1) {
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80 - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return charset;
    }




    public static String createRootPath(Context context) {
        String cacheRootPath = "";
        if (isSdCardAvailable()) {
            cacheRootPath = context.getExternalCacheDir().getPath();
        } else {
            cacheRootPath = context.getCacheDir().getPath();
        }
        return cacheRootPath;
    }

    public static boolean isSdCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String createFile(File file) {
        try {
            if (file.getParentFile().exists()) {
                Log.i("","----创建文件" + file.getAbsolutePath());
                file.createNewFile();
                return file.getAbsolutePath();
            } else {
                createDir(file.getParentFile().getAbsolutePath());
                file.createNewFile();
                Log.i("-----创建文件",file.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 递归创建文件夹
     *
     * @param dirPath
     * @return 创建失败返回""
     */
    public static String createDir(String dirPath) {
        try {
            File file = new File(dirPath);
            if (file.getParentFile().exists()) {
                Log.i("-----创建文件夹",file.getAbsolutePath());
                file.mkdir();
                return file.getAbsolutePath();
            }else {
                createDir(file.getParentFile().getAbsolutePath());
                Log.i("------创建文件夹",file.getAbsolutePath());
                file.mkdir();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return dirPath;
    }

    /**
     * 将内容写入文件
     *
     * @param filePath eg:/mnt/sdcard/demo.txt
     * @param content  内容
     * @param isAppend 是否追加
     */

    public static void writeFile(String filePath, String content, boolean isAppend) {
        Log.i("save", filePath);
        try {
            FileOutputStream fout = new FileOutputStream(filePath,isAppend);
            byte [] bytes = content.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeFile(String filePathAndName, String fileContent) {
        try {
            OutputStream outputStream = new FileOutputStream(filePathAndName);
            OutputStreamWriter out = new OutputStreamWriter(outputStream);
            out.write(fileContent);
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
