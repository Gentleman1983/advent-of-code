package de.havox_design.aoc.utils.java.programs.duet.instructions;

import de.havox_design.aoc.utils.java.programs.duet.State;
import de.havox_design.aoc.utils.java.programs.duet.token.Token;
import de.havox_design.aoc.utils.java.programs.duet.token.TokenProvider;

import java.math.BigInteger;

public class JumpIfGreaterZero implements Instruction {
    private final Token condition;
    private final Token offset;

    public JumpIfGreaterZero(final String condition, final String offset) {
        this.condition = TokenProvider.createToken(condition);
        this.offset = TokenProvider.createToken(offset);
    }

    @Override
    public void execute(final State state) {
        final BigInteger intValue = condition.intValue(state);

        if (intValue.signum() > 0) {
            state.jump(offset.intValue(state).intValueExact());
        }
    }
}
