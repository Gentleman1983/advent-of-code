package de.havox_design.aoc2017;

import de.havox_design.aoc2017.day01.InverseCaptcha;
import de.havox_design.aoc2017.day02.CorruptionChecksum;
import de.havox_design.aoc2017.day03.SpiralMemory;
import de.havox_design.aoc2017.day11.HexEd;
import de.havox_design.aoc2017.day12.DigitalPlumber;
import de.havox_design.aoc2017.day13.PacketScanners;
import de.havox_design.aoc2017.day15.DuelingGenerators;
import de.havox_design.aoc2017.day16.PermutationPromenade;
import de.havox_design.aoc2017.day17.SpinLock;
import de.havox_design.aoc2017.day18.Duet;
import de.havox_design.aoc2017.day19.ASeriesOfTubes;
import de.havox_design.aoc2017.day20.ParticleSwarm;
import de.havox_design.aoc2017.day21.FractalArt;
import de.havox_design.aoc2017.day22.SporificaVirus;
import de.havox_design.aoc2017.day23.CoprocessorConflagration;
import de.havox_design.aoc2017.day24.ElectromagneticMoat;
import de.havox_design.aoc2017.day25.TheHaltingProblem;

import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.logging.Logger;

public class MainClass {
    private static final Logger LOGGER = Logger.getLogger(de.havox_design.aoc2017.MainClass.class.getName());

    public static void main(String[] args) {
        boolean daysSelected = Arrays
                .stream(args)
                .anyMatch(argument -> argument.startsWith("day"));

        int day = 1;
        solveDay(
                day,
                d -> String.valueOf(InverseCaptcha.solvePart1(getFileName(d))),
                d -> String.valueOf(InverseCaptcha.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 2;
        solveDay(
                day,
                d -> String.valueOf(CorruptionChecksum.solvePart1(getFileName(d))),
                d -> String.valueOf(CorruptionChecksum.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 3;
        solveDay(
                day,
                d -> String.valueOf(SpiralMemory.solvePart1(getFileName(d))),
                d -> String.valueOf(SpiralMemory.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 11;
        solveDay(
                day,
                d -> String.valueOf(HexEd.solvePart1(getFileName(d))),
                d -> String.valueOf(HexEd.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 12;
        solveDay(
                day,
                d -> String.valueOf(DigitalPlumber.solvePart1(getFileName(d))),
                d -> String.valueOf(DigitalPlumber.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 13;
        solveDay(
                day,
                d -> String.valueOf(PacketScanners.solvePart1(getFileName(d))),
                d -> String.valueOf(PacketScanners.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 15;
        solveDay(
                day,
                d -> String.valueOf(DuelingGenerators.solvePart1(getFileName(d))),
                d -> String.valueOf(DuelingGenerators.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 16;
        String day16initialWord = "abcdefghijklmnop";
        int day16iterations = 1000000000;
        solveDay(
                day,
                d -> PermutationPromenade.solvePart1(getFileName(d), day16initialWord),
                d -> PermutationPromenade.solvePart2(getFileName(d), day16initialWord, day16iterations),
                daysSelected,
                args
        );

        day = 17;
        solveDay(
                day,
                d -> String.valueOf(SpinLock.solvePart1(getFileName(d))),
                d -> String.valueOf(SpinLock.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 18;
        solveDay(
                day,
                d -> String.valueOf(Duet.solvePart1(getFileName(d))),
                d -> String.valueOf(Duet.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 19;
        solveDay(
                day,
                d -> ASeriesOfTubes.solvePart1(getFileName(d)),
                d -> String.valueOf(ASeriesOfTubes.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 20;
        solveDay(
                day,
                d -> String.valueOf(ParticleSwarm.solvePart1(getFileName(d))),
                d -> String.valueOf(ParticleSwarm.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 21;
        solveDay(
                day,
                d -> String.valueOf(FractalArt.solvePart1(getFileName(d), 5)),
                d -> String.valueOf(FractalArt.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 22;
        solveDay(
                day,
                d -> String.valueOf(SporificaVirus.solvePart1(getFileName(d), 10000)),
                d -> String.valueOf(SporificaVirus.solvePart2(getFileName(d), 10000000)),
                daysSelected,
                args
        );

        day = 23;
        solveDay(
                day,
                d -> String.valueOf(CoprocessorConflagration.solvePart1(getFileName(d))),
                d -> String.valueOf(CoprocessorConflagration.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 24;
        solveDay(
                day,
                d -> String.valueOf(ElectromagneticMoat.solvePart1(getFileName(d))),
                d -> String.valueOf(ElectromagneticMoat.solvePart2(getFileName(d))),
                daysSelected,
                args
        );

        day = 25;
        solveDay(
                day,
                d -> String.valueOf(TheHaltingProblem.solvePart1(getFileName(d))),
                d -> "Merry X-MAS!!!",
                daysSelected,
                args
        );
    }

    private static void solveDay(
            int day,
            IntFunction<String> solutionPart1,
            IntFunction<String> solutionPart2,
            boolean daysSelected,
            String[] args
    ) {
        if (!daysSelected || Arrays.asList(args).contains(getDayString(day).toLowerCase())) {
            LOGGER.info(() -> getDayString(day) + ": Solution part 1: " + solutionPart1.apply(day));
            LOGGER.info(() -> getDayString(day) + ": Solution part 2: " + solutionPart2.apply(day));
        }
    }

    private static String getFileName(Integer day) {
        String dayString = day < 10 ? "0" + day : day.toString();

        return "de/havox_design/aoc2017/day" + dayString + "/day" + dayString + ".txt";
    }

    private static String getDayString(Integer day) {
        return day < 10 ? "Day0" + day : "Day" + day;
    }
}
