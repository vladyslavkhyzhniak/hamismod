package com.github.vladyslavkhyzhniak.hamis.client;

import com.github.vladyslavkhyzhniak.hamis.client.model.HamisNestModel;
import com.github.vladyslavkhyzhniak.hamis.entity.HamisNestBlockEntity;
import mod.azure.azurelib.renderer.GeoBlockRenderer;

public class HamisNestRender extends GeoBlockRenderer<HamisNestBlockEntity> {
    public HamisNestRender() {
        super(new HamisNestModel());
    }
}