package com.github.vladyslavkhyzhniak.hamis.client;

import com.github.vladyslavkhyzhniak.hamis.client.model.HamisNestItemModel;
import com.github.vladyslavkhyzhniak.hamis.item.HamisNestItem;
import mod.azure.azurelib.renderer.GeoItemRenderer;

public class HamisNestItemRender extends GeoItemRenderer<HamisNestItem> {
    public HamisNestItemRender() {
        super(new HamisNestItemModel());
    }
}