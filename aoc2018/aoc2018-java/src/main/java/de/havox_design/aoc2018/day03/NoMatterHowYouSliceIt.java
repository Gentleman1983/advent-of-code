package de.havox_design.aoc2018.day03;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoMatterHowYouSliceIt implements AoCFunctionality {
    private final List<String> input;

    public NoMatterHowYouSliceIt(String fileName) {
        input = readData(fileName);
    }

    public static long processTask1(String fileName) {
        NoMatterHowYouSliceIt instance = new NoMatterHowYouSliceIt(fileName);
        return instance.processTask1();
    }

    public static int processTask2(String fileName) {
        NoMatterHowYouSliceIt instance = new NoMatterHowYouSliceIt(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        Map<Position2d<Integer>, Status> fabric = new HashMap<>();

        for (String line : input) {
            Claim claim = Claim.from(line);

            for(Position2d<Integer> position : claim.positions()) {
                Status status = fabric.get(position);

                if (status == null) {
                    fabric.put(position, Status.CLAIMED);
                }
                else {
                    fabric.put(position, Status.CONFLICTED);
                }
            }
        }

        return fabric
                .values()
                .stream()
                .filter(status -> status == Status.CONFLICTED)
                .count();
    }

    public int processTask2() {
        Map<Position2d<Integer>, Status> fabric = new HashMap<>();
        List<Claim> claims = new ArrayList<>();

        for (String line : input) {
            Claim claim = Claim.from(line);

            for(Position2d<Integer> position : claim.positions()) {
                Status status = fabric.get(position);

                if (status == null) {
                    fabric.put(position, Status.CLAIMED);
                }
                else {
                    fabric.put(position, Status.CONFLICTED);
                }
            }

            claims.add(claim);
        }

        Claim validClaim = claims
                .stream()
                .filter(claim ->
                claim
                        .positions()
                        .stream()
                        .allMatch(position ->
                                fabric.get(position) == Status.CLAIMED
                        )
                )
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Expected a claim to be valid."));

        return validClaim.id();
    }
}
