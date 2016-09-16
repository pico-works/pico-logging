package org.pico.logging

import scala.reflect.ClassTag

trait Logger {
  def debug(message: => String, exception: Exception = null): Unit

  def trace(message: => String, exception: Exception = null): Unit

  def info(message: => String, exception: Exception = null): Unit

  def warn(message: => String, exception: Exception = null): Unit

  def error(message: => String, exception: Exception = null): Unit
}

object Logger {
  def apply[C: ClassTag]: Logger = {
    new Logger {
      val classTag = implicitly[ClassTag[C]]

      override def debug(message: =>String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(classTag, LogDebugLevel, message, exception))
      }

      override def trace(message: =>String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(classTag, LogTraceLevel, message, exception))
      }

      override def info(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(classTag, LogInfoLevel, message, exception))
      }

      override def warn(message: =>String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(classTag, LogWarnLevel, message, exception))
      }

      override def error(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(classTag, LogErrorLevel, message, exception))
      }
    }
  }
}
