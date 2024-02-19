package de.havox_design.aoc2018.day19;

import de.havox_design.aoc2018.day16.Instructions;

public record ProgramInstruction(Instructions instruction, int registerA, int registerB, int registerC) {}
