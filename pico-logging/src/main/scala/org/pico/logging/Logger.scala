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
      val runtimeClass = implicitly[ClassTag[C]].runtimeClass

      override def debug(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(runtimeClass, LogDebugLevel, message, exception))
      }

      override def trace(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(runtimeClass, LogTraceLevel, message, exception))
      }

      override def info(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(runtimeClass, LogInfoLevel, message, exception))
      }

      override def warn(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(runtimeClass, LogWarnLevel, message, exception))
      }

      override def error(message: => String, exception: Exception = null): Unit = {
        LogBus.publish(LogEvent(runtimeClass, LogErrorLevel, message, exception))
      }
    }
  }
}
