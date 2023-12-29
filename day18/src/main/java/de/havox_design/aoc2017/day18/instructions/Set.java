package de.havox_design.aoc2017.day18.instructions;

import de.havox_design.aoc2017.day18.State;
import de.havox_design.aoc2017.day18.token.Token;
import de.havox_design.aoc2017.day18.token.TokenProvider;

public class Set implements Instruction {
    private final Token value;
    private final String variableName;

    public Set(final String variableName, final String value) {
        this.variableName = variableName;
        this.value = TokenProvider.createToken(value);
    }

    @Override
    public void execute(final State state) {
        state.setValue(variableName, value.intValue(state));
    }

    public Token getValue() {
        return value;
    }
}
