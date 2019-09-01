package datastruct

object MiGong {
  def main(args: Array[String]): Unit = {


    val map = Array.ofDim[Int](8, 7)

    //上下全部值为1
    for (i <- 0 until 7) {
      map(0)(i) = 1
      map(7)(i) = 1
    }
    //左右也为1
    for (i <- 0 until (8)) {
      map(i)(0) = 1
      map(i)(6) = 1
    }
    map(3)(1) = 1
    map(3)(2) = 1
    for (i <- 0 until 8) {
      for (j <- 0 until 7) {
        print(map(i)(j) + " ")
      }
      println()
    }

    setweAY(map, 1, 1)


    println()
    for (i <- 0 until 8) {
      for (j <- 0 until 7) {
        print(map(i)(j) + " ")
      }
      println()
    }
  }

  /**
    * 使用递归回溯来找路
    *
    * @param map
    * @param i
    * @param j
    * @return
    * 1 map地图
    * 2 ij 约定从哪个位置出发
    * 3 约定 0：还没开始走 1 墙 2 可以走 3 已经走过，但是是死角
    */

  def setweAY(map: Array[Array[Int]], i: Int, j: Int): Boolean = {
    if (map(6)(5) == 2) {
      return true
    } else {
      if (map(i)(j) == 0) {
        //可以走但是还没走
        map(i)(j) = 2 //认为该点可以走通
        if (setweAY(map, i, j - 1)) {
          //向下走
          true
        } else if (setweAY(map, i, j + 1)) {
          //右边
          true
        } else if (setweAY(map, i - 1, j)) {
          //上
          true
        } else if (setweAY(map, i + 1
          , j)) {
          true
        } else {
          map(i)(j) = 3 //此路不通
          return false
        }
      } else {
        return false
      }

    }
  }
}
