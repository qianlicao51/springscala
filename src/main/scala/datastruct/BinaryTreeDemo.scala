package datastruct

/**
  * 二叉树
  */
object BinaryTreeDemo {

  def main(args: Array[String]): Unit = {

    //直接关联
    var root: HeroNodes2 = new HeroNodes2(1, "及时雨")
    var h2: HeroNodes2 = new HeroNodes2(2, "吴用")
    var h3: HeroNodes2 = new HeroNodes2(3, "卢俊义")
    var h4: HeroNodes2 = new HeroNodes2(4, "林冲")
    var h5: HeroNodes2 = new HeroNodes2(5, "广胜")

    root.left = h2
    root.right = h3

    h3.left = h5
    h3.right = h4

    val tree = new BinaryTree
    tree.root = root
    tree.preOrder()
    println("-" * 10)
    tree.delByNo(5)
    tree.preOrder()

    //    tree.infixOrder()
    println("-" * 10)

    //    tree.postOrder()


    val nodes = tree.preOrderSearch(5)
    if (nodes != null) {
      println("找到英雄", nodes.toString)
    } else {
      println("没有 此 英雄")
    }
  }

}

/**
  * 定义节点
  *
  * @param hNo
  * @param hName
  */
class HeroNodes2(hNo: Int, hName: String) {
  val no = hNo
  var nmae = hName

  var left: HeroNodes2 = _
  var right: HeroNodes2 = _

  override def toString: String = {
    s"节点信息 ${no} ${nmae}"
  }

  /**
    * 删除节点
    * 1 如果删除的节点是叶子节点 ，则删除该节点
    * 2 如果删除的是非叶子节点，则删除该子树
    * 2
    *
    * @param delNo
    */
  def delByNo(delNo: Int): Any = {
    // 比较当前节点的 左节点是不是 要删除的节点

    if (this.left != null && this.left.no == delNo) {
      this.left = null
      return
    }
    //比较当前节点的 右节点 是否为要删除的节点
    if (this.right != null && this.right.no == delNo) {
      this.right = null
      return
    }
    //左递归
    if (this.left != null) {
      this.left.delByNo(delNo)
    }
    // 右边递归
    if (this.right != null) {
      this.right.delByNo(delNo)
    }

  }

  /**
    * 查找
    *
    * @param findId
    * @return
    */
  def preOrderSearch(findId: Int): HeroNodes2 = {
    if (this.no == findId) {
      return this
    }
    var resuHero: HeroNodes2 = null
    if (this.left != null) {
      resuHero = this.left.preOrderSearch(findId)
    }
    if (resuHero != null) {
      return resuHero
    }
    if (this.right != null) {
      resuHero = this.right.preOrderSearch(findId)
    }
    resuHero
  }

  def infixOrderSearch(findId: Int): HeroNodes2 = {

    var resuHero: HeroNodes2 = null
    if (this.left != null) {
      resuHero = this.left.infixOrderSearch(findId)
    }
    if (resuHero != null) {
      return resuHero
    }
    if (this.no == findId) {
      return this
    }
    if (this.right != null) {
      resuHero = this.right.infixOrderSearch(findId)
    }
    resuHero
  }

  def postOrderSearch(findId: Int): HeroNodes2 = {

    var resuHero: HeroNodes2 = null
    if (this.left != null) {
      resuHero = this.left.postOrderSearch(findId)
    }
    if (resuHero != null) {
      return resuHero
    }
    if (this.right != null) {
      resuHero = this.right.postOrderSearch(findId)
    }
    if (resuHero != null) {
      return resuHero
    }
    if (this.no == findId) {
      return this
    }
    resuHero
  }

  /**
    * 前序遍历
    */
  def preOrder(): Unit = {
    //输出当前节点的值
    println(this.toString)
    //递归 左子树
    if (this.left != null) {
      this.left.preOrder()
    }
    //向右边递归
    if (this.right != null) {
      this.right.preOrder()
    }
  }

  /**
    * 中序遍历
    */
  def infixOrder(): Unit = {
    //递归 左子树
    if (this.left != null) {
      this.left.infixOrder()
    }
    //输出当前节点的值
    println(this.toString)
    //向右边递归
    if (this.right != null) {
      this.right.infixOrder()
    }
  }

  def postOrder(): Unit = {
    //递归 左子树
    if (this.left != null) {
      this.left.postOrder()
    }

    //向右边递归
    if (this.right != null) {
      this.right.postOrder()
    }
    //输出当前节点的值
    println(this.toString)
  }

}

class BinaryTree {

  var root: HeroNodes2 = _

  /**
    * 删除
    *
    * @param delNo
    */
  def delByNo(delNo: Int) {
    if (root != null) {
      if (root.no == delNo) {
        root = null
        return
      }
      root.delByNo(delNo)
    }
  }

  /**
    * 前序遍历
    */
  def preOrder(): Unit = {
    if (root != null) {
      root.preOrder()
    } else {
      println("当前树为空")
    }
  }

  /**
    * 前序查找
    *
    * @param no
    * @return
    */
  def preOrderSearch(no: Int): HeroNodes2 = {
    if (root != null) {
      return root.preOrderSearch(no)
    }
    println("当前二叉树为空 啊")
    return null
  }

  /**
    * 后序遍历
    */
  def postOrder(): Unit = {
    if (root != null) {
      root.postOrder()
    } else {
      println("当前树为空")
    }
  }

  def postOrderSearch(no: Int): HeroNodes2 = {
    if (root != null) {
      return root.postOrderSearch(no)
    }
    println("当前二叉树为空 啊")
    return null
  }

  /**
    * 后序查找
    *
    * @param no
    * @return
    */
  def infixOrderSearch(no: Int): HeroNodes2 = {
    if (root != null) {
      return root.infixOrderSearch(no)
    }
    println("当前二叉树为空 啊")
    return null
  }

  def infixOrder(): Unit = {
    if (root != null) {
      root.infixOrder()
    } else {
      println("当前树为空")
    }
  }
}
