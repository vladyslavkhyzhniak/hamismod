package com.github.vladyslavkhyzhniak.hamis.entity;

import com.github.vladyslavkhyzhniak.hamis.init.ModEntities;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import java.util.EnumSet;

public class HamisEntity extends PathfinderMob implements GeoEntity {
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
    public int jumpDelay;

    public HamisEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.jumpDelay = this.random.nextInt(20) + 10;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5)
                .add(Attributes.FOLLOW_RANGE, 32.0);
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
    public void tick() {
        super.tick();

        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0, 1, 0));

            if (this.jumpDelay > 0) {
                this.jumpDelay--;
            }
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SlimeAttackGoal(this));
        this.goalSelector.addGoal(2, new SlimeRandomJumpGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(HamisEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
                this,
                LivingEntity.class,
                10, true, false,
                (candidate) -> {
                    if (candidate instanceof HamisEntity) return false;
                    if (candidate instanceof Player) return false;

                    if (candidate instanceof Mob mobEnemy) {
                        if (mobEnemy.getTarget() instanceof HamisEntity) return true;
                    }
                    if (candidate.getLastHurtMob() instanceof HamisEntity) return true;
                    return false;
                }
        ));

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(
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

    static class SlimeAttackGoal extends Goal {
        private final HamisEntity mob;
        private int attackTimer;

        public SlimeAttackGoal(HamisEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.mob.getTarget();
            if (target == null) return false;
            if (!target.isAlive()) return false;

            if (target instanceof Player) return true;

            boolean isPackEnemy = false;
            if (target.getLastHurtMob() instanceof HamisEntity) isPackEnemy = true;
            if (target instanceof Monster) isPackEnemy = true;
            if (target instanceof Mob m && m.getTarget() instanceof HamisEntity) isPackEnemy = true;

            if (isPackEnemy) return true;

            return !(this.mob.distanceToSqr(target) > 9.0D);
        }

        @Override
        public void start() {
            this.attackTimer = 0;
        }

        @Override
        public void tick() {
            LivingEntity target = this.mob.getTarget();
            if (target == null) return;

            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

            if (this.attackTimer > 0) this.attackTimer--;

            double distSqr = this.mob.distanceToSqr(target);

            if (this.mob.onGround() && this.mob.jumpDelay <= 0) {
                this.mob.jumpDelay = this.mob.getRandom().nextInt(20) + 10;

                Vec3 vec3 = this.mob.getDeltaMovement();
                Vec3 vec31 = new Vec3(target.getX() - this.mob.getX(), 0.0D, target.getZ() - this.mob.getZ());
                if (vec31.lengthSqr() > 1.0E-7D) {
                    vec31 = vec31.normalize().scale(0.9D).add(vec3.scale(0.2D));
                }

                this.mob.setDeltaMovement(vec31.x, 0.4D, vec31.z);
            }

            double reach = (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F) + target.getBbWidth();
            if (distSqr <= reach && this.attackTimer <= 0) {
                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(target);
                this.attackTimer = 20;
            }
        }
    }

    static class SlimeRandomJumpGoal extends Goal {
        private final HamisEntity mob;
        private double targetX, targetZ;

        public SlimeRandomJumpGoal(HamisEntity mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return (this.mob.onGround() || this.mob.isInWater()) && this.mob.jumpDelay <= 0 && this.mob.getRandom().nextInt(40) == 0;
        }

        @Override
        public void start() {
            double angle = this.mob.getRandom().nextDouble() * 2.0D * Math.PI;
            this.targetX = this.mob.getX() + Math.cos(angle) * 4.0D;
            this.targetZ = this.mob.getZ() + Math.sin(angle) * 4.0D;

            this.mob.getLookControl().setLookAt(this.targetX, this.mob.getY(), this.targetZ, 30.0F, 30.0F);
        }

        @Override
        public void tick() {
            if (this.mob.onGround()) {
                this.mob.jumpDelay = this.mob.getRandom().nextInt(40) + 20;

                Vec3 vec3 = this.mob.getDeltaMovement();
                Vec3 vec31 = new Vec3(this.targetX - this.mob.getX(), 0.0D, this.targetZ - this.mob.getZ());
                if (vec31.lengthSqr() > 1.0E-7D) {
                    vec31 = vec31.normalize().scale(0.7D).add(vec3.scale(0.2D));
                }
                this.mob.setDeltaMovement(vec31.x, 0.4D, vec31.z);
            }
        }
    }
}