package org.pico.logging

sealed trait LogEvent {
  def clazz: Class[_]

  def level: LogLevel

  def message: String

  def exception: Exception
}

object LogEvent {
  def apply(
      theClass: Class[_],
      theLevel: LogLevel,
      theMessage: => String,
      theException: Exception): LogEvent = {
    new LogEvent {
      override def clazz: Class[_] = theClass

      override def level: LogLevel = theLevel

      override lazy val message: String = theMessage

      override def exception: Exception = theException
    }
  }
}
