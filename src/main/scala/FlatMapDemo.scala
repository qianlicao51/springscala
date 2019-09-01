object FlatMapDemo {


  def main(args: Array[String]): Unit = {
    val list = List("Alice", "scala", "java")

    println(list)
    //扁平化
    //flat扁平化 ，效果就是将集合中的每个元素的子元素映射到某个函数并返回新的集合
    val chars = list.flatMap(_.toUpperCase())

    println(chars)


    def ads(int: Int) = {
      int * 2
    }
  }
}
