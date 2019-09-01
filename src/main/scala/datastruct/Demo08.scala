package datastruct

object Demo08 {
  def main(args: Array[String]): Unit = {
    // ArrayStack
    val expression = "3+2*6-2"
    val numStack = new ArrayStack2(10)
    val operStack = new ArrayStack2(10)


    while (true) {
      //

    }

  }
}


class ArrayStack2(size: Int) {
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
    top == maxSize - 1
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

  def isEmpty(): Boolean = {
    top == -1
  }

  /**
    * 操作符
    *
    * @param oper
    * @return
    */
  def priority(oper: Int): Int = {
    if (oper == '*' || oper == '/') {
      1
    } else if (oper == '-' || oper == '+') {
      0
    } else {
      -1
    }
  }

  def cal(num1: Int, num2: Int, oper: Int): Int = {
    var res = 0
    oper match {
      case '+' => {
        res = num1 + num2
      }
      case '-' => res = num1 - num2
      case '/' => res = num1 / num2
      case '*' => res = num1 * num2
    }
    res
  }

}