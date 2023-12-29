package de.havox_design.aoc2017.day18.instructions;

import de.havox_design.aoc2017.day18.State;
import de.havox_design.aoc2017.day18.token.Token;
import de.havox_design.aoc2017.day18.token.TokenProvider;

import java.math.BigInteger;

public class Multiply implements Instruction {

    private final Token factor;

    private final String variableName;

    public Multiply(final String variableName, final String value) {
        this.variableName = variableName;
        this.factor = TokenProvider.createToken(value);
    }

    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(variableName);

        currentValue = currentValue.multiply(factor.intValue(state));
        state.setValue(variableName, currentValue);
        state.countMultiplicationInstruction();
    }
}
