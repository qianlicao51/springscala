package datastruct

//
import scala.util.control.Breaks._

object Demo02 {

  def main(args: Array[String]): Unit = {
    println(s"#" * 8)
  }

}

class SingleLikedList {
  //头结点
  val head = new HeroNode(0, "", "")


  //添加方法
  //第一种，直接添加到尾部
  def addList(heroNode: HeroNode) = {
    var temp = head
    //找到链表的最后
    breakable {
      while (true) {
        if (temp.next == null) {
          //已经是最后
          break()
        }
        // 如果没有到最后
        temp=temp.next
      }
    }
    //当退出while循环，temp就是链表的最后
    temp.next=heroNode

  }

  /**
    * 遍历链表
    */
  def list(): Unit ={
    if(head.next==null){
      println("当前列表为空")
      return
    }
    

  }


}

/**
  * 链表LinkedList
  */
class HeroNodes(hNo: Int, hName: String, hnickName: String) {
  var no: Int = hNo
  var name: String = hName
  var nickName: String = hnickName
  var next: HeroNode = null
}

