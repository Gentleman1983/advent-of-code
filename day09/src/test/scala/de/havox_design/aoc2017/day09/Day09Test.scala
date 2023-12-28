package de.havox_design.aoc2017.day09

import Day09._
import org.scalatest.funsuite.AnyFunSuite

class Day09Test extends AnyFunSuite {
  def dayFileName = "day09.txt"

  test("Day 09 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 09 - Part 1 - Sample") {
    assert(solvePart1("sample.txt") == 0)
  }

  test("Day 09 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 09 - Part 2 - Sample") {
    assert(solvePart2("sample.txt") == 0)
  }
}
