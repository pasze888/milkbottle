package com.milkbottle;

import net.neoforged.neoforge.common.ModConfigSpec;

// A common config class for the mod.
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // the maximum number of active effects cleared by a milk bottle, 0 clears all
    public static final ModConfigSpec.IntValue MAX_EFFECTS_CLEARED = BUILDER
            .comment("The maximum number of active effects a milk bottle clears. 0 clears all active effects.")
            .defineInRange("maxEffectsCleared", 0, 0, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();
}
