package de.havox_design.aoc.utils.java.model.programs.duet.duet.instructions;

import de.havox_design.aoc.utils.java.model.programs.duet.duet.State;
import de.havox_design.aoc.utils.java.model.programs.duet.duet.token.Token;
import de.havox_design.aoc.utils.java.model.programs.duet.duet.token.TokenProvider;

public class Send implements Instruction {
    private final Token token;

    public Send(final String value) {
        token = TokenProvider.createToken(value);
    }

    @Override
    public void execute(final State state) {
        state.send(token.intValue(state));
    }
}
