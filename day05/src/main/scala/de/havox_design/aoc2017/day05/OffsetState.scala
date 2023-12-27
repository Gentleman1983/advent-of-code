package de.havox_design.aoc2017.day05

case class OffsetState(offsets: Seq[Int], index: Int = 0) {
  def jump: OffsetState = {
    val offset = offsets(index)
    OffsetState(offsets.updated(index, offset + 1), index + offset)
  }

  def exited: Boolean =
    !offsets
      .indices
      .contains(index)
}
