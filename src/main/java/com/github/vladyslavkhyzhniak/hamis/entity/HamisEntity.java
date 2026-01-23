package com.github.vladyslavkhyzhniak.hamis.entity;

import com.github.vladyslavkhyzhniak.hamis.init.ModEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HamisEntity extends PathfinderMob {
    public HamisEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    // 3. Метод атрибутов для Forge выглядит так (AttributeSupplier)
    public static AttributeSupplier.Builder createHamisAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.HAMIS.get(), HamisEntity.createHamisAttributes().build());
    }

    @Override
    protected ResourceLocation getDefaultLootTable(){
        return new ResourceLocation(MOD_ID,"entities/hamis");
    }
}
