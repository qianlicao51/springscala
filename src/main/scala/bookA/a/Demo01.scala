package bookA.a


import scala.util.control.Breaks._

/**
  * 图解算法
  */
object Demo01 {
  def main(args: Array[String]): Unit = {
    println("选择排序")

    printDate()
    val ints = randNum()

    println(sum(Array(4, 1, 2, 3), 0))

    val array = Array(1, 10, 4, -2, 4, 188, -90)
    quickSort(array, 0, array.length - 1)
    println(s"快速排序之后的结果是:${array.mkString(" , ")}")


  }

  /**
    * 快速排序
    *
    * @param array
    * @param left
    * @param right
    */
  def quickSort(array: Array[Int], left: Int, right: Int): Unit = {

    var l = left
    var r = right
    var mid = (l + r) / 2
    var temp = 0;

    breakable {
      while (l < r) {
        while (array(l) < array(mid)) {
          //寻找 左边大的数据
          l += 1
        }
        while (array(r) > array(mid)) {
          //寻找右边  小的数据
          r -= 1
        }
        if (l >= r) {
          break()
        }
        //TODO 交换数据|大于中间值的 放在右侧，小于中间值的放在 左侧

        temp = array(l)
        array(l) = array(r)
        array(r) = temp

        //TODO  提高效率
        if (array(l) == array(mid)) {
          r -= 1
        }
        if (array(r) == array(mid)) {
          l += 1
        }
      }
    }

    if (l == r) {
      l += 1
      r -= 1
    }


    //TODO 向左递归
    if (left < r) {
      quickSort(array, left, r)
    }
    //TODO 向右递归
    if (right > l) {
      quickSort(array, l, right)
    }
  }

  /**
    * 递归求和|未完成
    *
    * @param array
    * @param start
    * @return
    */
  def sum(array: Array[Int], start: Int): Int = {

    if (array == null) {
      return 0
    }
    if (array.length == 1) {
      return array(0)
    }
    if (start == array.length) {
      println("ssssssss")
      return array(array.length - 1)
    }

    var sumNum = 0

    if (start < array.length - 1) {
      sumNum += sum(array, start + 1)
      println(s"递归次数 ${start + 1} +${sumNum}")
    }
    return sumNum
  }

  /**
    * 打印当前时间
    */
  def printDate() = {
    var time = new org.joda.time.DateTime()
    println(time.toString("yyyy-MM-dd HH:mm:ss"))

  }

  /**
    * 随机数
    *
    * @return
    */
  def randNum(): Array[Int] = {

    val arr = new Array[Int](80000)
    val random = new util.Random()
    for (i <- 0 until arr.length) {
      arr.apply(random.nextInt(80000))
    }
    arr
  }


  def quickSort(array: Array[Int]): Unit = {


    println(array)
  }

  /**
    * 选择排序|
    * 思路：第一次：选出最小的放在第一位
    * 第二次：选出第二小的放在第二位
    * ……
    *
    * @param arr
    */
  def selectionSort(arr: Array[Int]): Unit = {


    //TODO to包含首尾
    for (i <- 0 until arr.length - 1) {
      var min = arr(i)
      var min_index = i
      //TODO 找出第一位最小的
      for (j <- (i + 1) until arr.length) {
        if (arr(j) < min) {
          min = arr(j)
          min_index = j
        }
      }
      //TODO 交换数据
      if (min_index != i) {
        //TODO 这个交换位置不可颠倒
        arr(min_index) = arr(i)
        arr(i) = min //最小值

      }
      println(s"第 ${i}  次 排序 是${arr.mkString(" ,")}")
    }

    println(s"\r\n 排序之后是 |${arr.mkString(" ,")}")
  }

  /**
    * 二分法查找
    *
    * @param arr
    * @param item
    * @return
    */
  def binary_search(arr: Array[Int], item: Int): Any = {
    var low = 0
    //TODO 跟踪查找的列表部分
    var high = arr.length - 1
    while (low <= high) {
      var mid = (low + high) / 2
      var guess = arr(mid) //TODO 列表的中间值
      if (guess == item) {
        return guess
      }
      if (guess < item) {
        //TODO 猜小了
        low = mid + 1
      } else {
        high = mid - 1
      }
    }
    //TODO 上面while 找到了就不会执行此处
    println("s" * 10)
    None
  }

  /**
    * 选择排序|
    * 思路：第一次：选出最小的放在第一位
    * 第二次：选出第二小的放在第二位
    * ……
    *
    * @param arr
    */
  def selectionSortPart1(arr: Array[Int]): Unit = {

    var min = arr(0)
    var min_index = 0

    //TODO 找出第一位最小的
    for (i <- 1 to arr.length - 1) {
      if (arr(i) < min) {
        min = arr(i)
        min_index = i
      }
    }
    //TODO 交换数据
    val temp = arr(0)
    arr(0) = arr(min_index)
    arr(min_index) = temp

    println(s"最小的数 是${arr(min_index)}")
  }

  /**
    *
    * @param arr
    * @return
    */
  def findSmallest(arr: Array[Int]): Int = {
    //TODO 存储最小值
    var smal = arr(0)
    //TODO 存储最小下标
    var smal_index = 0

    for (i <- 1 to arr.length) {
      if (smal > arr(i)) {
        smal = arr(i)
        smal_index = i
      }
    }
    smal_index
  }


}
