package net.makozort.makosmisc.reg;

import net.makozort.makosmisc.MakosMisc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AllSounds {
    public static DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MakosMisc.MODID);



    public static final Supplier<SoundEvent> DASH =
            registerSoundEvent("dash");


    public static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MakosMisc.MODID,name);

        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }


    public static void reg(IEventBus bus){
        SOUNDS.register(bus);
    }
}
