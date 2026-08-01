package com.milkbottle;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

/**
 * A throwable splash milk bottle. Thrown like a splash potion; clears effects
 * on impact via {@link ThrownMilkBottle}.
 */
public class SplashMilkBottleItem extends Item implements ProjectileItem {
    public SplashMilkBottleItem(Properties properties) {
        super(properties);
    }

    /**
     * Called to trigger the item's "innate" right click behavior.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            ThrownMilkBottle thrownmilkbottle = new ThrownMilkBottle(level, player);
            thrownmilkbottle.setItem(new ItemStack(this));
            thrownmilkbottle.shootFromRotation(player, player.getXRot(), player.getYRot(), -20.0F, 0.5F, 1.0F);
            level.addFreshEntity(thrownmilkbottle);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ThrownMilkBottle thrownmilkbottle = new ThrownMilkBottle(level, pos.x(), pos.y(), pos.z());
        thrownmilkbottle.setItem(new ItemStack(this));
        return thrownmilkbottle;
    }

    @Override
    public ProjectileItem.DispenseConfig createDispenseConfig() {
        return ProjectileItem.DispenseConfig.builder()
            .uncertainty(ProjectileItem.DispenseConfig.DEFAULT.uncertainty() * 0.5F)
            .power(ProjectileItem.DispenseConfig.DEFAULT.power() * 1.25F)
            .build();
    }
}
