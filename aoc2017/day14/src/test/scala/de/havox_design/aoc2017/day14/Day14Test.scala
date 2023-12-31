package de.havox_design.aoc2017.day14

import Day14._
import org.scalatest.funsuite.AnyFunSuite

class Day14Test extends AnyFunSuite {
  def dayFileName = "day14.txt"

  test("Day 10 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 10 - Part 1 - Sample") {
    assert(solvePart1("day14Sample.txt") == 8108)
  }

  test("Day 10 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 10 - Part 2 - Sample") {
    assert(solvePart2("day14Sample.txt") == 1242)
  }
}
