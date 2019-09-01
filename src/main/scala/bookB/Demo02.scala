package bookB

object Demo02 {
  def main(args: Array[String]): Unit = {

    val v = Vector(1, 2, 3)
    v.sum //6
    v.filter(_ > 1) //Vector(2,3)
    v.map(_ * 2) // Vector(2,4,6)

    val couples = List(List("kim", "al"), List("julia", "cat"))
    couples.flatten
    couples.flatten.map(_.capitalize).sorted

    val bag = List("1", "2", "tree", "4", "one hundred seventy five")
    println(bag.flatMap(toInt).sum)


    val ss=List(1,2,3,4,5,6,7,8)
    ss.groupBy(_>3)
    println(ss)

  }

  /**
   * 字符串转数字
   *
   * @param in
   * @return
   */
  def toInt(in: String): Option[Int] = {
    try {
      Some(Integer.parseInt(in))
    } catch {
      case e: Exception => None
    }
  }
}
