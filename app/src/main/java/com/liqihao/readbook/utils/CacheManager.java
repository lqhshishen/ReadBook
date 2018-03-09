package com.liqihao.readbook.utils;

import android.util.Log;

import com.liqihao.readbook.module.ReadPage.bean.ChapterDetailBean;

import java.io.File;
import java.util.List;

/**
 * Created by liqihao on 2018/3/7.
 */

public class CacheManager {
    private static CacheManager manager;

    public static CacheManager getInstance() {
        return manager == null ? (manager = new CacheManager()) : manager;
    }

    public File getChapterFile(String bookId, String chapter) {
        File file = FileUtils.getChapterFile(bookId, chapter);
        if (file != null && file.length() > 50) return file;
        return null;
    }

    public void saveChapterFile(String bookId, String chapter, ChapterDetailBean data) {
        File file = FileUtils.getChapterFile(bookId,chapter);
        FileUtils.writeFile(file.getAbsolutePath(),StringUtils.formatContent(data.getResult()),false);
    }

}
