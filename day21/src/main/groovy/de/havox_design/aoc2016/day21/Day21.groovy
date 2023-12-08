package de.havox_design.aoc2016.day21

import groovy.transform.Memoized

class Day21 {

    static String solvePart1(String filename, String password = 'abcdefgh') {
        def scrambles = readScrambles(filename)
        def str = password
                .toCharArray()
                .toList()

        for (scr in scrambles) {
            scr.scramble(str)
        }

        return str.join('')
    }

    static String solvePart2(String filename, String password = 'fbgdceah') {
        def scrambles = readScrambles(filename, true)
                .reverse()
        def str = password
                .toCharArray()
                .toList()

        for (scr in scrambles) {
            scr.scramble(str)
        }

        return str.join('')
    }

    private static List<Scramble> readScrambles(String filename, boolean reverse = false) {
        Day21
                .class
                .getResource("/${filename}")
                .readLines()
                .collect { interpret(it, reverse) }
    }

    private static Scramble interpret(String line, boolean reverse = false) {
        if (line.startsWith('swap position ')) {
            return handleSwapPosition(line)
        } else if (line.startsWith('swap letter ')) {
            return handleSwapLetter(line)
        } else if (line.startsWith('rotate based on position of letter')) {
            return handleRotatePositionOfLetter(line, reverse)
        } else if (line.startsWith('rotate ')) {
            return handleRotate(line, reverse)
        } else if (line.startsWith('reverse positions ')) {
            return handleReversePositions(line)
        } else if (line.startsWith('move position ')) {
            return handleMovePosition(line, reverse)
        } else {
            throw new IllegalArgumentException("Unknown instruction '$line'")
        }
    }

    private static Closure handleSwapPosition(String line) {
        def m = line =~ /swap position (\d+) with position (\d+)/
        int from = m[0][1] as int
        int to = m[0][2] as int
        return (chars) -> {
            swap(chars, from, to)
        }
    }

    private static Closure handleSwapLetter(String line) {
        def m = line =~ /swap letter (\w+) with letter (\w+)/
        char from = m[0][1] as char
        char to = m[0][2] as char
        return (chars) -> {
            swap(chars, chars.indexOf(from), chars.indexOf(to))
        }
    }

    private static Closure handleRotatePositionOfLetter(String line, reverse) {
        def m = line =~ /rotate based on position of letter (\w+)/
        char c = m[0][1] as char
        return (chars) -> {
            if (reverse) {
                reverseFromPosition(chars.indexOf(c)).times {
                    rotateLeft(chars)
                }
            } else {
                int idx = chars.indexOf(c)
                (1 + idx + (idx >= 4 ? 1 : 0)).times {
                    rotateRight(chars)
                }
            }
        }
    }

    private static Closure handleRotate(String line, boolean reverse) {
        def m = line =~ /rotate (left|right) (\d+) step[s]?/
        boolean isLeft = m[0][1] == 'left'
        int steps = m[0][2] as int
        return (chars) -> {
            steps.times {
                if (!reverse) {
                    if (isLeft) {
                        rotateLeft(chars)
                    } else {
                        rotateRight(chars)
                    }
                } else {
                    if (isLeft) {
                        rotateRight(chars)
                    } else {
                        rotateLeft(chars)
                    }
                }
            }
        }
    }

    private static Closure<Void> handleReversePositions(String line) {
        def m = line =~ /reverse positions (\d+) through (\d+)/
        int from = m[0][1] as int
        int to = m[0][2] as int
        int diff = to - from
        int steps = (diff / 2 + diff % 2)
        return (chars) -> {
            for (int i = 0; i < steps; ++i) {
                swap(chars, i + from, to - i)
            }
        }
    }

    private static Closure handleMovePosition(String line, reverse) {
        def m = line =~ /move position (\d+) to position (\d+)/
        int from = m[0][1] as int
        int to = m[0][2] as int
        return (chars) -> {
            if (!reverse) {
                movePosition(chars, from, to)
            } else {
                movePosition(chars, to, from)
            }
        }
    }

    private static void swap(List<Character> chars, int from, int to) {
        char t = chars[from]
        chars[from] = chars[to]
        chars[to] = t
    }

    private static void movePosition(List<Character> chars, int from, int to) {
        Character c = chars.removeAt(from)
        chars.add(to, c)
    }

    private static void rotateLeft(List<Character> chars) {
        Character c = chars.removeAt(0)
        chars.add(c)
    }

    private static void rotateRight(List<Character> chars) {
        Character chr = chars.removeAt(chars.size() - 1)
        chars.add(0, chr)
    }

    @FunctionalInterface
    private static interface Scramble {
        void scramble(List<Character> chars)
    }

    @Memoized
    private static int reverseFromPosition(int i) {
        if (i % 2 == 1) {
            for (int j = 0; j < 8; ++j) {
                if ((j * 2 + 1) % 8 == i) {
                    return (8 + i - j) % 8
                }
            }
        } else {
            for (int j = 4; j < 8; ++j) {
                if ((j * 2 + 2) % 8 == i) {
                    return (8 + i - j) % 8
                }
            }
        }
    }
}
