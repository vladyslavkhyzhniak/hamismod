package com.github.vladyslavkhyzhniak.hamis.client;

import com.github.vladyslavkhyzhniak.hamis.init.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.github.vladyslavkhyzhniak.hamis.hamis.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModTooltips {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (PotionUtils.getMobEffects(event.getItemStack()).stream()
                .anyMatch(effect -> effect.getEffect() == ModEffects.PHEROMONE_EFFECT.get())) {

            String attributeName = Component.translatable(Attributes.FOLLOW_RANGE.getDescriptionId()).getString();

            event.getToolTip().removeIf(component -> component.getString().contains(attributeName));

            event.getToolTip().add(Component.translatable("tooltip.hamis.pheromone_desc"));
        }
    }
}