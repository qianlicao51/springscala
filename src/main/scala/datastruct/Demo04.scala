package datastruct

import scala.util.control.Breaks._

/**
  * 修改链表 15 4 分钟位置
  */
object Demo04 {

  println(s"^" * 8)

  def main(args: Array[String]): Unit = {
    val node1 = new HeroNode(1, "宋江", "及时雨")
    val node2 = new HeroNode(2, "宋江2", "及时雨2")
    val node3 = new HeroNode(3, "宋江3", "及时雨3")


    println("-" * 10)

    //创建一个单向链表
    val singleLinked = new SingleLinked
    singleLinked.add(node2)
    singleLinked.add(node1)
    singleLinked.add(node3)

    singleLinked.list()


    println("-" * 10, "有序添加", s"-" * 10)

    val singleLinkeds = new SingleLinked

    singleLinkeds.addOrderByID(node2)
    singleLinkeds.addOrderByID(node3)
    singleLinkeds.addOrderByID(node1)
    singleLinkeds.addOrderByID(node1)
    singleLinkeds.list()

    //singleLinked.update(new HeroNode(5, "5", "5nickname"))
    singleLinkeds.del(1)
    println("删除之后")
    singleLinkeds.list()
  }
}

class SingleLinked {

  val head = new HeroNode(0, "", "")

  /**
    * 删除|思路|1 head不能动，2 使用临时变量
    *
    * @param heroNode
    */
  def del(delId: Int): Unit = {
    var flag = false
    var temp = head
    breakable(
      while (true) {
        if (temp.next == null) {
          break()
        }
        if (temp.next.id == delId) {
          flag = true
          break()
        }
        temp = temp.next
      }
    )
    if (flag) {
      //判断边界

      temp.next = temp.next.next
    } else {
      println(s" ${delId} 不存在")
    }

  }

  /**
    * 根据编号修改|编号不能修改
    *
    * @param heroNode
    */
  def update(heroNode: HeroNode): Unit = {
    var flag = false //默认找不到


    if (head.next == null) {
      println("链表为空")
      return
    }
    var temp = head.next
    breakable(
      while (true) {
        if (temp == null) {
          break()
        }
        if (temp.id == heroNode.id) {
          flag = true
          break()
        }
        temp = temp.next
      }
    )
    // 找到节点
    if (flag) {
      temp.name = heroNode.name
      temp.nickname = heroNode.nickname
    } else {
      println(s"没有找到，无法修改 $heroNode.id")
    }


  }

  def addOrderByID(heroNode: HeroNode): Unit = {

    var temp = head
    var flag = false //用于判断英雄的编号时候已经存在，默认是false
    breakable(
      while (true) {
        if (temp.next == null) {
          break() //判断是否到最后
        }
        if (temp.next.id > heroNode.id) {
          //找到位置，填加载temp之后
          break()
        } else if (temp.next.id == heroNode.id) {
          //已经有这个节点
          flag = true
          break()
        }
        temp = temp.next
      }
    )

    if (!flag) {
      //可以添加|注意添加顺序|我是使用临时变量，但是作者这个更加巧妙
      heroNode.next = temp.next
      temp.next = heroNode
    } else {
      println(s"*" * 9, s"添加的英雄已经存在  id=${heroNode.id}")

    }

  }

  def list(): Any = {
    if (head.next == null) {
      println(s"*" * 8, "当前链表为空")
      return
    }
    var temp = head.next //因为不关心头结点数据
    breakable(
      while (true) {
        if (temp == null) {
          break() //判断是否到最后
        }
        println(s"节点信息 no=${temp.id} name=${temp.name} nickname=${temp.nickname} ")
        temp = temp.next
      }
    )
  }

  def add(heroNode: HeroNode): Unit = {
    /**
      * 1 不考虑排序 ，直接添加到链表尾部
      */
    // 头结点不能动，需要一个临时节点作为辅助功能
    var temp = head
    breakable(
      while (true) {
        if (temp.next == null) {
          //已经是最后了
          break()
        }
        temp = temp.next //没有到 最后
      }
    )
    //当while退出循环，temp就是链表的最后
    temp.next = heroNode
  }

}

class HeroNode(ids: Int, names: String, nickNames: String) {
  var id = ids
  var name = names
  var nickname = nickNames

  var next: HeroNode = _

  override def toString: String = {
    s"id $id name: $name  nickname:$nickname next:$next "
  }
}