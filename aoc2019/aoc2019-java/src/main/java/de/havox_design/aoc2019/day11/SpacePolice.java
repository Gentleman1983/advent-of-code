package de.havox_design.aoc2019.day11;

import static de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.exceptions.AdventOfCodeException;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;
import de.havox_design.aoc.utils.kotlin.model.coordinates.FourDirections;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

public class SpacePolice implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";
    private static final char ICON_BLACK = '.';
    private static final char ICON_WHITE = '#';

    private static final Map<Character, Long> PANEL_IN = Map.of( ICON_BLACK, 0L, ICON_WHITE, 1L );
    private static final Map<Long, Character> PANEL_OUT = Map.of( 0L, ICON_BLACK, 1L, ICON_WHITE );
    private static final Map<Long, Character> PAINT = Map.of( 0L, ICON_BLACK, 1L, ICON_WHITE );
    private static final Map<Long, UnaryOperator<FourDirections>> ROTATE = Map.of( 0L, FourDirections::turnLeft, 1L, FourDirections::turnRight );
    private static final Map<FourDirections, Function<Pair<Long, Long>, Pair<Long, Long>>> MOVE = Map.of(
            UP, pos -> Pair.of(pos.getLeft(), pos.getRight() - 1),
            DOWN, pos -> Pair.of(pos.getLeft(), pos.getRight() + 1),
            LEFT, pos -> Pair.of(pos.getLeft() - 1, pos.getRight()),
            RIGHT, pos -> Pair.of(pos.getLeft() + 1, pos.getRight())
    );

    private final List<Long> input;

    public SpacePolice(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        SpacePolice instance = new SpacePolice(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        SpacePolice instance = new SpacePolice(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        return solve( ICON_BLACK, grid -> grid.size());
    }

    public long processTask2() {
        return 0;
    }

    private long solve( Character start, ToLongFunction<Map<Pair<Long, Long>, Character>> computeResult ) {
        Pair<Long, Long> pos = Pair.of( 0L, 0L );
        Map<Pair<Long, Long>, Character> grid = new HashMap<>();
        BlockingQueue<Long> in = new LinkedBlockingQueue<>();
        BlockingDeque<Long> out = new LinkedBlockingDeque<>();
        Future<?> future = IntComputer.runComputer( input, in, out, true );

        FourDirections direction = UP;
        try {
            do {
                in.add( PANEL_IN.get( grid.getOrDefault( pos, start ) ) );

                final Long paint = readOutput( out, future );
                if ( paint == null ) {
                    break;
                }

                grid.put( pos, PANEL_OUT.get( paint ) );

                //move
                direction = ROTATE.get( out.take() ).apply( direction );
                pos = MOVE.get( direction ).apply( pos );

            } while ( true );
        } catch ( InterruptedException e ) {
            throw new AdventOfCodeException( e );
        }

        return computeResult.applyAsLong( grid );
    }

    public Long readOutput(final BlockingDeque<Long> output, final Future<?> writer)
            throws InterruptedException {
        Long x;
        do {
            x = output.poll( 50, MILLISECONDS );
        } while ( x == null && !writer.isDone() );
        return x;
    }
}
