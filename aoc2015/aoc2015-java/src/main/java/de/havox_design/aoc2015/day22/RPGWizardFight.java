package de.havox_design.aoc2015.day22;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RPGWizardFight implements AoCFunctionality {
    private final List<String> input;

    public RPGWizardFight(String fileName) {
        input = readData(fileName);
    }

    public static int solvePart1(String fileName) {
        RPGWizardFight instance = new RPGWizardFight(fileName);
        return instance.solvePart1();
    }

    public static int solvePart2(String fileName) {
        RPGWizardFight instance = new RPGWizardFight(fileName);
        return instance.solvePart2();
    }

    public int solvePart1() {
        return calc(false);
    }

    public int solvePart2() {
        return calc(true);
    }

    private Integer calc(boolean hardMode) {
        int hitPoints = Integer.parseInt(matchRegex("Hit Points: (\\d+)", input.get(0)).group(1));
        int damage = Integer.parseInt(matchRegex("Damage: (\\d+)", input.get(1)).group(1));
        RPGBoss boss = new RPGBoss(hitPoints, damage);
        RPGPlayer player = new RPGPlayer(50, 500, 0);
        AtomicInteger best = new AtomicInteger(Integer.MAX_VALUE);
        RPGState state = new RPGState(player, boss, new int[RPGSpell.values().length], hardMode, best);

        Algorithms.breadthFirstSearch(state, this::producer, (x -> {
            if (x.getState().player.hitPoints() >= 0 && x.getState().boss.hitPoints() <= 0) {
                best.getAndAccumulate(x.getState().player.manaSpent(), Math::min);
            }
        }), s -> false);

        return best.get();
    }

    public Iterable<RPGState> producer(StateWrapper<RPGState> wrappedState) {
        RPGState state = wrappedState.getState();

        if (state.player.hitPoints() <= 0 || state.boss.hitPoints() <= 0) {
            return Collections.emptyList();
        }

        RPGSpell[] spells = RPGSpell.getPossibleSpells(state.player.mana(), state.getSpellDuration());
        List<RPGState> result = new ArrayList<>();

        for (RPGSpell spell : spells) {
            RPGState test = state.apply(spell);

            if (test.player.manaSpent() < test.best.get()) {
                result.add(test);
            }
        }

        if (result.stream().anyMatch(s -> s.player.hitPoints() > 0 && s.boss.hitPoints() <= 0)) {
            result.removeIf(s -> s.player.hitPoints() <= 0 || s.boss.hitPoints() > 0);
        }

        return result;
    }

    public Matcher matchRegex(final String regex, final CharSequence input) {
        return matchRegex(Pattern.compile(regex), input);
    }

    public static Matcher matchRegex(final Pattern pattern, final CharSequence input) {
        final Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return matcher;
        } else {
            throw new IllegalArgumentException("Input '" + input + "' does not match pattern " + pattern.pattern());
        }
    }
}
