package com.zhuzi;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;

public class DemoApp {

    public static void main(String[] args) throws IOException {
        System.out.println("java程序  DemoApp.main");
        System.out.println("DemoApp.main读取资源文件 jdbc.url :"+ PropUtils.getProp().getProperty("jdbc.url"));
        FileUtils.copyFile(new File("d://1.jpg"), new File(("d:/" + SysUtils.getDateYmd() + "_java.jpg")));
    }
}

