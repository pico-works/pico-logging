package org.pico.logging

sealed trait LogEvent {
  def clazz: Class[_]

  def level: LogLevel

  def message: String

  def throwable: Throwable

  @deprecated("Use throwable method instead")
  def exception: Throwable = throwable
}

object LogEvent {
  def apply(
      theClass: Class[_],
      theLevel: LogLevel,
      theMessage: => String,
      theThrowable: Throwable): LogEvent = {
    new LogEvent {
      override def clazz: Class[_] = theClass

      override def level: LogLevel = theLevel

      override lazy val message: String = theMessage

      override def throwable: Throwable = theThrowable
    }
  }
}
