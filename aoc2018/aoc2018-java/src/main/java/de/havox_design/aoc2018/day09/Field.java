package de.havox_design.aoc2018.day09;

import java.util.Deque;

public record Field(Deque<Long> playerScores, Deque<Integer> marbles, int round) {}
