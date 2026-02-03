package com.github.vladyslavkhyzhniak.hamis.init;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisNestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

    public static final RegistryObject<BlockEntityType<HamisNestBlockEntity>> HAMIS_NEST_ENTITY =
            BLOCK_ENTITIES.register("hamis_nest", () ->
                    BlockEntityType.Builder.of(HamisNestBlockEntity::new,
                            ModBlocks.HAMIS_NEST.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}