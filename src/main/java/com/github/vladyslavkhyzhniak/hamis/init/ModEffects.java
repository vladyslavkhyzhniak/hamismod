package com.github.vladyslavkhyzhniak.hamis.init;

import com.github.vladyslavkhyzhniak.hamis.effect.PheromoneEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MOD_ID);

    public static final RegistryObject<MobEffect> PHEROMONE_EFFECT = MOB_EFFECTS.register("pheromone_effect",
            ()->new PheromoneEffect(MobEffectCategory.BENEFICIAL, 0xE027F5));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
