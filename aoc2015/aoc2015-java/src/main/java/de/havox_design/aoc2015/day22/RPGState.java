package de.havox_design.aoc2015.day22;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class RPGState {

    final boolean hardMode;

    final RPGPlayer player;

    final RPGBoss boss;

    final AtomicInteger best;

    private final int[] spellDuration;

    public RPGState(RPGPlayer player, RPGBoss boss, int[] spellDuration, boolean hardMode, AtomicInteger best) {
        this.player = player;
        this.boss = boss;
        this.spellDuration = spellDuration;
        this.hardMode = hardMode;
        this.best = best;
    }

    public RPGState apply(final RPGSpell spell) {
        int playerHitPoints = player.hitPoints();
        int playerMana = player.mana() - spell.getCosts();
        int bossHitPoints = boss.hitPoints();
        int bossDamage = boss.damage();

        if (hardMode) {
            playerHitPoints--;
            if (playerHitPoints <= 0) {
                return new RPGState
                        (
                                new RPGPlayer
                                        (
                                                playerHitPoints,
                                                playerMana,
                                                player.manaSpent() + spell.getCosts()
                                        ),
                                new RPGBoss
                                        (
                                                bossHitPoints,
                                                bossDamage
                                        ),
                                spellDuration,
                                hardMode,
                                best
                        );
            }
        }

        final int[] newSpellDuration = Arrays.copyOf(spellDuration, spellDuration.length);

        // players turn
        if (newSpellDuration[RPGSpell.POISON.ordinal()] > 0) {
            bossHitPoints -= 3;
        }
        if (newSpellDuration[RPGSpell.RECHARGE.ordinal()] > 0) {
            playerMana += 101;
        }
        for (int i = 0; i < newSpellDuration.length; i++) {
            newSpellDuration[i] = Math.max(0, newSpellDuration[i] - 1);
        }
        newSpellDuration[spell.ordinal()] = spell.duration();

        if (spell == RPGSpell.MAGIC_MISSILE) {
            bossHitPoints -= 4;
        }
        if (spell == RPGSpell.DRAIN) {
            bossHitPoints -= 2;
            playerHitPoints += 2;
        }

        if (bossHitPoints <= 0) { // win
            return new RPGState
                    (
                            new RPGPlayer
                                    (
                                            playerHitPoints,
                                            playerMana,
                                            player.manaSpent() + spell.getCosts()
                                    ),
                            new RPGBoss
                                    (
                                            bossHitPoints,
                                            bossDamage
                                    ),
                            newSpellDuration,
                            hardMode,
                            best
                    );
        }

        // boss turn
        if (newSpellDuration[RPGSpell.POISON.ordinal()] > 0) {
            bossHitPoints -= 3;
        }
        if (newSpellDuration[RPGSpell.RECHARGE.ordinal()] > 0) {
            playerMana += 101;
        }
        for (int i = 0; i < newSpellDuration.length; i++) {
            newSpellDuration[i] = Math.max(0, newSpellDuration[i] - 1);
        }
        if (bossHitPoints <= 0) { // win
            return new RPGState
                    (
                            new RPGPlayer
                                    (
                                            playerHitPoints,
                                            playerMana,
                                            player.manaSpent() + spell.getCosts()
                                    ),
                            new RPGBoss
                                    (
                                            bossHitPoints,
                                            bossDamage
                                    ),
                            newSpellDuration,
                            hardMode,
                            best
                    );
        }

        playerHitPoints -= Math.max(1, bossDamage - (newSpellDuration[RPGSpell.SHIELD.ordinal()] > 0 ? 7 : 0));

        return new RPGState
                (
                        new RPGPlayer
                                (
                                        playerHitPoints,
                                        playerMana,
                                        player.manaSpent() + spell.getCosts()
                                ),
                        new RPGBoss
                                (
                                        bossHitPoints,
                                        bossDamage
                                ),
                        newSpellDuration,
                        hardMode,
                        best
                );
    }

    public int[] getSpellDuration() {
        return spellDuration;
    }
}
