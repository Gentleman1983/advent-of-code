package de.havox_design.aoc2019.day23;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.java.exceptions.AdventOfCodeException;
import de.havox_design.aoc.utils.java.model.computer.aoc2019.IntComputer;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class CategorySix implements AoCFunctionality {
    private static final String VALUE_DELIMITER = ",";
    private static final int COMPUTERS = 50;

    private final List<Long> input;

    public CategorySix(String fileName) {
        input = Arrays
                .stream(readString(fileName).split(VALUE_DELIMITER))
                .map(value -> Long.parseLong(value.trim()))
                .toList();
    }

    public static long processTask1(String fileName) {
        CategorySix instance = new CategorySix(fileName);

        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        CategorySix instance = new CategorySix(fileName);

        return instance.processTask2();
    }

    public long processTask1() {
        return computePacketValueTo(255, true);
    }

    public long processTask2() {
        return computePacketValueTo(255, false);
    }

    private long computePacketValueTo(int targetAddress, boolean firstMessageToTarget) {
        Map<Integer, BlockingQueue<Long>> in = new HashMap<>();
        List<BlockingQueue<Long>> out = new ArrayList<>();

        for (int i = 0; i < COMPUTERS; i++) {
            in.put(i, new LinkedBlockingQueue<>());
            out.add(new LinkedBlockingQueue<>());
            in.get(i).add((long) i);
            new IntComputer(input, in.get(i), out.get(i)).runAsync();
        }

        in.put(targetAddress, new LinkedBlockingQueue<>());

        return runComputers(in, out, targetAddress, firstMessageToTarget);
    }

    @SuppressWarnings("squid:S3776")
    private long runComputers(Map<Integer, BlockingQueue<Long>> receivers, List<BlockingQueue<Long>> senders, int targetAddress, boolean firstMessageToTarget) {
        long lastYSent = 0L;

        while (true) {
            IntStream.range(0, COMPUTERS)
                    .parallel()
                    .forEach(to -> receivers.get(to).add(-1L));
            boolean idle = true;

            for (int from = 0; from < COMPUTERS; from++) {
                BlockingQueue<Long> sender = senders.get(from);
                Integer to = Optional
                        .ofNullable(sender.poll())
                        .map(Long::intValue)
                        .orElse(null);

                if (to != null) {
                    if (to == targetAddress) {
                        receivers.get(to).clear();
                    }

                    long y = dispatchPacket(to, receivers, sender);

                    if (firstMessageToTarget && to == targetAddress) {
                        return y;
                    }
                }
            }

            if ( !firstMessageToTarget && idle && isIdle( receivers, senders ) ) {
                BlockingQueue<Long> sender = receivers.get( targetAddress );
                long y = dispatchPacket( 0, receivers, sender );

                if ( lastYSent == y ) {
                    return y;
                }

                lastYSent = y;
            }
        }
    }

    @SuppressWarnings("squid:S2142")
    private long dispatchPacket(int to, Map<Integer, BlockingQueue<Long>> receivers, BlockingQueue<Long> sender) {
        try {
            BlockingQueue<Long> receiver = receivers.get(to);
            long x = sender.take();
            long y = sender.take();

            receiver.add(x);
            receiver.add(y);

            return y;
        } catch (InterruptedException e) {
            throw new AdventOfCodeException(e);
        }
    }

    private boolean isIdle(Map<Integer, BlockingQueue<Long>> in, List<BlockingQueue<Long>> out) {
        return IntStream
                .range(0, COMPUTERS)
                .parallel()
                .allMatch(address -> blocked(in.get(address), out.get(address)));
    }

    private boolean blocked(BlockingQueue<Long> in, BlockingQueue<Long> out) {
        return in.isEmpty() && out.isEmpty();
    }
}
