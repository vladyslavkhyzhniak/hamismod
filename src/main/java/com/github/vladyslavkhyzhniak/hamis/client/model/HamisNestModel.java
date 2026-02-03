package com.github.vladyslavkhyzhniak.hamis.client.model;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisNestBlockEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class HamisNestModel extends GeoModel<HamisNestBlockEntity> {
    @Override
    public ResourceLocation getModelResource(HamisNestBlockEntity animatable) {
        return new ResourceLocation(MOD_ID, "geo/hamis_nest.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HamisNestBlockEntity animatable) {
        return new ResourceLocation(MOD_ID, "textures/block/hamis_nest.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HamisNestBlockEntity animatable) {
        return new ResourceLocation(MOD_ID, "animations/hamis_nest.animation.json");
    }
    @Override
    public RenderType getRenderType(HamisNestBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}