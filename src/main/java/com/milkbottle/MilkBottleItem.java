package com.milkbottle;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.EffectCures;

/**
 * A drinkable bottle of milk. Drinking it clears all active effects, exactly
 * like the vanilla milk bucket, and leaves an empty glass bottle behind.
 */
public class MilkBottleItem extends Item {
    private static final int DRINK_DURATION = 32;

    public MilkBottleItem(Properties properties) {
        super(properties);
    }

    /**
     * Clears the first N active effects of the entity, where N is the configured
     * maximum. When the configured maximum is 0, all active effects are cleared.
     */
    private static void clearEffects(LivingEntity livingEntity) {
        int max = Config.MAX_EFFECTS_CLEARED.get();
        if (max <= 0) {
            livingEntity.removeEffectsCuredBy(EffectCures.MILK);
            return;
        }
        livingEntity.getActiveEffects().stream()
                .limit(max)
                .map(MobEffectInstance::getEffect)
                .toList()
                .forEach(livingEntity::removeEffect);
    }

    /**
     * Called when the player finishes drinking the bottle.
     */
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide) {
            clearEffects(livingEntity);
        }

        if (livingEntity instanceof Player player) {
            return ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE), false);
        } else {
            stack.consume(1, livingEntity);
            return stack;
        }
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return DRINK_DURATION;
    }

    /**
     * Returns the action that specifies what animation to play when the item is being used.
     */
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    /**
     * Called to trigger the item's "innate" right click behavior.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }
}
