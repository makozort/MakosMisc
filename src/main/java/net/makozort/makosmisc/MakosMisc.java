package net.makozort.makosmisc;

import net.makozort.makosmisc.content.enchantment.ModEnchantmentEffects;
import net.makozort.makosmisc.event.ClientEvents;
import net.makozort.makosmisc.event.ModEvents;
import net.makozort.makosmisc.networking.PayloadRegister;
import net.makozort.makosmisc.reg.AllBlocks;
import net.makozort.makosmisc.reg.AllEffectDataComponentTypes;
import net.makozort.makosmisc.reg.AllEffects;
import net.makozort.makosmisc.reg.AllItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MakosMisc.MODID)
public class MakosMisc
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "makosmisc";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public MakosMisc(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        AllEffects.register(modEventBus);

        modEventBus.addListener(PayloadRegister::register);

        NeoForge.EVENT_BUS.register(new ModEvents());

        ModEnchantmentEffects.reg(modEventBus);

        AllEffectDataComponentTypes.register(modEventBus);
        AllBlocks.register(modEventBus);
        AllItems.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            NeoForge.EVENT_BUS.register(ClientEvents.class);
        }
    }
}
