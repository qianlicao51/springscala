package com.zhuzi.down.pic;

import com.zhuzi.PropUtils;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 下载启动入口
 */
public class Index {
    private static final Logger logger = LoggerFactory.getLogger(Index.class);

    public static void main(String[] args) {


        int all = Integer.parseInt(PropUtils.getDefaultPropByKey(Config.pic_all));
        for (int j = 0; j < all; j++) {

            List<String> urlList = indexUrl(Config.base_url + j);
            for (String s : urlList) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PicBean picBean = getPicBean(s);
                        System.out.println(picBean);
                        downDetail(picBean);
                    }
                }).start();

            }
        }

    }

    public static void downDetail(PicBean picBean) {
        for (int i = picBean.getIndex(); i <= picBean.getAll(); i++) {
            DownFileUtils.downFic(picBean.getBaseUrl() + i + ".jpg", picBean.getTitle(), Config.imgweb);
        }
    }

    /**
     * 获取每个URL 里面 PicBean
     */
    static PicBean getPicBean(String url) {
        try {
            Document document = Jsoup.connect(url).timeout(500000).get();
            Elements select = document.select(".article");
            PicBean picBean = new PicBean();
            picBean.setTitle(select.select("h1").text());

            String picNum = DownFileUtils.getPicNum(document.toString());
            picBean.setAll(Integer.parseInt(picNum));
            //第一tul
            String firstImageUrl = select.select("#content").select("a").select("img").attr("src");
            picBean.setBaseUrl(FilenameUtils.getPathNoEndSeparator(firstImageUrl) + "/");
            return picBean;
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("失败url{}-{}",url);
        }

        return null;

    }

    /**
     * @param index
     * @return
     * @DownFileUtils 获取每页的图片链接
     */
    static List<String> indexUrl(String index) {
        ArrayList<String> urlList = new ArrayList<>(21);
        try {
            Document document = Jsoup.connect(index).timeout(50000).get();
            Elements pic = document.getElementsByClass("pic").select("li").select("a");//选择基数
            int size = pic.size();
            for (int i = 0; i < pic.size(); i += 2) {//选择基数的
                urlList.add(Config.base_urll + pic.get(i).attr("href"));
            }
            return urlList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
