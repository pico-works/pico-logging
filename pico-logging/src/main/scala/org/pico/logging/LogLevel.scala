package org.pico.logging

sealed trait LogLevel

case object LogDebugLevel extends LogLevel
case object LogTraceLevel extends LogLevel
case object LogInfoLevel extends LogLevel
case object LogWarnLevel extends LogLevel
case object LogErrorLevel extends LogLevel
