package bookB

object Demo03 {

  def main(args: Array[String]): Unit = {
    val list=1::2::3::Nil
    val list2=List(1,2,3)

    val list3= List.range(1,10,2)
    val list4= List.fill(3)("foo")

    val list5= List.tabulate(3)(n=>n*2)

    val list6= "foo".toList
  }

}
