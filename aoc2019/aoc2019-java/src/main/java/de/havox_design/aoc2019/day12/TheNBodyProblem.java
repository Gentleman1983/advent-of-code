package de.havox_design.aoc2019.day12;

import de.havox_design.aoc.utils.java.AoCFunctionality;
import de.havox_design.aoc.utils.kotlin.model.positions.Position3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TheNBodyProblem implements AoCFunctionality {
    private final List<String> input;
    private final List<Position3d<Integer>> initialCoordinates;


    public TheNBodyProblem(String fileName) {
        this.input = readData(fileName);
        this.initialCoordinates = getInitialMoonCoordinates();
    }

    public static long processTask1(String fileName) {
        TheNBodyProblem instance = new TheNBodyProblem(fileName);
        return instance.processTask1();
    }

    public static long processTask2(String fileName) {
        TheNBodyProblem instance = new TheNBodyProblem(fileName);
        return instance.processTask2();
    }

    public long processTask1() {
        List<Position3d<Integer>> moonCoordinates = getInitialMoonCoordinates();
        List<Position3d<Integer>> velocities = new ArrayList<>(
                List.of(
                        new Position3d<>(0, 0, 0),
                        new Position3d<>(0, 0, 0),
                        new Position3d<>(0, 0, 0),
                        new Position3d<>(0, 0, 0)
                )
        );
        int totalEnergyInSystem = 0;

        for (int i = 0; i < 1000; i++) {
            updateVelocities(moonCoordinates, velocities);
            updateCoordinates(moonCoordinates, velocities);
        }

        for (int i = 0; i < moonCoordinates.size(); i++) {
            Position3d<Integer> moonPos = moonCoordinates.get(i);
            Position3d<Integer> velocity = velocities.get(i);

            int potentialEnergy = Math.abs(moonPos.getX()) + Math.abs(moonPos.getY()) + Math.abs(moonPos.getZ());
            int kineticEnergy = Math.abs(velocity.getX()) + Math.abs(velocity.getY()) + Math.abs(velocity.getZ());

            totalEnergyInSystem += potentialEnergy * kineticEnergy;
        }

        return totalEnergyInSystem;
    }

    @SuppressWarnings({"squid:S3358", "squid:S3776", "squid:S6541"})
    public long processTask2() {
        List<Position3d<Integer>> moonCoordinates = getInitialMoonCoordinates();
        List<Position3d<Integer>> velocities = new ArrayList<>(
                List.of(
                        new Position3d<>(0, 0, 0),
                        new Position3d<>(0, 0, 0),
                        new Position3d<>(0, 0, 0),
                        new Position3d<>(0, 0, 0)
                )
        );
        int zeroVelStepCountX = 1;
        int zeroVelStepCountY = 1;
        int zeroVelStepCountZ = 1;

        updateVelocities(moonCoordinates, velocities);
        updateCoordinates(moonCoordinates, velocities);

        while (!foundZeroVelX(moonCoordinates, velocities)) {
            zeroVelStepCountX++;

            for (int i = 0; i < moonCoordinates.size(); i++) {
                for (int j = 0; j < moonCoordinates.size(); j++) {
                    if (i != j) {
                        int x1 = moonCoordinates.get(i).getX();
                        int x2 = moonCoordinates.get(j).getX();
                        int v1x = velocities.get(i).getX();

                        v1x = x1 > x2 ? v1x - 1 : (x1 == x2 ? v1x : v1x + 1);
                        velocities.set(i, new Position3d<>(v1x, 0, 0));
                    }
                }
            }

            updateCoordinates(moonCoordinates, velocities);
        }

        moonCoordinates = getInitialMoonCoordinates();
        updateVelocities(moonCoordinates, velocities);
        updateCoordinates(moonCoordinates, velocities);

        while (!foundZeroVelY(moonCoordinates, velocities)) {
            zeroVelStepCountY++;

            for (int i = 0; i < moonCoordinates.size(); i++) {
                for (int j = 0; j < moonCoordinates.size(); j++) {
                    if (i != j) {
                        int y1 = moonCoordinates.get(i).getY();
                        int y2 = moonCoordinates.get(j).getY();
                        int v1y = velocities.get(i).getY();

                        v1y = y1 > y2 ? v1y - 1 : (y1 == y2 ? v1y : v1y + 1);
                        velocities.set(i, new Position3d<>(0, v1y, 0));
                    }
                }
            }

            updateCoordinates(moonCoordinates, velocities);
        }

        moonCoordinates = getInitialMoonCoordinates();
        updateVelocities(moonCoordinates, velocities);
        updateCoordinates(moonCoordinates, velocities);

        while (!foundZeroVelZ(moonCoordinates, velocities)) {
            zeroVelStepCountZ++;

            for (int i = 0; i < moonCoordinates.size(); i++) {
                for (int j = 0; j < moonCoordinates.size(); j++) {
                    if (i != j) {
                        int z1 = moonCoordinates.get(i).getZ();
                        int z2 = moonCoordinates.get(j).getZ();
                        int v1z = velocities.get(i).getZ();

                        v1z = z1 > z2 ? v1z - 1 : (z1 == z2 ? v1z : v1z + 1);
                        velocities.set(i, new Position3d<>(0, 0, v1z));
                    }
                }
            }

            updateCoordinates(moonCoordinates, velocities);
        }

        return leastCommonMultiple(leastCommonMultiple(zeroVelStepCountX, zeroVelStepCountY), zeroVelStepCountZ);
    }

    private void updateCoordinates(List<Position3d<Integer>> coordinates, List<Position3d<Integer>> velocities) {
        for (int i = 0; i < coordinates.size(); i++) {
            Position3d<Integer> currentMoonPos = coordinates.get(i);
            Position3d<Integer> currentMoonVelocity = velocities.get(i);

            coordinates.set(
                    i,
                    new Position3d<>(
                            currentMoonPos.getX() + currentMoonVelocity.getX(),
                            currentMoonPos.getY() + currentMoonVelocity.getY(),
                            currentMoonPos.getZ() + currentMoonVelocity.getZ()
                    )
            );
        }
    }

    @SuppressWarnings({"squid:S3358", "squid:S3776"})
    private void updateVelocities(List<Position3d<Integer>> coordinates, List<Position3d<Integer>> velocities) {
        for (int i = 0; i < coordinates.size(); i++) {
            for (int j = 0; j < coordinates.size(); j++) {
                if (i != j) {
                    int x1 = coordinates.get(i).getX();
                    int y1 = coordinates.get(i).getY();
                    int z1 = coordinates.get(i).getZ();

                    int x2 = coordinates.get(j).getX();
                    int y2 = coordinates.get(j).getY();
                    int z2 = coordinates.get(j).getZ();

                    int v1x = velocities.get(i).getX();
                    int v1y = velocities.get(i).getY();
                    int v1z = velocities.get(i).getZ();

                    v1x = x1 > x2 ? v1x - 1 : (x1 == x2 ? v1x : v1x + 1);
                    v1y = y1 > y2 ? v1y - 1 : (y1 == y2 ? v1y : v1y + 1);
                    v1z = z1 > z2 ? v1z - 1 : (z1 == z2 ? v1z : v1z + 1);

                    velocities.set(i, new Position3d<>(v1x, v1y, v1z));
                }
            }
        }
    }

    private List<Position3d<Integer>> getInitialMoonCoordinates() {
        List<Position3d<Integer>> coordinates = new ArrayList<>();

        for (String rawCoordinate : input) {
            int x = Integer.parseInt(rawCoordinate.substring(rawCoordinate.indexOf('=') + 1, rawCoordinate.indexOf(',')));
            int y = Integer.parseInt(rawCoordinate.substring(rawCoordinate.indexOf('y') + 2, rawCoordinate.indexOf(',', rawCoordinate.indexOf('y'))));
            int z = Integer.parseInt(rawCoordinate.substring(rawCoordinate.indexOf('z') + 2, rawCoordinate.indexOf('>')));

            coordinates.add(new Position3d<>(x, y, z));
        }

        return coordinates;
    }

    private boolean foundZeroVelX(List<Position3d<Integer>> moonCoordinates, List<Position3d<Integer>> velocities) {
        for (Position3d<Integer> velocity : velocities) {
            if (velocity.getX() != 0) {
                return false;
            }
        }

        for (int i = 0; i < moonCoordinates.size(); i++) {
            if (!Objects.equals(moonCoordinates.get(i).getX(), initialCoordinates.get(i).getX())) {
                return false;
            }
        }

        return true;
    }

    private boolean foundZeroVelY(List<Position3d<Integer>> moonCoordinates, List<Position3d<Integer>> velocities) {
        for (Position3d<Integer> velocity : velocities) {
            if (velocity.getY() != 0) {
                return false;
            }
        }

        for (int i = 0; i < moonCoordinates.size(); i++) {
            if (!Objects.equals(moonCoordinates.get(i).getY(), initialCoordinates.get(i).getY())) {
                return false;
            }
        }

        return true;
    }

    private boolean foundZeroVelZ(List<Position3d<Integer>> moonCoordinates, List<Position3d<Integer>> velocities) {
        for (Position3d<Integer> velocity : velocities) {
            if (velocity.getZ() != 0) {
                return false;
            }
        }

        for (int i = 0; i < moonCoordinates.size(); i++) {
            if (!Objects.equals(moonCoordinates.get(i).getZ(), initialCoordinates.get(i).getZ())) {
                return false;
            }
        }

        return true;
    }

    private long leastCommonMultiple(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }

        long absNumber1 = Math.abs(number1);
        long absNumber2 = Math.abs(number2);
        long absHigherNumber = Math.max(absNumber1, absNumber2);
        long absLowerNumber = Math.min(absNumber1, absNumber2);
        long lcm = absHigherNumber;

        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }

        return lcm;
    }
}
