package com.github.vladyslavkhyzhniak.hamis;

import com.github.vladyslavkhyzhniak.hamis.init.*;
import com.mojang.logging.LogUtils;
import mod.azure.azurelib.AzureLib;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(hamis.MOD_ID)

public class hamis {

    public static final String MOD_ID = "hamis";

    private static final Logger LOGGER = LogUtils.getLogger();

    public hamis() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        modEventBus.addListener(ModPotions::commonSetup);

        AzureLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);
    }
}