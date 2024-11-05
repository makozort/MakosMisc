package net.makozort.makosmisc.reg;

import net.makozort.makosmisc.MakosMisc;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AllCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MakosMisc.MODID);



    public static final Supplier<CreativeModeTab> MAIN_TAB =
            TABS.register("makos_misc_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(AllItems.ENDER_LENS.get()))
                    .title(Component.translatable("creativetab.makosmisc.main"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(AllItems.ENDER_LENS);
                    }))
                    .build());

    public static void reg(IEventBus bus) {
        TABS.register(bus);
    }
}
