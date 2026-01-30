package com.github.vladyslavkhyzhniak.hamis.client.model;

import com.github.vladyslavkhyzhniak.hamis.item.HamisNestItem;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class HamisNestItemModel extends GeoModel<HamisNestItem> {
    @Override
    public ResourceLocation getModelResource(HamisNestItem animatable) {
        return new ResourceLocation(MOD_ID, "geo/hamis_nest.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HamisNestItem animatable) {
        return new ResourceLocation(MOD_ID, "textures/block/hamis_nest.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HamisNestItem animatable) {
        return new ResourceLocation(MOD_ID, "animations/hamis_nest.animation.json");
    }
}