package com.github.vladyslavkhyzhniak.hamis.client;

import com.github.vladyslavkhyzhniak.hamis.client.model.HamisModel;
import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class HamisRender extends GeoEntityRenderer<HamisEntity> {
    public HamisRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HamisModel());
    }
}
