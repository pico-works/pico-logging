package org.pico.logging

trait Logging {
  protected lazy val log = Logger[this.type]
}
