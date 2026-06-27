package com.revskymc.beautifulexpansivebiomes;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BeautifulExpansiveBiomes.MOD_ID)
public class BeautifulExpansiveBiomes {
    public static final String MOD_ID = "beautifulexpansivebiomes";
    private static final Logger LOGGER = LogManager.getLogger();

    public BeautifulExpansiveBiomes() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Beautiful Expansive Biomes initialized!");
    }

    @SubscribeEvent
    public void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.Category.NETHER || event.getCategory() == Biome.Category.THEEND) {
            return; // Skip non-Overworld
        }

        // Add lush vegetation for "beautiful" look
        var vegetal = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
        vegetal.add(() -> Features.OAK_BEES_005);      // More bee-friendly oaks
        vegetal.add(() -> Features.FANCY_OAK);         // Fancy tall trees
        vegetal.add(() -> Features.BIRCH_BEES_005);
        vegetal.add(() -> Features.FLOWER_DEFAULT);    // Extra flowers
        vegetal.add(() -> Features.FLOWER_PLAINS);
        vegetal.add(() -> Features.HUGE_BROWN_MUSHROOM);
        vegetal.add(() -> Features.HUGE_RED_MUSHROOM);
        vegetal.add(() -> Features.PATCH_GRASS);       // Denser grass

        // More structures for interest/expansiveness
        event.getGeneration().getStructures().add(() -> Structure.VILLAGE);
        event.getGeneration().getStructures().add(() -> Structure.PILLAGER_OUTPOST);

        LOGGER.debug("Enhanced biome: {}", event.getName());
    }
}
