package de.havox_design.aoc2016.day17;

import com.google.common.hash.Hashing;
import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.search.BreadthFirstSearch;
import de.havox_design.aoc.utils.java.search.PathResult;
import de.havox_design.aoc.utils.kotlin.model.directions.UDLRDirection;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TwoStepsForward implements AoCFunctionality {
    private static final int SIZE = 4;
    private static final Tile START_TILE = new Tile(0, 0);
    private static final Tile TARGET_TILE = new Tile(3, 3);

    private final String input;

    public TwoStepsForward(String fileName) {
        input = readData(fileName).get(0);
    }

    public static String solvePart1(String fileName) {
        TwoStepsForward instance = new TwoStepsForward(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        TwoStepsForward instance = new TwoStepsForward(fileName);
        return instance.solvePart2();
    }

    public String solvePart1() {
        return getResultMap()
                .values()
                .stream()
                .filter(res -> res.getNode().tile().equals(TARGET_TILE))
                .min(Comparator.comparing(PathResult::getDistance))
                .orElseThrow()
                .getNode()
                .path();
    }

    public long solvePart2() {
        return getResultMap()
                .values()
                .stream()
                .filter(res -> res.getNode().tile().equals(TARGET_TILE))
                .mapToLong(PathResult::getDistance)
                .max()
                .orElseThrow();
    }

    private Map<State, PathResult<State>> getResultMap() {
        return BreadthFirstSearch.run(new State(START_TILE, ""), state -> getNextFeasibleStates(state, input));
    }

    private List<State> getNextFeasibleStates(State state, String passCode) {
        if (state.tile().equals(TARGET_TILE)) {
            return List.of();
        }

        String hash = getMd5Hash(passCode + state.path());

        var result = new ArrayList<State>(4);
        for (int i = 0; i < 4; i++) {
            Tile neighbor = state.tile().neighbor(UDLRDirection.values()[i]);
            boolean doorIsOpen = neighbor.isValid(SIZE, SIZE)
                    && hash.charAt(i) >= 'b'
                    && hash.charAt(i) <= 'f';
            if (doorIsOpen) {
                result.add(new State(neighbor, state.path() + UDLRDirection.values()[i].getSymbol()));
            }
        }

        return result;
    }

    @SuppressWarnings({ "deprecated", "squid:S1874", "squid:S4790"})
    private static String getMd5Hash(String s) {
        return Hashing.md5().hashString(s, StandardCharsets.UTF_8).toString();
    }
}
