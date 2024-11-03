package net.makozort.makosmisc.reg;

import net.makozort.makosmisc.MakosMisc;
import net.makozort.makosmisc.content.item.EnderLensItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MakosMisc.MODID);

    public static final DeferredItem<EnderLensItem> ENDER_LENS = ITEMS.register("ender_lens",
            () -> new EnderLensItem(AllBlocks.ENDER_LENS.get(),new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
