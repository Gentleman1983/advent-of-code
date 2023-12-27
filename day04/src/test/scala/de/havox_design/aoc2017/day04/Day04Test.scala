package de.havox_design.aoc2017.day04

import Day04._
import org.scalatest.funsuite.AnyFunSuite

class Day04Test extends AnyFunSuite {
  def dayFileName = "day04.txt"

  test("Day 04 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 04 - Part 1 - Sample 1") {
    assert(solvePart1("part1sample1.txt") == 1)
  }

  test("Day 04 - Part 1 - Sample 2") {
    assert(solvePart1("part1sample2.txt") == 0)
  }

  test("Day 04 - Part 1 - Sample 3") {
    assert(solvePart1("part1sample3.txt") == 1)
  }

  test("Day 04 - Part 1 - Sample 4") {
    assert(solvePart1("part1sample4.txt") == 2)
  }

  test("Day 04 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }
}
