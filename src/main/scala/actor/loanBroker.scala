package actor

import akka.persistence.{AtLeastOnceDelivery, PersistentActor}

class loanBroker  extends PersistentActor  with AtLeastOnceDelivery{
  override def receiveRecover: Receive = ???

  override def receiveCommand: Receive = ???

  override def persistenceId: String = ???
}
