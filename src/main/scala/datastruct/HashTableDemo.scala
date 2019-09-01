package datastruct

import scala.io.StdIn
import scala.util.control.Breaks._

object HashTableDemo {
  def main(args: Array[String]): Unit = {

    val hashTab = new HashTab(5)

    var key = ""
    while (true) {
      println("list")
      println("exit")
      println("add")
      key = StdIn.readLine()
      key match {
        case "add" => {
          val id: Int = StdIn.readLine("输入id：").toInt
          val name = StdIn.readLine("输入名字：")
          hashTab.add(new Emp(id, name))
        }
        case "list" => {
          hashTab.list()
        }
        case "exit" => {
          System.exit(0)
        }
      }
    }

  }
}

class Emp(eId: Int, eName: String) {
  val id = eId
  var name = eName
  var next: Emp = _

  override def toString: String = {
    s" $id $name"
  }

}

class HashTab(val size: Int) {
  //size会成为一个只读属性
  val empLinkedListArr: Array[EmpLinkedList] = new Array[EmpLinkedList](size)

  //初始化链表各个元素
  for (i <- 0 until (size)) {
    empLinkedListArr(i) = new EmpLinkedList
  }

  def findByID(findId: Int): Unit = {
    val emp = this.empLinkedListArr(hashFun(findId)).findByID(findId)
    if (emp == null) {
      println("没有找到")
    } else {
      println("找的的信息是", emp.toString)
    }


  }

  /**
    * 散列函数
    *
    * @param emp
    */
  def add(emp: Emp): Unit = {
    //返回该员工 ，应该加入哪条链表
    val empNo = hashFun(emp.id)
    this.empLinkedListArr(empNo).add(emp)
  }

  /**
    * 散列函数
    *
    * @param id
    * @return
    */
  def hashFun(id: Int): Int = {
    println(s"hasFun is ${id % size}")
    id % size
  }

  def list(): Unit = {
    //遍历整个hashtable
    for (i <- 0 until (size)) {
      empLinkedListArr(i).list(i)
      println()
    }

  }

}

class EmpLinkedList {
  //头指针
  var head: Emp = null

  /**
    *
    * @param findId
    */
  def findByID(findId: Int): Emp = {
    //辅助指针
    if (head == null) {
      println("链表为空~~")
      return null
    }
    //辅助指针
    var cur: Emp = head
    breakable(
      while (true) {
        if (cur == null) {
          break()
        }
        if (cur.id == findId) {
          break()
        }
        cur = cur.next
      })
    //cur指向了链表的最后
    return cur
  }

  /**
    * 添加|直接添加尾部|id自增
    *
    * @param emp
    */
  def add(emp: Emp): Unit = {

    if (head == null) {
      head = emp
      return
    }
    //辅助指针
    var cur: Emp = head
    breakable(
      while (true) {
        if (cur.next == null) {
          break()
        }
        cur = cur.next
      })
    //cur指向了链表的最后
    cur.next = emp

  }

  def list(index: Int): Unit = {
    if (head == null) {
      println(s"链表${index} 为空")
      return
    }
    println(s"链表${index} 的信息")
    //辅助指针
    var cur: Emp = head
    breakable(
      while (true) {
        if (cur == null) {
          break()
        }
        println(s"当前数据是 ${cur}")
        cur = cur.next
      })
    //cur指向了链表的最后
  }
}
