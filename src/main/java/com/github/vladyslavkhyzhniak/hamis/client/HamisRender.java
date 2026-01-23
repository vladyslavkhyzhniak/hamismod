package com.github.vladyslavkhyzhniak.hamis.client;

import com.github.vladyslavkhyzhniak.hamis.client.model.HamisModel;
import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import net.minecraft.client.model.SilverfishModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

public class HamisRender  extends MobRenderer<HamisEntity, HamisModel> {
    public  HamisRender(EntityRendererProvider.Context context) {
        super(context, new HamisModel(context.bakeLayer(HamisModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(HamisEntity entity) {
        return new ResourceLocation(MOD_ID, "textures/entity/hamis.png");
    }
}
