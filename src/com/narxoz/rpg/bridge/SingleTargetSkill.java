package com.narxoz.rpg.bridge;

import com.narxoz.rpg.composite.CombatNode;

public class SingleTargetSkill extends Skill {
    public SingleTargetSkill(String skillName, int basePower, EffectImplementor effect) {
        super(skillName, basePower, effect);
    }

    @Override
    public void cast(CombatNode target) {
         if (target == null || !target.isAlive()) {
        return;
    }

    int damage = resolvedDamage();
    target.takeDamage(damage);
        // TODO: Single-target Bridge action
        // 1) Resolve final damage through effect implementor
        // 2) Apply to target node
    }
}
