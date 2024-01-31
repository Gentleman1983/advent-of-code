package de.havox_design.aoc.utils.java.model.programs.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.token.TokenProvider;

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
