package de.havox_design.aoc2017.day05

case class OffsetState(offsets: Seq[Int], isPart2: Boolean = false, index: Int = 0) {
  def jump: OffsetState = {
    val offset = offsets(index)
    OffsetState(offsets.updated(index, updatedOffset(offset)), isPart2, index + offset)
  }

  def exited: Boolean =
    !offsets
      .indices
      .contains(index)

  private def updatedOffset(offset: Int): Int = {
    if (isPart2 && offset >= 3) {
      offset - 1
    }
    else {
      offset + 1
    }
  }
}
