package com.github.vladyslavkhyzhniak.hamis;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import com.github.vladyslavkhyzhniak.hamis.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import com.github.vladyslavkhyzhniak.hamis.init.ModItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "hamis", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.HAMIS.get(), HamisEntity.createAttributes().build());
    }
}
    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.HAMIS_SPAWN_EGG);
        }
    }
}
