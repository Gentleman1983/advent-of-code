package de.havox_design.aoc2016.day11;

import static de.havox_design.aoc2016.day11.State.*;

import de.havox_design.aoc2016.utils.DataReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day11 {
    private final List<String> input;

    private int visitedNodeCount = 0;

    public Day11(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day11 instance = new Day11(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day11 instance = new Day11(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return solve(input);
    }

    public long solvePart2() {
        List<String> part2Input = new ArrayList<>(input);
        part2Input.set(0, part2Input.get(0) + " AND an elerium generator, an elerium-compatible microchip, "
                + "a dilithium generator, a dilithium-compatible microchip.");

        return solve(part2Input);
    }

    @SuppressWarnings({"squid:S3655", "squid:S3776"})
    private long solve(List<String> lines) {
        State startState = new State(lines);

        PathResult<State> result = BreadthFirstSearch.run(
                        startState,
                        state -> {
                            visitedNodeCount++;

                            Set<State> nextStates = new HashSet<>();
                            for (int offset : new int[]{+1, -1}) {
                                int newElevatorPos = state.getElevator() + offset;

                                if (newElevatorPos < 0 || newElevatorPos >= FLOOR_COUNT) {
                                    continue;
                                }

                                int itemCount = state.getItemCount();

                                for (int a = 0; a < itemCount; a++) {
                                    if (state.getFloor(a) != state.getElevator()) {
                                        continue;
                                    }

                                    State nextState1 = state.move(a, newElevatorPos);

                                    if (nextState1.isSafe()) {
                                        nextStates.add(nextState1);
                                    }

                                    for (int b = a + 1; b < itemCount; b++) {
                                        if (state.getFloor(b) != state.getElevator()) {
                                            continue;
                                        }

                                        State nextState2 = nextState1.move(b, newElevatorPos);

                                        if (nextState2.isSafe()) {
                                            nextStates.add(nextState2);
                                        }
                                    }
                                }
                            }

                            return nextStates;
                        },
                        State::isTerminal
                )
                .get();

        return result.getDistance();
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
