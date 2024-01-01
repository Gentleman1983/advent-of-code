package de.havox_design.aoc2017.day05

import scala.collection.AbstractIterator

class OffsetIterator(private var offsetState: OffsetState) extends AbstractIterator[OffsetState] {
  override def hasNext: Boolean = {
    !offsetState
      .exited
  }

  override def next(): OffsetState = {
    val returnState = offsetState

    offsetState = offsetState
      .jump

    returnState
  }
}
