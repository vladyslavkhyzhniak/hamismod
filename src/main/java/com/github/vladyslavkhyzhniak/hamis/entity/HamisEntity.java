package com.github.vladyslavkhyzhniak.hamis.entity;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class HamisEntity extends PathfinderMob implements GeoEntity {
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    public HamisEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5)
                .add(Attributes.FOLLOW_RANGE, 16.0);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, event -> {
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation_idle"));
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));


        this.goalSelector.addGoal(1, new SmartChaseGoal(this, 1.2D, false));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));


        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));


        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
                this,
                LivingEntity.class,
                0, true, false,
                (entity) -> {
                    if (entity instanceof HamisEntity) return false;
                    if (entity instanceof Player) return false;
                    return entity.distanceToSqr(this) <= 4.0D;
                }
        ));
    }

    static class SmartChaseGoal extends MeleeAttackGoal {
        private int attackTimer = 0;

        public SmartChaseGoal(PathfinderMob mob, double speed, boolean followingTargetEvenIfNotSeen) {
            super(mob, speed, followingTargetEvenIfNotSeen);
        }

        @Override
        public void tick() {
            super.tick();
            if (attackTimer > 0) attackTimer--;
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity enemy, double distSqr) {
            double reach = this.getAttackReachSqr(enemy);
            if (distSqr <= reach && this.attackTimer <= 0) {
                this.resetAttackCooldown();
                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(enemy);
                this.attackTimer = 20;
            }
        }

        @Override
        public boolean canContinueToUse() {
            boolean canContinue = super.canContinueToUse();
            if (!canContinue) return false;

            LivingEntity target = this.mob.getTarget();
            if (target == null) return false;


            if (target instanceof Player) return true;


            if (target instanceof Monster) return true;


            if (this.mob.distanceToSqr(target) > 9.0D) {
                this.mob.setTarget(null);
                return false;
            }

            return true;
        }
    }
}