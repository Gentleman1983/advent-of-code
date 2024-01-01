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
        return getPortA() + getPortB();
    }

    public Component rotateToFit(final int currentPort) {
        if (getPortA() == currentPort) {
            return this;
        } else if (getPortB() == currentPort) {
            return turn();
        } else {
            return null;
        }
    }
}
