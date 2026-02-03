package com.github.vladyslavkhyzhniak.hamis.client;

import com.github.vladyslavkhyzhniak.hamis.client.model.HamisModel;
import com.github.vladyslavkhyzhniak.hamis.init.ModBlockEntities;
import com.github.vladyslavkhyzhniak.hamis.init.ModBlocks;
import com.github.vladyslavkhyzhniak.hamis.init.ModEntities;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.HAMIS.get(), HamisRender::new);
        event.registerBlockEntityRenderer(ModBlockEntities.HAMIS_NEST_ENTITY.get(), context -> new HamisNestRender());
    }
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.HAMIS_NEST.get(), RenderType.cutout());
    }
}
