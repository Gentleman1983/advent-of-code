package de.havox_design.aoc2017.day20;

import de.havox_design.aoc.utils.DataReader;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day20 {
    private final List<String> input;

    public Day20(String fileName) {
        input = readData(fileName);
    }

    public static long solvePart1(String fileName) {
        Day20 instance = new Day20(fileName);
        return instance.solvePart1();
    }

    public static long solvePart2(String fileName) {
        Day20 instance = new Day20(fileName);
        return instance.solvePart2();
    }

    public long solvePart1() {
        return getClosestParticleToOrigin(parseParticles());
    }

    public long solvePart2() {
        return countParticlesNotCollided(parseParticles(), 1000);
    }

    private int getClosestParticleToOrigin(List<Particle> particles) {
        return IntStream
                .range(0, particles.size())
                .boxed()
                .min(Comparator.comparing(i -> particles.get(i).getTotalAcceleration()))
                .orElseThrow(() -> new IllegalArgumentException("Expected particle"));
    }

    private int countParticlesNotCollided(List<Particle> particles, int iterations) {
        int lastCount = -1;
        for (int i = 1; lastCount != particles.size() || i <= iterations; i++) {
            int t = i;

            lastCount = particles.size();
            particles
                    .stream()
                    .collect(Collectors.groupingBy(p -> p.positionAt(t)))
                    .values()
                    .stream()
                    .filter(v -> v.size() > 1)
                    .forEach(particles::removeAll);
        }

        return particles
                .size();
    }

    private List<Particle> parseParticles() {
        return input
                .stream()
                .map(Particle::from)
                .toList();
    }

    private List<String> readData(String fileName) {
        return DataReader.readData(fileName, MainClass.class);
    }
}
