package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;
import java.util.Random;

public class DodgeHandler extends DefenseHandler {
    private final double dodgeChance;
    private final Random random;

    public DodgeHandler(double dodgeChance, long seed) {
        this.dodgeChance = dodgeChance;
        this.random = new Random(seed);
    }

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        double roll = random.nextDouble();

        if (roll < dodgeChance) {
            System.out.printf("  [DODGE] %s dodged! (roll=%.2f < chance=%.2f) — %d damage avoided%n", target.getName(), roll, dodgeChance, incomingDamage);
        } else {
            System.out.printf("  [DODGE] %s failed to dodge (roll=%.2f >= chance=%.2f)%n", target.getName(), roll, dodgeChance);
            passToNext(incomingDamage, target);
        }
    }
}
