package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class HealCommand implements ActionCommand {
    private final ArenaFighter fighter;
    private final int healAmount;
    private int actualHealApplied;

    public HealCommand(ArenaFighter fighter, int healAmount) {
        this.fighter= fighter;
        this.healAmount = healAmount;
    }

    @Override
    public void execute() {
        int hpBefore = fighter.getCurrentHp();
        actualHealApplied = fighter.heal(healAmount);
        if (actualHealApplied > 0) {
            System.out.printf("  [HEAL] %s healed %d HP (HP: %d → %d)%n", fighter.getName(), actualHealApplied, hpBefore, fighter.getCurrentHp());
        } else {
            System.out.printf("  [HEAL] %s has no potions remaining — heal skipped%n", fighter.getName());
        }
    }

    @Override
    public void undo() {
        fighter.takeDamage(actualHealApplied);
        System.out.printf("  [UNDO HEAL] removed %d HP from %s%n", actualHealApplied, fighter.getName());
    }

    @Override
    public String getDescription() {
        return String.format("HealCommand(target=%s, amount=%d)", fighter.getName(), healAmount);
    }
}
