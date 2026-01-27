package com.github.vladyslavkhyzhniak.hamis.client.model;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.resources.ResourceLocation;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class HamisModel extends GeoModel<HamisEntity> {

	@Override
	public ResourceLocation getModelResource(HamisEntity object) {
		return new ResourceLocation(MOD_ID, "geo/hamis_geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(HamisEntity object) {
		return new ResourceLocation(MOD_ID, "textures/entity/hamis.png");
	}
	@Override
	public ResourceLocation getAnimationResource(HamisEntity animatable) {
		return new ResourceLocation(MOD_ID, "animations/hamis_animation.json");
	}
}