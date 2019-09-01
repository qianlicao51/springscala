package datastruct

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

/**
  *
  */
object Demo10 {
  def main(args: Array[String]): Unit = {
    println("s" * 8)

    val arr = Array(1, 8, 10, 89, 1000, 1234)
    val i = binatySearch(arr, 0, arr.length - 1, 1)
    println(i)


  }

  /**
    * 二分查找|1先找到中间值，2 将中间值与查找值比较
    *
    * @param array
    * @return
    */
  def binatySearch(array: Array[Int], l: Int, r: Int, findValue: Int): ArrayBuffer[Int] = {
    val resArr = ArrayBuffer[Int]()
    if (l > r) {
      println("找不到")
      return resArr
    }
    //可变数组

    val midIndex = (l + r) / 2
    val midValue = array(midIndex)
    if (midValue > findValue) {
      // 左边找
      binatySearch(array, l, midIndex, findValue)
    } else if (midValue < findValue) {
      binatySearch(array, midIndex, r, findValue)
    } else {
      var temp = midIndex - 1
      while (true) {
        if (temp < 0 || array(temp) != findValue) {
          break()
        }
        if (array(temp) == findValue) {
          resArr.append(temp)
        }
        temp -= 1
      }
      resArr.append(midIndex)
      temp = midIndex + 1
      while (true) {
        if (temp > array.length - 1 || array(temp) != findValue) {
          break()
        }
        if (array(temp) == findValue) {
          resArr.append(temp)
        }
        temp += 1
      }

    }
    return resArr
  }

  /**
    * 二分查找|1先找到中间值，2 将中间值与查找值比较
    *
    * @param array
    * @return
    */
  def binatySearch2(array: Array[Int], l: Int, r: Int, findValue: Int): Int = {
    if (l > r) {
      println("找不到")
      return -1
    }
    val midIndex = (l + r) / 2
    val midValue = array(midIndex)
    if (midValue > findValue) {
      // 左边找
      binatySearch2(array, l, midIndex, findValue)
    } else if (midValue < findValue) {
      binatySearch2(array, midIndex, r, findValue)
    } else {
      return midIndex
    }
  }
}
