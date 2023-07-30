package net.andrewthepika.minefinity;

import com.mojang.logging.LogUtils;
import net.andrewthepika.minefinity.block.ModBlocks;
import net.andrewthepika.minefinity.item.ModItems;
import net.andrewthepika.minefinity.world.feature.ModConfiguredFeatures;
import net.andrewthepika.minefinity.world.feature.ModPlacedFeatures;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Minefinity.MOD_ID)
public class Minefinity
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "minefinity";

    private static final Logger LOGGER = LogUtils.getLogger();


    public Minefinity()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }
//
    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }




    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
