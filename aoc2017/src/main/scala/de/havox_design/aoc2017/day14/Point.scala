package de.havox_design.aoc2017.day14

val orthogonal = Set((-1, 0), (1, 0), (0, -1), (0, 1))

case class Point(x: Int, y: Int):
  private def delta(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
  def neighbours: Set[Point] = orthogonal.map(delta)
