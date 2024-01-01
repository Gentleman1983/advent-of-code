package de.havox_design.aoc2017.day07

import RecursiveCircus._
import org.scalatest.funsuite.AnyFunSuite

class Day07Test extends AnyFunSuite {
  def dayFileName = "de/havox_design/aoc2017/day07/day07.txt"

  test("Day 07 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 07 - Part 1 - Sample") {
    assert(solvePart1("de/havox_design/aoc2017/day07/day07Sample.txt") == "tknk")
  }

  test("Day 07 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 07 - Part 2 - Sample") {
    assert(solvePart2("de/havox_design/aoc2017/day07/day07Sample.txt") == 60)
  }
}
