package de.havox_design.aoc2017.day24;

public class Component {
    private final int portA;
    private final int portB;
    private final Component turned;

    public Component(final int portA, final int portB) {
        this(portA, portB, new Component(portB, portA, null));
    }

    private Component(final int portA, final int portB, final Component turned) {
        this.portA = portA;
        this.portB = portB;
        this.turned = turned;
    }

    public Component turn() {
        return turned;
    }

    public int getPortA() {
        return portA;
    }

    public int getPortB() {
        return portB;
    }

    public int getStrength() {
        return portA + portB;
    }

    public Component rotateToFit(final int currentPort) {
        if (portA == currentPort) {
            return this;
        } else if (portB == currentPort) {
            return turned;
        } else {
            return null;
        }
    }
}
