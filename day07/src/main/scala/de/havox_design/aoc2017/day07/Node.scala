package de.havox_design.aoc2017.day07

case class Node(name: String, weight: Int, total: Int, unbalanced: Boolean, children: Seq[Node])
