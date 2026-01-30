package com.github.vladyslavkhyzhniak.hamis.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.time.Duration;

public class PheromoneEffect extends MobEffect {
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration%20 == 0;
    }

    public PheromoneEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                Attributes.FOLLOW_RANGE,
                "7107DE5E-7CE8-4030-940E-514C1F160890",
                -1.0,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int Ampilfier) {
        if(livingEntity instanceof Mob mob){
            if (mob.getTarget() != null){
                mob.setTarget(null);
            }
        }

        super.applyEffectTick(livingEntity,Ampilfier);
    }
}
