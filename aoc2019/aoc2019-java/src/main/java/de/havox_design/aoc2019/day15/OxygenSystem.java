package de.havox_design.aoc2019.day15;

import static de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections.*;
import static java.lang.Math.min;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.exceptions.AdventOfCodeException;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.UnaryOperator;

public class OxygenSystem implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";
    private static final long STUCK = 0L;
    private static final long MOVED = 1L;
    private static final long FOUND = 2L;
    private static final char ROOM = '.';
    private static final char WALL = '#';
    private static final char UNKNOWN = '?';
    private static final char OXYGEN = 'X';
    private static final Map<Long, Character> STATUS = Map.of(
            STUCK, WALL,
            MOVED, ROOM,
            FOUND, OXYGEN
    );
    private static final Map<FourDirections, Long> MOVE_COMMAND = Map.of(
            UP, 1L,
            DOWN, 2L,
            LEFT, 3L,
            RIGHT, 4L
    );
    private static final Map<Long, UnaryOperator<FourDirections>> ROTATE = Map.of(
            STUCK, FourDirections::turnLeft,
            MOVED, FourDirections::turnRight,
            FOUND, FourDirections::turnRight
    );
    private static final Map<FourDirections, UnaryOperator<Pair<Long, Long>>> MOVE_POSITION = Map.of(
            UP, pos -> Pair.of(pos.getLeft(), pos.getRight() - 1),
            DOWN, pos -> Pair.of(pos.getLeft(), pos.getRight() + 1),
            LEFT, pos -> Pair.of(pos.getLeft() - 1, pos.getRight()),
            RIGHT, pos -> Pair.of(pos.getLeft() + 1, pos.getRight())
    );

    private final List<Long> input;

    public OxygenSystem(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        OxygenSystem instance = new OxygenSystem(fileName);

        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        OxygenSystem instance = new OxygenSystem(fileName);

        return instance.processTask2();
    }

    public long processTask1() {
        return searchForOxygen();
    }

    public long processTask2() {
        return floodWithOxygen();
    }

    private Long searchForOxygen() {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        Map<Pair<Long, Long>, Pair<Character, Long>> maze = new HashMap<>();
        IntComputer.runComputer( input, in, out, true );
        Pair<Long, Long> droidPosition = exploreMaze( in, out, maze, Pair.of( 0L, 0L ) );

        return maze.get( droidPosition ).getRight();
    }

    private Long floodWithOxygen() {
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        Map<Pair<Long, Long>, Pair<Character, Long>> maze = new HashMap<>();
        IntComputer.runComputer( input, in, out, true );
        Pair<Long, Long> droidPosition = exploreMaze( in, out, maze, Pair.of( 0L, 0L ) );
        Map<Pair<Long, Long>, Pair<Character, Long>> invertedMaze = new HashMap<>();

        exploreMaze( in, out, invertedMaze, droidPosition );

        return invertedMaze.values()
                .stream()
                .map( Pair::getRight )
                .max( Long::compareTo )
                .orElseThrow();
    }

    @SuppressWarnings("squid:S2142")
    private Pair<Long, Long> exploreMaze( BlockingQueue<Long> in,
                                          BlockingDeque<Long> out,
                                          Map<Pair<Long, Long>, Pair<Character, Long>> maze,
                                          Pair<Long, Long> start
    ) {
        Pair<Long, Long> droidPosition = start;

        maze.put( droidPosition, Pair.of( ROOM, 0L ) );

        long status = 0L;
        long steps = 0L;
        FourDirections direction = RIGHT;

        try {
            do {
                Long movement = MOVE_COMMAND.get( direction );

                in.add( movement );
                status = out.take();

                Pair<Long, Long> mazePosition = MOVE_POSITION.get( direction )                        .apply( droidPosition );

                if ( status != STUCK ) {
                    droidPosition = mazePosition;
                    steps++;

                    long finalSteps = steps;

                    maze.computeIfAbsent(droidPosition, k -> Pair.of(UNKNOWN, finalSteps));
                    steps = maze.get( droidPosition ).getRight();
                }

                maze.put( mazePosition, Pair.of( STATUS.get( status ), steps ) );
                direction = ROTATE.get( status ).apply( direction );
            } while ( status != FOUND );
        } catch ( InterruptedException e ) {
            throw new AdventOfCodeException( e );
        }

        return droidPosition;
    }
}
