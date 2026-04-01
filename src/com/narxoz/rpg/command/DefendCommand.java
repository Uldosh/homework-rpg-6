package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class DefendCommand implements ActionCommand {
    private final ArenaFighter fighter;
    private final double dodgeBoost;

    public DefendCommand(ArenaFighter fighter, double dodgeBoost) {
        this.fighter= fighter;
        this.dodgeBoost = dodgeBoost;
    }

    @Override
    public void execute() {
        double before = fighter.getDodgeChance();
        fighter.boostDodge(dodgeBoost);

        System.out.printf("  [DEFEND] %s dodge chance boosted: %.0f%% → %.0f%%  (+%.0f%%)%n", fighter.getName(), before * 100, fighter.getDodgeChance() * 100, dodgeBoost * 100);
    }

    @Override
    public void undo() {
        fighter.removeDodgeBoost(dodgeBoost);
        System.out.printf("  [UNDO DEFEND] dodge boost of %.0f%% removed from %s (now %.0f%%)%n", dodgeBoost * 100, fighter.getName(), fighter.getDodgeChance() * 100);
    }

    @Override
    public String getDescription() {
        return String.format("DefendCommand(target=%s, boost=+%.0f%%)", fighter.getName(), dodgeBoost * 100);
    }
}
