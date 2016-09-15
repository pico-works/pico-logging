package org.pico.logging

import scala.reflect.ClassTag

sealed trait LogEvent {
  def classTag: ClassTag[_]

  def level: LogLevel

  def message: String

  def exception: Exception
}

object LogEvent {
  def apply(
      theClassTag: ClassTag[_],
      theLevel: LogLevel,
      theMessage: => String,
      theException: Exception): LogEvent = {
    new LogEvent {
      override def classTag: ClassTag[_] = theClassTag

      override def level: LogLevel = theLevel

      override lazy val message: String = theMessage

      override def exception: Exception = theException
    }
  }
}
