package com.zhuzi;

import org.joda.time.DateTime;

/**
 * 工具类
 */
public class SysUtils {
    /**
     *
     * @return YYYY-MM-dd HH:mm:ss
     */
    public static String getDate() {

        return new DateTime().toString("YYYY-MM-dd HH:mm:ss");
    }

    /**
     *
     * @return YYYY-MM-dd-HH-mm-ss
     */
    public static String getDateYmd() {

        return new DateTime().toString("YYYY-MM-dd-HH-mm-ss");
    }

}
