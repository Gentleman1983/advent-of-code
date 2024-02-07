package de.havox_design.aoc2018.day09;

import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.IntStream;

public class MarbleMania implements AoCFunctionality {
    private final GameResult input;

    public MarbleMania(String fileName) {
        GameParser parser = new GameParser();
        input = parser.parse(readString(fileName));
    }

    public static long processTask1(String fileName) {
        MarbleMania instance = new MarbleMania(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        MarbleMania instance = new MarbleMania(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        Iterator<Integer> marbleGenerator = IntStream
                .rangeClosed(0, input.valueLastMarble())
                .iterator();

        return propagate(marbleGenerator);
    }

    public long processTask2() {
        return 0;
    }

    private long propagate(Iterator<Integer> marbleGenerator) {
        Field field = initField(marbleGenerator);

        while (marbleGenerator.hasNext()) {
            nextMove(field, marbleGenerator);
        }

        return field
                .playerScores()
                .stream()
                .mapToLong(Long::longValue)
                .max()
                .orElseThrow(() -> new IllegalStateException("No max value found..."));
    }

    private Field initField(Iterator<Integer> marbleGenerator) {
        Deque<Long> playerScores = new ArrayDeque<>();
        IntStream
                .range(0, input.numberOfPlayers())
                .forEach(i -> playerScores.add(0L));
        Deque<Integer> marbles = new ArrayDeque<>(input.valueLastMarble());

        marbles.add(marbleGenerator.next());

        return new Field(playerScores, marbles, 0);
    }

    private void nextMove(Field field, Iterator<Integer> marbleGenerator) {
        Deque<Long> playerScores = field.playerScores();
        int m = marbleGenerator.next();
        Deque<Integer> marbles = field.marbles();

        if (m % 23 == 0) {
            rotate(marbles, -7);
            playerScores.addFirst(playerScores.removeFirst() + m + marbles.remove());
        } else {
            rotate(marbles, 2);
            marbles.addFirst(m);
        }

        rotate(playerScores, 1);
    }

    private static <E> void rotate(Deque<E> queue, int distance) {
        distance = distance % queue.size();

        if (distance < 0) {
            for (int i = 0; i > distance; i--) {
                queue.addFirst(queue.removeLast());
            }
        } else {
            for (int i = 0; i < distance; i++) {
                queue.addLast(queue.removeFirst());
            }
        }
    }
}
