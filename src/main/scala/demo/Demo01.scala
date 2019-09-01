package demo

import java.io.File

import net.ruippeixotog.scalascraper.browser.HtmlUnitBrowser.HtmlUnitDocument
import net.ruippeixotog.scalascraper.browser.{HtmlUnitBrowser, JsoupBrowser}
import org.apache.commons.io.FileUtils
import org.joda.time.DateTime


/**
 * scala 解析html|https://github.com/ruippeixotog/scala-scraper#quick-start
 */
object Demo01 {
  def main(args: Array[String]): Unit = {
    val browser = JsoupBrowser()
    val doc2 = browser.get("http://www.mmmjpg.com/mm/1506")
    //    println(doc2)

    val picUrlStr = "http://img.mmmjpg.com/1505/1.jpg"
    val pic2="http://img.mmmjpg.com/370/2.jpg"
    val typedBrowser: HtmlUnitBrowser = HtmlUnitBrowser.typed()

    val typedDoc: HtmlUnitDocument = typedBrowser.get(pic2)
    val asStream = typedDoc.underlying.getWebResponse.getContentAsStream


    FileUtils.copyInputStreamToFile(asStream,new File(s"d:/pic/${new DateTime().toString("yyyy-MM-dd-HH-mm-ss")}.jpg"))

  }
}
