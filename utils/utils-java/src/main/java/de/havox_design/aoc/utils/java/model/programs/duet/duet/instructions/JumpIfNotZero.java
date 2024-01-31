package de.havox_design.aoc.utils.java.model.programs.duet.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.duet.token.TokenProvider;

import java.math.BigInteger;

public class JumpIfNotZero implements Instruction {
    private final Token condition;
    private final Token offset;

    public JumpIfNotZero(final String cond, final String offs) {
        this.condition = TokenProvider.createToken(cond);
        this.offset = TokenProvider.createToken(offs);
    }

    @Override
    public void execute(final State state) {
        final BigInteger intValue = condition.intValue(state);

        if (intValue.signum() != 0) {
            state.jump(offset.intValue(state).intValueExact());
        }
    }
}
