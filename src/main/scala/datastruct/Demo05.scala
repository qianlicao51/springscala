package datastruct

import scala.util.control.Breaks.{break, breakable}

object Demo05 {


  def main(args: Array[String]): Unit = {

    val double = new DoubleLinkedList
    double.add(new HeroNode2(1, "1name", "1nickname"))
    double.add(new HeroNode2(2, "2name", "2nickname"))
    double.add(new HeroNode2(3, "3name", "3nickname"))
    double.list()

    double.update(new HeroNode2(2, "及时雨", "宋江"))
    double.list()
    double.del(1)
    double.list()
    double.del(3)
    double.list()
  }
}

class DoubleLinkedList {
  val head = new HeroNode2(0, "", "")

  /**
    * 删除|思路|双向链表可以实现自我删除
    *
    * @param delId
    */
  def del(delId: Int): Unit = {
    var flag = false
    var temp = head
    breakable(
      while (true) {
        if (temp.next == null) {
          break()
        }
        if (temp.id == delId) {
          flag = true
          break()
        }
        temp = temp.next
      }
    )
    if (flag) {
      //判断边界
      temp.pre.next = temp.next
      if(temp.next!=null){
        temp.next.pre = temp.pre
      }

    } else {
      println(s" ${delId} 不存在")
    }
  }

  /**
    * 根据编号修改|编号不能修改
    *
    * @param heroNode
    */
  def update(heroNode: HeroNode2): Unit = {
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

  def list(): Any = {
    println("_" * 20)
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

  /**
    *
    * @param heroNode2
    */
  def add(heroNode2: HeroNode2): Unit = {
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
    temp.next = heroNode2
    heroNode2.pre = temp
  }
}

class HeroNode2(ids: Int, names: String, nickNames: String) {
  var id = ids
  var name = names
  var nickname = nickNames
  var pre: HeroNode2 = _
  //默认为空
  var next: HeroNode2 = _

  override def toString: String = {
    s"id $id name: $name  nickname:$nickname next:$next "
  }
}
