package de.havox_design.aoc2017.day18.instructions;

import de.havox_design.aoc2017.day18.State;
import de.havox_design.aoc2017.day18.token.Token;
import de.havox_design.aoc2017.day18.token.TokenProvider;

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
