package datastruct

import scala.io.StdIn

/**
  * scala 数据结构和算法
  * https://www.bilibili.com/video/av44375469/?p=5&t=661
  */
object Demo {
  def main(args: Array[String]): Unit = {
    var key = ""

    val queue = new ArrayQueue(3)

    while (true) {

      println("show 显示")
      println("exit 退出")
      println("add 添加")
      println("get")
      key = StdIn.readLine("请输入")

      key match {
        case "show" => {
          queue.show()

        }

        case "exit" => {
          println("退出程序")
          System.exit(1)
        }
        case "add" => {
          println("输入添加数据")
          val addData = StdIn.readInt()
          queue.add(addData);
        }

        case "get" => {
          val result = queue.getQueue()
          if (result.isInstanceOf[Exception]) {
            println(result.asInstanceOf[Exception].getMessage)
          } else {
            println(s"取出的数据是 $result")
          }

        }

      }

    }


  }

}

/**
  * 数组模拟队列
  */
class ArrayQueue(arrayMaxSize: Int) {

  val maxSize = arrayMaxSize

  val array = new Array[Int](maxSize) //模拟 队列

  var front = -1 //指向队列的头部| 对着输出数据的改变而改变|指向队列前一个位置
  var rear = -1 //指向队列的尾部|随着输入数据的改变而改变|指向列表最后包含最后

  def add(n: Int): Any = {
    if (isFully()) {
      println("队列满了")
      return
    }
    rear += 1 // rear后移动
    array(rear) = n
  }

  /**
    * 判断是否满
    *
    */
  def isFully(): Boolean = {
    return rear == maxSize - 1
  }

  def getQueue(): Any = {
    if (isEmpty()) {
      return new Exception("队列  为空")
    }
    front += 1
    return array(front)
  }

  def show(): Unit = {
    if (isEmpty()) {
      println("队列为空")
      return
    }

    for (i <- front + 1 to rear) {
      println(s"arr[$i] = ${array(i)}")
    }


  }

  /**
    * 判断是否为空
    */
  def isEmpty(): Boolean = {
    return front == rear //头和尾在一起为空
  }
}
