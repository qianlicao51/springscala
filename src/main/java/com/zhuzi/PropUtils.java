package com.zhuzi;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Properties;

public class PropUtils {

    private static Properties prop = null;

    static {
        try {
            prop = Resources.getResourceAsProperties("conf/db.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProp() {
        if (null != prop) {
            return prop;
        }
        try {
            prop = Resources.getResourceAsProperties("conf/db.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //PropertyPlaceholderConfigurer
        return prop;
    }

    /**
     * 获取配置文件中指定的 key
     *
     * @param key
     * @return
     */
    public static final String getDefaultPropByKey(String key) {
        return prop.getProperty(key);
    }
}
