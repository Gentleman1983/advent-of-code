package de.havox_design.aoc.utils.java.model.programs.duet.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.duet.token.TokenProvider;

import java.math.BigInteger;

public class Add implements Instruction {
    private final Token addend;
    private final String variableName;

    public Add(final String variableName, final String value) {
        this.variableName = variableName;
        this.addend = TokenProvider.createToken(value);
    }

    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(variableName);
        currentValue = currentValue.add(addend.intValue(state));
        state.setValue(variableName, currentValue);
    }
}
