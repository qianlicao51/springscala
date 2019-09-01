package datastruct

object Demo03 {

  def main(args: Array[String]): Unit = {
    println("ss")
  }


}

/**
  * 环形队列 10
  *
  * @param maxSize
  */
class CircleArrayQueue(arrayMaxSize: Int) {


  val maxSize = arrayMaxSize

  val array = new Array[Int](maxSize) //模拟 队列

  var front = 0
  //指向队列的头部|
  var rear = 0 //指向队列的尾部|

  def add(n: Int): Unit = {
    if (isFull()) {
      println("队列满了")
      return
    }

    array(rear) = n
    //rear后移 ，必须考虑取模
    rear = (rear + 1) % maxSize
  }

  def isFull() = {
    (rear + 1) % maxSize == front

  }

  /**
    * 先进先出
    */
  def getQueue: Unit = {
    if (isEmpty) {
      return new Exception("  队列为空")

    }
    //front指向了队列的头元素
    /**
      * 1 先把front 对应的数据保存到变量
      * 2 将front后移
      * 3 返回前面保存的变量值
      */
    val tmp = array(front)
    front = (1 + front) % maxSize
    return tmp
  }

  def show(): Unit = {
    if (isEmpty) {
      println("队列为空")
      return
    }

    //取队列思路：从front取，取几个元素

    for (i <- front until front + size()) {
      println("arr[%d]=%d", i % maxSize, array(i % maxSize))
    }
  }

  /**
    * 队列有几个数据
    * @return
    */
  def isEmpty: Boolean = {
    rear == front
  }

  def size(): Int = {
    (rear + maxSize - front) % maxSize
  }

}