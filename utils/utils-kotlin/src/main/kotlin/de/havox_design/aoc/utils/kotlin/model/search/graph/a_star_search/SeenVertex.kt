package de.havox_design.aoc.utils.kotlin.model.search.graph.a_star_search

data class SeenVertex<K>(val score: Int, val prev: K?)
