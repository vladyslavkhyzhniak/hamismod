package com.github.vladyslavkhyzhniak.hamis.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

    public static final RegistryObject<Potion> PHEROMONE_POTION = POTIONS.register("pheromone_potion",
            () -> new Potion(new MobEffectInstance(
                    ModEffects.PHEROMONE_EFFECT.get(),
                    3600,
                    0
            )));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BrewingRecipeRegistry.addRecipe(new BrewingRecipe(
                    Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                    Ingredient.of(Items.SLIME_BALL),
                    PotionUtils.setPotion(new ItemStack(Items.POTION), PHEROMONE_POTION.get())
            ));
        });
    }
}