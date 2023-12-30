package de.havox_design.aoc2017.day09

case class State(groupDepth: Int, groupScore: Int, garbage: Boolean, garbageScore: Int, ignoreNext: Boolean):
  def step(next: Char): State =
    next match
      case _ if ignoreNext => copy(ignoreNext = false)
      case '!' => copy(ignoreNext = true)
      case '>' => copy(garbage = false)
      case _ if garbage => copy(garbageScore = garbageScore + 1)
      case '{' => copy(groupDepth = groupDepth + 1, groupScore = groupScore + groupDepth)
      case '}' => copy(groupDepth = groupDepth - 1)
      case '<' => copy(garbage = true)
      case _ => this
