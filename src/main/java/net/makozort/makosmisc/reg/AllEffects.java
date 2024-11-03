package net.makozort.makosmisc.reg;


import net.makozort.makosmisc.MakosMisc;
import net.makozort.makosmisc.content.effect.FlightEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public abstract class AllEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(Registries.MOB_EFFECT, MakosMisc.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> FLIGHT_EFFECT = MOB_EFFECTS.register("flight_effect", () -> new FlightEffect(
            MobEffectCategory.BENEFICIAL,
            0xffffff
    ).addAttributeModifier(NeoForgeMod.CREATIVE_FLIGHT, ResourceLocation.withDefaultNamespace("effect.makosmisc.flight_effect"), 1, AttributeModifier.Operation.ADD_VALUE));


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }


}