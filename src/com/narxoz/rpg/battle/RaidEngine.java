package com.narxoz.rpg.battle;

import com.narxoz.rpg.bridge.Skill;
import com.narxoz.rpg.composite.CombatNode;
import java.util.Random;

public class RaidEngine {
    private Random random = new Random(1L);

    public RaidEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public RaidResult runRaid(CombatNode teamA, CombatNode teamB, Skill teamASkill, Skill teamBSkill) {

        if (teamA == null || teamB == null || teamASkill == null || teamBSkill == null) {
            throw new IllegalArgumentException("Teams and skills must not be null");
        }

        if (!teamA.isAlive() || !teamB.isAlive()) {
            throw new IllegalStateException("Both teams r have to be alive to start the raid");
        }

        RaidResult result = new RaidResult();
        result.addLine("Raid started: " + teamA.getName() + " vs " + teamB.getName());

        int rounds = 0;

        while (teamA.isAlive() && teamB.isAlive()) {

            rounds++;
            result.addLine("Round " + rounds);

            result.addLine(teamA.getName() + " uses " + teamASkill.getSkillName());
            teamASkill.cast(teamB);

            if (!teamB.isAlive()) {
                result.addLine(teamB.getName() + " has been defeated.");
                break;
            }

            result.addLine(teamB.getName() + " uses " + teamBSkill.getSkillName());
            teamBSkill.cast(teamA);

            if (!teamA.isAlive()) {
                result.addLine(teamA.getName() + " has been defeated.");
            }
        }

        result.setRounds(rounds);

        if (teamA.isAlive()) {
            result.setWinner(teamA.getName());
        } else {
            result.setWinner(teamB.getName());
        }

        result.addLine("Battle finished. Winner: " + result.getWinner());

        return result;
    }
}