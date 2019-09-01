package actor.hspxhj

class Message {

}

//协议|样例类默认 序列化|默认只读属性
case class ClientMsg(msg: String)

//服务器端样例类
case class ServerMsg(msg: String)