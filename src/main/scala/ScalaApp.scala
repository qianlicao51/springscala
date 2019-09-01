import java.io.File

import com.zhuzi.{PropUtils, SysUtils}
import org.apache.commons.io.FileUtils

object ScalaApp  {
  def main(args: Array[String]): Unit = {
    print("scala 程序")
    println("  读取资源文件 jdbc.driver :" + PropUtils.getProp.getProperty("jdbc.driver"))
    FileUtils.copyFile(new File("d://1.jpg"), new File("d:/" + SysUtils.getDateYmd + "_scala.jpg"))
  }
}
