package actor.char01

//消息|默认是不可变的|scala的case class 是可以被序列化的
case class SetRequest(key: String, value: Object)

case class GetRequest(key: String)

case class KeyNotFoundException(key: String) extends Exception
