package com.zhuzi.down.pic;

import com.zhuzi.PropUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DownFileUtils {

    //http://img.mmmjpg.com/1462/2.jpg
    public static void downFic(String url, String fileName, String imgweb) {
        String baseName = FilenameUtils.getBaseName(url);
        File destFile = null;
        if ("1".equals(baseName)) {
            destFile = new File(PropUtils.getDefaultPropByKey("downFile"), url.replace(imgweb, "") + fileName + ".jpg");
        } else
            destFile = new File(PropUtils.getDefaultPropByKey("downFile"), url.replace(imgweb, ""));
        try {
            destFile= new File(destFile.getAbsolutePath().replaceAll(" ",""));
            FileUtils.copyURLToFile(new URL(url), destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片总数
     *
     * @param url
     * @return 图片总数字符串
     */
    public static String getPicNum(String url) {
        String page = "";
        Element body = Jsoup.parse(url);
        Elements page1 = body.getElementById("page").getAllElements();
        Element picNumEle = page1.get(page1.size() - 3);
        page = picNumEle.ownText();

        return page;

    }

    /**
     * 文件名
     *
     * @param url
     * @param remove
     * @return
     */
    public static String getFileName(String url, String remove, String title) {
        String extension = FilenameUtils.getExtension(url);
        String replace = url.replace(remove, "");
        return extension;
    }
}
