package datastruct

import scala.io.StdIn

object Demo07 {
  def main(args: Array[String]): Unit = {

    val arrayStack = new ArrayStack(5)

    var key = ""
    while (true) {
      println("show 显示")
      println("exit 退出")
      println("push 显示")
      println("pop 显示")
      println("list 显示")

      key = StdIn.readLine()
      key match {
        case "show" => arrayStack.list()
        case "push" => {
          println("输入一个数字")
          val value = StdIn.readInt()
          arrayStack.push(value)
        }
        case "pop" => {
          val res = arrayStack.pop()
          if (res.isInstanceOf[Exception]) {
            println(res.asInstanceOf[Exception].getMessage)
          } else {
            println(s"得到的数据是${res}")
          }

        }
        case "exit" => System.exit(0)
      }
    }

  }
}

class ArrayStack(size: Int) {
  val maxSize = size //栈大小
  var stack = new Array[Int](maxSize)
  //栈顶 初始化为-1
  var top = -1

  def push(pushNum: Int): Unit = {
    if (isFull()) {
      println("满了")
      return
    }
    top = top + 1
    stack(top) = pushNum
  }

  /**
    * 栈满
    */
  def isFull(): Boolean = {
    return top == maxSize - 1

  }

  /**
    * 出栈
    *
    * @return
    */
  def pop(): Any = {
    if (isEmpty()) {
      return new Exception("栈空")
    }
    val temp = stack(top)
    top -= 1
    return temp
  }

  def isEmpty(): Boolean = {
    top == -1
  }

  def list(): Unit = {
    if (isEmpty()) {
      println("栈空，无法遍历")
      return
    }

    // 反转
    for (i <- 0 until top reverse) {
      println(s" 遍历 结果 ${stack(i)}")
    }

  }
}