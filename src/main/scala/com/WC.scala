package com

import org.apache.spark.{SparkConf, SparkContext}

object WC {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("appName ").setMaster("local[*]")
    val sc = new SparkContext(conf)


    val word = sc.makeRDD(List("hello spark","hello java"))
    val result = word.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    for (item <- result) {
      println(item)
    }

  }
}
