package de.havox_design.aoc2015.day22;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Algorithms {

    private Algorithms() {
    }

    public static <S> List<S> breadthFirstSearch(
            final S state,
            final Function<StateWrapper<S>,
                    Iterable<S>> stateProducer,
            final Consumer<StateWrapper<S>> consumer,
            final Predicate<StateWrapper<S>> acceptanceCriterion
    ) {
        final Queue<StateWrapper<S>> queue = new LinkedList<>();
        StateWrapper<S> currentState = new StateWrapper<>(state, null);

        queue.add(currentState);

        if (consumer != null) {
            consumer.accept(currentState);
        }

        while (!queue.isEmpty()) {
            currentState = queue.poll();

            final Iterable<S> possibleStates = stateProducer.apply(currentState);

            for (final S possibleState : possibleStates) {
                final StateWrapper<S> newState = new StateWrapper<>(possibleState, currentState);

                if (consumer != null) {
                    consumer.accept(newState);
                }

                if (acceptanceCriterion.test(newState)) {
                    return newState.getStates();
                }

                queue.add(newState);
            }
        }

        return Collections.emptyList();
    }
}
