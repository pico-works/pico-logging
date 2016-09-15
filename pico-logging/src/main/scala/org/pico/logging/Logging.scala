package org.pico.logging

trait Logging {
  lazy val log = Logger[this.type]
}
