package scalafuture


import rx.lang.scala._

object ObservableDemo  extends App {

  private val o: Observable[String] = Observable.just("c++","java","scala")

  o.subscribe(name=>println(s" learnd the $name language"))
  o.subscribe(name=> println(s"forgot the $name language"))



}
