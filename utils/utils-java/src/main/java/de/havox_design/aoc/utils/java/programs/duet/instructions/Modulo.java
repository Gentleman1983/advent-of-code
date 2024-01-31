package de.havox_design.aoc.utils.java.programs.duet.instructions;

import de.havox_design.aoc.utils.java.programs.duet.State;
import de.havox_design.aoc.utils.java.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.programs.duet.token.TokenProvider;

import java.math.BigInteger;

public class Modulo implements Instruction {
    private final Token modulus;
    private final String variableName;

    public Modulo(final String variableName, final String value) {
        this.variableName = variableName;
        this.modulus = TokenProvider.createToken(value);
    }

    @Override
    public void execute(final State state) {
        BigInteger currentValue = state.getValue(variableName);

        currentValue = currentValue.mod(modulus.intValue(state));
        state.setValue(variableName, currentValue);
    }
}
