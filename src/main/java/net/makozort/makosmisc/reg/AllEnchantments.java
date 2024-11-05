package net.makozort.makosmisc.reg;

import net.makozort.makosmisc.MakosMisc;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;

public class AllEnchantments {

    public static final ResourceKey<Enchantment> DASH = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(MakosMisc.MODID, "dash"));

    public static final ResourceKey<Enchantment> HIDDEN = ResourceKey.create(Registries.ENCHANTMENT,
            ResourceLocation.fromNamespaceAndPath(MakosMisc.MODID, "hidden"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantment = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        reg(context, DASH, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), 4,1,Enchantment.dynamicCost(30,8),
                Enchantment.dynamicCost(30,30),3,EquipmentSlotGroup.FEET)).withEffect(AllEffectDataComponentTypes.DASH.get()));

        reg(context, HIDDEN , Enchantment.enchantment(Enchantment.definition(items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE), 10,1,Enchantment.dynamicCost(5,8),
                Enchantment.dynamicCost(25,8),3,EquipmentSlotGroup.ARMOR)).withEffect(AllEffectDataComponentTypes.HIDDEN.get()));
    }

    private static void reg(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key,builder.build(key.location()));

    }

}
