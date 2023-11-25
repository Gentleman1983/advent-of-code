package de.havox_design.aoc2015.day14;

import static de.havox_design.aoc2015.day14.ReindeerState.*;

public class Reindeer {
    private final String name;
    private final int flySpeed;
    private final int flyTime;
    private final int restTime;

    private ReindeerState currentState = ReindeerState.RESTING;
    private int timeInState = 0;
    private int distance = 0;


    public Reindeer(String name, int flySpeed, int flyTime, int restTime) {
        super();

        this.name = name;
        this.flySpeed = flySpeed;
        this.flyTime = flyTime;
        this.restTime = restTime;
    }

    public String getName() {
        return name;
    }

    public int getFlySpeed() {
        return flySpeed;
    }

    public int getFlyTime() {
        return flyTime;
    }

    public  int getRestTime() {
        return restTime;
    }

    public int getDistance() {
        return distance;
    }

    public void processSecond() {
        if(timeInState == 0) {
            currentState = ReindeerState.toggle(currentState);

            timeInState = currentState == FLYING ? flyTime : restTime;
        }

        if(currentState == FLYING) {
            distance += flySpeed;
        }

        timeInState--;
    }

    public void processSeconds(int seconds) {
        for(int i = 0; i < seconds; i++) {
            processSecond();
        }
    }
}
