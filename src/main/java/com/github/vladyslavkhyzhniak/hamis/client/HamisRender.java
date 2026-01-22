package com.github.vladyslavkhyzhniak.hamis.client;

import com.github.vladyslavkhyzhniak.hamis.entity.HamisEntity;
import net.minecraft.client.model.SilverfishModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HamisRender  extends MobRenderer<HamisEntity, SilverfishModel<HamisEntity>> {
    public  HamisRender(EntityRendererProvider.Context context) {
        super(context, new SilverfishModel<>(context.bakeLayer(ModelLayers.SILVERFISH)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(HamisEntity entity) {
        return new ResourceLocation("minecraft", "textures/entity/silverfish.png");
    }
}
