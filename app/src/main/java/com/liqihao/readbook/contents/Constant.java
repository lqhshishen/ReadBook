package com.liqihao.readbook.contents;

import com.liqihao.readbook.app.App;
import com.liqihao.readbook.utils.FileUtils;

/**
 * Created by liqihao on 2018/1/10.
 */

public class Constant {
    public static final String API_BASE_URL = "http://novel.xianwan.com/api/";

    public static String PATH_DATA = FileUtils.createRootPath(App.AppContext) + "/cache";

    public static String PATH_TXT = PATH_DATA + "/book/";
}
