package datastruct

import scala.util.control.Breaks._

object Demo09 {
  def main(args: Array[String]): Unit = {

    // until不包括3 |to包括3

    for (i <- 1 to 3) {
      println(i)
    }
    val arr = Array(101, 34, 119, 1)

//    quickSort(0, arr.length - 1, arr)
//    println(arr.mkString(" ,"))


  }

  /**
    * 快速排序：是针对冒泡排序的一种改进，基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分
    * 的所有数据都比另一部分都要小，然后再按次方法对这两部数据分别进行快速排序，整个排序过程可以递归，以此达到
    * 整个数据变成有序序列。
    * left 左边 下标从0开始
    * right  指定从数组的邮边下标length-1
    */
  def quickSort(left: Int, right: Int, arr: Array[Int]): Unit = {
    //测试数组
    var l = left //
    var r = right
    // 右边下标
    var pivot = arr((left + right) / 2)
    breakable {
      //TODO 用于交换， 比中间值小的书放在左边，比中间值大的值放在右边
      while (l < r) {

        while (arr(l) < pivot) {
          //从左边找一个 比 pivot 小的值的下标|把大的数据放在右侧
          l += 1
        }
        while (arr(r) > pivot) {
          //从左边找一个 比 pivot 小的值的下标|把小的数据放在左侧
          r -= 1
        }
        if (l >= r) {
          break() //本次交换结束,退出本次交换
        }
        var temp = arr(l)
        arr(l) = arr(r)
        arr(r) = temp //交换|左边存放小的数据，右边存放大的数据

        //TODO 提高效率的，可以没有
        if (arr(l) == pivot) {
          r -= 1
        }
        if (arr(r) == pivot) {
          l += 1
        }
      }
      //TODO 提高效率的，可以没有
      if (l == r) {
        l += 1
        r -= 1
      }
      //向左递归
      if (left < r) {
        quickSort(left, r, arr)
      }
      if (right > l) {
        quickSort(l, right, arr)
      }

    }
  }

  /**
    * 插入排序|把n个待排序的元素看出一个有序表和一个无序表，开始时有序表只包含一个元素，无序表包含n-1个元素
    * ，排序的过程每次从无序表中取出第一个元素，把它的排序码与有序表中的排序码进行比较，将它插入到有序表中的适当位置
    * ，使之成为新的有序表
    */
  def insertSort(): Unit = {
    //测试数组
    val arr = Array(101, 34, 119, 1)
    //思路 |1 (101 34,119 1)->( (101,34,119 1))
    val insertVal = arr(1)
    var insertIndex = 1 - 1 // 有序表的 最后一个元素
    while (insertIndex >= 0 && insertVal < arr(insertIndex)) {
      //找到位置
      arr(insertIndex + 1) = arr(insertIndex)
      insertIndex -= 1
    }
    arr(insertIndex + 1) = insertVal


    for (i <- 1 until arr.length) {
      val insertVal = arr(i)
      var insertIndex = i - 1 // 有序 表最后一个元素
      while (insertIndex >= 0 && insertVal < arr(insertIndex)) {
        //找到位置
        arr(insertIndex + 1) = arr(insertIndex)
        insertIndex -= 1
      }
      arr(insertIndex + 1) = insertVal
    }
    println(arr.mkString(" "))
  }


}

