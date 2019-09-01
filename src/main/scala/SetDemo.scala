import scala.collection.mutable

object SetDemo {
  def main(args: Array[String]): Unit = {


    /**
      * set
      * 默认是不可变的。
      * 不重复的
      */
    val se = Set(1, 2, 3)
    println(se)


    val set = mutable.Set(1, 2, "hello", "scala")

    println(set)
    println(s"~*|" * 20)
    set.add(5)
    set -= 2
    println(set)

    val setss = mutable.Set(1, 2, 3, 4)
    val ints = setss.map(_ * 2)
    println(ints)
    println(setss)
    ints.map(println(_))
  }
}
