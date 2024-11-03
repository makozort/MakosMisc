package net.makozort.makosmisc.content.enchantment;

import com.mojang.serialization.MapCodec;
import net.makozort.makosmisc.MakosMisc;
import net.makozort.makosmisc.content.enchantment.custom.DashEnchantmentEffect;
import net.makozort.makosmisc.content.enchantment.custom.HiddenEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MakosMisc.MODID);


    public static Supplier<MapCodec<? extends  EnchantmentEntityEffect>> DASH =
            registerEnchantmentEffect("dash", DashEnchantmentEffect.CODEC);

    public static Supplier<MapCodec<? extends  EnchantmentEntityEffect>> HIDDEN =
            registerEnchantmentEffect("hidden", HiddenEnchantmentEffect.CODEC);

    private static Supplier<MapCodec<? extends EnchantmentEntityEffect>> registerEnchantmentEffect(String name, MapCodec<? extends  EnchantmentEntityEffect> codec) {
        return ENTITY_ENCHANTMENT_EFFECTS.register(name, () -> codec);
    }

    public static void reg(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
    }

}
