package com.github.vladyslavkhyzhniak.hamis.init;

import com.github.vladyslavkhyzhniak.hamis.item.HamisNestItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> HAMIS_SPAWN_EGG = ITEMS.register("hamis_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.HAMIS, 0x553473, 0xC7EF63,
                    new Item.Properties()));

    public static final RegistryObject<Item> HAMIS_NEST_ITEM = ITEMS.register("hamis_nest",
            () -> new HamisNestItem(ModBlocks.HAMIS_NEST.get(), new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
