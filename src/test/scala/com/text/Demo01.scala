package com.text

import java.io.File
import java.net.URL

import org.apache.commons.io.FileUtils
import org.jsoup.Jsoup

object Demo01 {
  def main(args: Array[String]): Unit = {

    val url = "http://img.mmmjpg.com/1461/2.jpg"

    val l = System.currentTimeMillis()
    val connection = Jsoup.connect(url)
    val response = connection.ignoreContentType(true).execute()
    var file: File = new File(s"d:/${l}.jpg")
    FileUtils.copyInputStreamToFile(response.bodyStream(), file)
    file = new File(s"d://${l + 1}.jpg")
    FileUtils.copyURLToFile(new URL(url), file)
  }
}
