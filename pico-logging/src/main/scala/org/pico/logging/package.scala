package org.pico

package object logging {
  import org.pico.event.Bus

  val LogBus = Bus[LogEvent]
}
