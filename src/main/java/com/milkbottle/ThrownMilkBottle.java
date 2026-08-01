package com.milkbottle;

import java.util.List;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.EffectCures;

/**
 * A thrown splash milk bottle. On impact it clears the active effects of every
 * living entity within the same splash radius as a vanilla splash potion.
 */
public class ThrownMilkBottle extends ThrowableItemProjectile {
    private static final double SPLASH_RANGE = 4.0;
    private static final double SPLASH_RANGE_SQ = SPLASH_RANGE * SPLASH_RANGE;

    public ThrownMilkBottle(EntityType<? extends ThrownMilkBottle> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownMilkBottle(Level level, LivingEntity shooter) {
        super(MilkBottleMod.THROWN_MILK_BOTTLE.get(), shooter, level);
    }

    public ThrownMilkBottle(Level level, double x, double y, double z) {
        super(MilkBottleMod.THROWN_MILK_BOTTLE.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return MilkBottleMod.SPLASH_MILK_BOTTLE.get();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.05;
    }

    /**
     * Called when this projectile hits a block or entity.
     */
    @Override
    protected void onHit(HitResult result) {
        if (!this.level().isClientSide) {
            AABB aabb = this.getBoundingBox().inflate(SPLASH_RANGE, 2.0, SPLASH_RANGE);
            List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, aabb);
            if (!list.isEmpty()) {
                for (LivingEntity livingEntity : list) {
                    if (livingEntity.isAffectedByPotions() && this.distanceToSqr(livingEntity) < SPLASH_RANGE_SQ) {
                        int max = Config.MAX_EFFECTS_CLEARED.get();
                        if (max <= 0) {
                            livingEntity.removeEffectsCuredBy(EffectCures.MILK);
                        } else {
                            livingEntity.getActiveEffects().stream()
                                    .limit(max)
                                    .map(MobEffectInstance::getEffect)
                                    .toList()
                                    .forEach(livingEntity::removeEffect);
                        }
                    }
                }
            }

            // Same splash effect as a splash potion, with a white (milk) color.
            this.level().levelEvent(2002, this.blockPosition(), 0xFFFFFF);
            this.discard();
        }
    }
}
