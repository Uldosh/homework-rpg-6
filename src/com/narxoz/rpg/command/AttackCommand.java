package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaOpponent;

public class AttackCommand implements ActionCommand {
    private final ArenaOpponent opponent;
    private final int attackPower;
    private int damageDealt;

    public AttackCommand(ArenaOpponent opponent, int attackPower) {
        this.opponent    = opponent;
        this.attackPower = attackPower;
        this.damageDealt = 0;
    }

    @Override
    public void execute() {
        int hpBefore = opponent.getCurrentHp();
        opponent.takeDamage(attackPower);
        damageDealt = hpBefore - opponent.getCurrentHp();

        System.out.printf("  [ATTACK] %s takes %d damage (HP: %d → %d)%n", opponent.getName(), damageDealt,
                hpBefore, opponent.getCurrentHp());
    }

    @Override
    public void undo() {
        opponent.restoreHp(damageDealt);
        System.out.printf("  [UNDO ATTACK] restored %d HP to %s%n", damageDealt, opponent.getName());
    }

    @Override
    public String getDescription() {
        return String.format("AttackCommand(target=%s, power=%d)", opponent.getName(), attackPower);
    }
}
