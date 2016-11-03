package org.pico.logging

import scala.reflect.ClassTag

trait Logger {
  def debug(message: => String, throwable: Throwable = null): Unit

  def trace(message: => String, throwable: Throwable = null): Unit

  def info(message: => String, throwable: Throwable = null): Unit

  def warn(message: => String, throwable: Throwable = null): Unit

  def error(message: => String, throwable: Throwable = null): Unit
}

object Logger {
  def apply(clazz: Class[_]): Logger = {
    new Logger {
      override def debug(message: => String, throwable: Throwable = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogDebugLevel, message, throwable))
      }

      override def trace(message: => String, throwable: Throwable = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogTraceLevel, message, throwable))
      }

      override def info(message: => String, throwable: Throwable = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogInfoLevel, message, throwable))
      }

      override def warn(message: => String, throwable: Throwable = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogWarnLevel, message, throwable))
      }

      override def error(message: => String, throwable: Throwable = null): Unit = {
        LogBus.publish(LogEvent(clazz, LogErrorLevel, message, throwable))
      }
    }
  }

  def apply[C: ClassTag]: Logger = this(implicitly[ClassTag[C]].runtimeClass)
}
