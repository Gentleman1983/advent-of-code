package de.havox_design.aoc2022.day16

import Day16._
import org.scalatest.funsuite.AnyFunSuite

class Day16ScalaTest extends AnyFunSuite {
  test("Day 16 - Part 2") {
    println("Solution for part2: " + solvePart2("day16.txt"))
  }

  test("Day 16 - Part 2 - Sample") {
    assert(solvePart2("day16Sample.txt") == 1707)
  }
}
