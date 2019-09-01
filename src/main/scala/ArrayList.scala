import scala.collection.mutable

object ArrayList {
  def main(args: Array[String]): Unit = {


    //java list 转scala 集合

    //元组
    /**
      * 最大是22个元素 ，将多个无关的元素封装为一个整体
      *
      */

    val tuple = new Tuple2("name", "scala")
    println(tuple.productElement(0)) //访问元素

    //    遍历
    for (item <- tuple.productIterator) {
      println(item)
    }

    // scala中年的list,是不可变的，如果需要使用可变的，使用listBuff
    var list = List(1, 2, 4)
    println(list)
    Nil //空集合 List()
    println(Nil)

    val lists = list :+ 4 //list追加元素
    10 +: list

    //    listBuffer 可变list 集合

    //    队列

    val queue = new mutable.Queue[Int]()
    queue += 20
    queue ++= List(1, 2, 3, 4)
    println(queue)
    //    出队列
    val q1 = queue.dequeue() //默认从队列首   从头部取出一个元素
    queue
    println(queue)
    //入队列 默认从尾部加入

    queue.enqueue(12)
    println(queue)


    /** *
      * map
      * 不可变的 map是有序的
      *
      *
      */
    val stringToInt = Map("Alice" -> 2, "helo" -> "scala")
    println(stringToInt)
    //    构建可变map
    val map = mutable.Map("hello" -> "scala", ("java", "java"))
    val maps = mutable.Map()

    //取值 不存在会抛出异常 NoSuchElementException
    println(map("java"))
    println(map.get("java").get) //不存在是 None
    println(map.contains("j"))

    println(map.getOrElse("jav", "默认值"))

    map("java") = "javascript"
    map("python") = "python" //不存在就 添加存在就更新
    println(map)
    map += ("s" -> "s", "a" -> "s")//添加多个

    //遍历
    for (v<- map){
      println(v._1,v._2)
    }

    println(s"~"*20)
    for ((k,v)<-map){
      println(s" key ${k} value ${v}")
    }

  }

}
