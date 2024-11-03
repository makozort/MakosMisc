package net.makozort.makosmisc.reg;

import net.makozort.makosmisc.MakosMisc;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class AllEffectDataComponentTypes {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, MakosMisc.MODID);


    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> DASH =
            register("dash_component", builder -> builder.persistent(Unit.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> HIDDEN =
            register("hidden_component", builder -> builder.persistent(Unit.CODEC));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }


    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
