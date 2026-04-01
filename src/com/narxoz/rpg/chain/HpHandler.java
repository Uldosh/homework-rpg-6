package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class HpHandler extends DefenseHandler {

    @Override
    public void handle(int incomingDamage, ArenaFighter target) {
        target.takeDamage(incomingDamage);

        System.out.printf("  [HP]    %s takes %d damage → HP now %d/%d%n", target.getName(), incomingDamage, target.getCurrentHp(), target.getMaxHp());

    }
}
