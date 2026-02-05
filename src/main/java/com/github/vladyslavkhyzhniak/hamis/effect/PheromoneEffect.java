package com.github.vladyslavkhyzhniak.hamis.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

public class PheromoneEffect extends MobEffect {
    public PheromoneEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                "5488A193-352D-4E14-8785-3571A3783935",
                -1.0,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );

        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "A6C96C32-E7A9-4186-9C6B-708092B04D6D",
                -1.0,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof net.minecraft.world.entity.player.Player) {
            return;
        }else if (!livingEntity.level().isClientSide && livingEntity instanceof Mob mob) {
            mob.setLastHurtByMob(null);

            if (mob instanceof NeutralMob neutral) {
                neutral.stopBeingAngry();
            }
        }
    }
}
