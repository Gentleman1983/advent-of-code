package de.havox_design.aoc2022.day16;

public class ValveJava {

    private ValveJava[] tunnels;

    private int rate;

    private int index;

    private long mask;

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setTunnels(ValveJava[] tunnels) {
        this.tunnels = tunnels;
    }

    public void setIndex(int index) {
        this.index = index;
        this.mask = 1L << index;
    }

    public int getIndex() {
        return index;
    }

    public int getRate() {
        return rate;
    }

    public long getMask() {
        return mask;
    }

    public ValveJava[] getTunnels() {
        return tunnels;
    }
}
