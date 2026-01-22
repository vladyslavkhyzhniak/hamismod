package com.github.vladyslavkhyzhniak.hamis.init;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.html.parser.Entity;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,MOD_ID);

    public static final RegistryObject<EntityType<HamisEntity>> HAMIS = ENTITIES.register("hamis",
            () -> EntityType.Builder.of(HamisEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f)
                    .build(new ResourceLocation("hamis_mod", "hamis").toString()));


    public static void register(IEventBus EventBus){
        ENTITIES.register(EventBus);
    }
}
