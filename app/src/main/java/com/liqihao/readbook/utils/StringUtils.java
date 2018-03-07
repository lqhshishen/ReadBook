package com.liqihao.readbook.utils;

/**
 * Created by liqihao on 2018/3/7.
 */

public class StringUtils {
    public static String formatContent(String str) {

        /**
         * 格式化小说内容。
         * <p/>
         * <li>小说的开头，缩进2格。在开始位置，加入2格空格。
         * <li>所有的段落，缩进2格。所有的\n,替换为2格空格。
         *
         * @param str
         * @return
         */
        str = str.replaceAll("[ ]*", "");//替换来自服务器上的，特殊空格
        str = str.replaceAll("[ ]*", "");//
        str = str.replace("\n\n", "\n");
        str = str.replace("\n", "\n" + getTwoSpaces());
        str = getTwoSpaces() + str;
//        str = convertToSBC(str);
        return str;
    }

    /**
     *
     * @return
     */

    public static String getTwoSpaces() {
        return "\u3000\u3000";
    }
}
