package datastruct

import scala.util.control.Breaks._

/**
  * 21  开始看
  */
object Demo06 {
  def main(args: Array[String]): Unit = {

    val childGame = new ChildGame
    childGame.addChile(7)
    childGame.showBoy()

  }
}

/**
  * 单向环形链表应用，Josephu问题
  *
  */

class ChildGame {
  //初始化头
  var first: Child = new Child(-1)


  /**
    *
    * @param startNo  从第几个开始数
    * @param countNum 数几次
    * @param nums     一共几个人
    */
  def countBoy(startNo: Int, countNum: Int, nums: Int): Unit = {
    if (first.next == null || startNo < 1 || startNo > nums) {
      println("参数有误")
      return
    }


    var helper = first
    // help定位到 first之前
    breakable {
      while (true) {
        if (helper.next == first) {
          break()
        }
        helper = helper.next
      }
    }
    //2 first移到 到startno(help对应移动)
    for (i <- 0 until startNo - 1) {
      first = first.next
      helper = helper.next

    }

    /**
      * 开始数数，按照给定的值，每数到一个小孩就出圈，直到环形链表只有一个节点
      */
    breakable(
      while (true) {
        if (helper == first) {
          //只有一个人
          break()
        }
        // 开始 书countNum
        for (i <- 0 until countNum - 1) {
          first = first.next
          helper = helper.next

        }
        println(s"出现的是 ${first.id}")
        //移除 first指向的节点
        first = first.next
        helper.next = first


      })

    println(s"最后留下的人是 ${first.id}")

  }

  /** **
    * 添加
    *
    * @param num
    */
  def addChile(num: Int): Unit = {
    if (num < 1) {
      println("num 值不正确")
      return
    }
    //环形链表 需要一个指针
    var curBoy: Child = null
    for (no <- 1 to num) {
      //根据编号创建小海对象
      val boy = new Child(no)

      if (no == 1) {
        //第一个孩子，形成一个环形链表
        first = boy
        first.next = first //形成环形
        curBoy = first
      } else {
        curBoy.next = boy
        boy.next = first
        curBoy = boy
      }
    }
  }

  /**
    * 展示
    */
  def showBoy(): Unit = {
    if (first.next == null) {
      println(" 为空")
      return
    }
    //first 不能动，需要 辅助指针完成遍历
    var curBoy = first
    breakable(
      while (true) {
        println(s"小孩编号是 ${curBoy.id}")
        if (curBoy.next == first) {
          break()
        }
        curBoy = curBoy.next //curBoy后移
      }
    )
  }
}

class Child(ids: Int) {
  var id = ids
  //默认为空
  var next: Child = _

  override def toString: String = {
    s"id $id   next:$next   "
  }
}
