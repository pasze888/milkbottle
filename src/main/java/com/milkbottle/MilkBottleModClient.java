package com.milkbottle;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.color.item.ItemColor;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = MilkBottleMod.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = MilkBottleMod.MODID, value = Dist.CLIENT)
public class MilkBottleModClient {
    public MilkBottleModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        MilkBottleMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        MilkBottleMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Render the thrown milk bottle the same way vanilla renders thrown items
        event.registerEntityRenderer(MilkBottleMod.THROWN_MILK_BOTTLE.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        // Milk color: pure white RGB(255, 255, 255).
        // Only tints layer 0 (the milk liquid); the glass bottle (layer 1) keeps its own color.
        int milkColor = 0xFFFFFFFF;
        ItemColor milkBottleColor = (stack, tintIndex) -> tintIndex == 0 ? milkColor : 0xFFFFFFFF;
        event.register(milkBottleColor, MilkBottleMod.MILK_BOTTLE.get(), MilkBottleMod.SPLASH_MILK_BOTTLE.get());
    }
}
