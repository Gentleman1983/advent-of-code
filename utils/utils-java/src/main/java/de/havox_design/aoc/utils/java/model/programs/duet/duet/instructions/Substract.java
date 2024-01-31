package de.havox_design.aoc.utils.java.model.programs.duet.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.duet.token.TokenProvider;

import java.math.BigInteger;

public class Substract implements Instruction {
    private final String varName;
    private final Token subtrahend;

    public Substract(final String varName, final String value) {
        this.varName = varName;
        this.subtrahend = TokenProvider.createToken(value);
    }

    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(varName);

        currentValue = currentValue.subtract(subtrahend.intValue(state));

        state.setValue(varName, currentValue);
    }
}
