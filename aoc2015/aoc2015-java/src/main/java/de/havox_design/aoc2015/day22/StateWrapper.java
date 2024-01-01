package de.havox_design.aoc2015.day22;

import java.util.ArrayList;
import java.util.List;

public  class StateWrapper<S> {

    private final S state;

    private final StateWrapper<S> predecessor;

    StateWrapper(final S state, final StateWrapper<S> predecessor) {
        this.state = state;
        this.predecessor = predecessor;
    }

    public S getState() {
        return state;
    }

    public StateWrapper<S> getPredecessor() {
        return predecessor;
    }

    public List<S> getStates() {
        final List<S> stateList;
        if (predecessor == null) {
            stateList = new ArrayList<>();
        } else {
            stateList = predecessor.getStates();
        }
        stateList.add(state);
        return stateList;
    }
}
