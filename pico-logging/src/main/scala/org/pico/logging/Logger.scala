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
  def apply(clazz: Class[_]): Logger = {
    new Logger {
      override def debug(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogDebugLevel, message, exception))
      }

      override def trace(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogTraceLevel, message, exception))
      }

      override def info(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogInfoLevel, message, exception))
      }

      override def warn(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogWarnLevel, message, exception))
      }

      override def error(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogErrorLevel, message, exception))
      }
    }
  }

  def apply[C: ClassTag]: Logger = this(implicitly[ClassTag[C]].runtimeClass)
}
