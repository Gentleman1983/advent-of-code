package de.havox_design.aoc2015.day22;

import java.util.ArrayList;
import java.util.List;

public  class StateWrapper<StateT> {

    private final StateT state;

    private final StateWrapper<StateT> predecessor;

    StateWrapper(final StateT state, final StateWrapper<StateT> predecessor) {
        this.state = state;
        this.predecessor = predecessor;
    }

    public StateT getState() {
        return state;
    }

    public StateWrapper<StateT> getPredecessor() {
        return predecessor;
    }

    public List<StateT> getStates() {
        final List<StateT> stateList;
        if (predecessor == null) {
            stateList = new ArrayList<>();
        } else {
            stateList = predecessor.getStates();
        }
        stateList.add(state);
        return stateList;
    }
}
