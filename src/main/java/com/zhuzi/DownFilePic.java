package com.zhuzi;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DownFilePic {

    private static String baseName;

    public static void main(String[] args) throws IOException {

        downFilePic();
    }


    /**
     * 没有防盗措施
     *
     * @throws IOException
     */
    public static void dfule() throws IOException {
        //http://img.mmmjpg.com/1461/1.jpg
        String url = "http://img.mmmjpg.com/1461/1.jpg";
        FileUtils.copyURLToFile(new URL(url), new File(PropUtils.getDefaultPropByKey("downFile"), SysUtils.getDateYmd() + ".jpg"));
    }

    public static final void downFilePic() throws IOException {
        String url = "http://file.92game.net/mzitu/d/file/bigpic/2016/05/18/23/201605182332162.jpg";
        baseDownPic(url,url);


    }

    private static void dowFilePic() throws IOException {
        String url = "https://i.meizitu.net/2019/04/20a01.jpg";
        baseDownPic(url,"https://www.mzitu.com");
        return;
    }

    private static void baseDownPic(String url,String referrer) throws IOException {
        baseName = FilenameUtils.getName(url);
        File destFile = new File(PropUtils.getDefaultPropByKey("downFile"), SysUtils.getDateYmd() + "." + baseName);
        Connection conn = Jsoup.connect(url).timeout(500000);
        conn.referrer(referrer);
        conn.ignoreContentType(true);
        BufferedInputStream inputStream = conn.execute().bodyStream();
        FileUtils.copyInputStreamToFile(inputStream, destFile);
    }
}
