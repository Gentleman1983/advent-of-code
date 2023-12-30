package de.havox_design.aoc2017.day18.instructions;

import de.havox_design.aoc2017.day18.State;
import de.havox_design.aoc2017.day18.token.Token;
import de.havox_design.aoc2017.day18.token.TokenProvider;

import java.math.BigInteger;

public class Recover implements Instruction {
    private final Token value;
    public Recover(final String value) {
        this.value = TokenProvider.createToken(value);
    }

    @Override
    public void execute(final State state) {
        final BigInteger intValue = value.intValue(state);

        if (intValue.signum() != 0) {
            final BigInteger currentSound = state.getCurrentSound();
            state.recoverSound(currentSound);
        }
    }
}
