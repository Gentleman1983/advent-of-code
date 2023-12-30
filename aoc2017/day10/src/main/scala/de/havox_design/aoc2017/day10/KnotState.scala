package de.havox_design.aoc2017.day10

case class KnotState(elements: Vector[Int] = Vector.range(0, 256), currentPosition: Int = 0, skipSize: Int = 0) {
  def reversed(length: Int): KnotState = {
    val buffer = elements
      .toBuffer
    
    for (i <- 0 until length) {
      buffer((currentPosition + i) % buffer.size) = elements((currentPosition + length - i - 1) % buffer.size)
    }

    KnotState(buffer.toVector, (currentPosition + length + skipSize) % buffer.size, skipSize + 1)
  }

  def checkProduct: Int = elements
    .take(2)
    .product
}
