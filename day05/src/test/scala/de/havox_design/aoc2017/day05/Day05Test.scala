package de.havox_design.aoc2017.day05

import Day05._
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Day05Test extends AnyFunSuite {
  def dayFileName = "day05.txt"

  test("Day 05 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 05 - Part 1 - Sample") {
    assert(solvePart1("part1sample.txt") == 5)
  }

  test("Day 05 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 05 - Part 2 - Sample 1") {
    assert(solvePart2("part2sample1.txt") == 0)
  }
}
