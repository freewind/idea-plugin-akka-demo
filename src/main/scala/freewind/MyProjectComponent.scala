package freewind

import akka.actor.{Actor, ActorSystem, Props}
import com.intellij.openapi.components.ProjectComponent

class MyProjectComponent extends ProjectComponent {
  override def projectClosed(): Unit = ()
  override def projectOpened(): Unit = ()
  override def initComponent(): Unit = {
    val system = ActorSystem("my-actor", MyAkkaConfig.config, this.getClass.getClassLoader)
    val actor = system.actorOf(Props[MyActor])
    actor ! "Hello!"
  }
  override def disposeComponent(): Unit = ()
  override def getComponentName: String = "idea-plugin-akka-demo"
}

class MyActor extends Actor {
  override def receive: Receive = {
    case msg: String => println(msg)
  }
}
