package de.havox_design.aoc2017.day06

import Day06._
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Day06Test extends AnyFunSuite {
  def dayFileName = "day06.txt"

  test("Day 06 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 06 - Part 1 - Sample 1") {
    assert(solvePart1("part1sample1.txt") == 0)
  }

  test("Day 06 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 06 - Part 2 - Sample 1") {
    assert(solvePart2("part2sample1.txt") == 0)
  }
}
