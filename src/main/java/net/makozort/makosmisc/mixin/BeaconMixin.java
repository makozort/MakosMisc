package net.makozort.makosmisc.mixin;

import net.makozort.makosmisc.content.block.EnderLensBlock;
import net.makozort.makosmisc.reg.AllEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;


@Mixin(BeaconBlockEntity.class)
public abstract class BeaconMixin extends BlockEntity {
    public BeaconMixin(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Inject(method = "applyEffects", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void onApplyMaxTierEffects(Level level, BlockPos pos, int beaconLevel, Holder<MobEffect> primaryEffect, Holder<MobEffect> secondaryEffect, CallbackInfo ci, double d0, int i, int j, AABB aabb, List list, Iterator var11, Player player) {
        if (level.getBlockState(pos.above()).getBlock() instanceof EnderLensBlock) {
            player.addEffect(new MobEffectInstance(AllEffects.FLIGHT_EFFECT, 320));
        }
    }
}