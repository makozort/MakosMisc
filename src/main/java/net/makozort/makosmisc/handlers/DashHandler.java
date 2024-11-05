package net.makozort.makosmisc.handlers;

import net.makozort.makosmisc.networking.payloads.DashPayload;
import net.makozort.makosmisc.reg.AllEffectDataComponentTypes;
import net.makozort.makosmisc.reg.AllSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;
import java.util.Map;

public class DashHandler {

    private static Map<Player, Boolean> dashMap = new HashMap<>();

    public static void Handle(DashPayload p, IPayloadContext c) {
        Player player = c.player();
        if (!player.isInFluidType()) {
            if (!dashMap.containsKey(player)) {
                tryDash(player);
            } else if (dashMap.get(player)) {
                tryDash(player);
            }
        }
    }


    private static void tryDash(Player player) {
        if (EnchantmentHelper.has(player.getItemBySlot(EquipmentSlot.FEET), AllEffectDataComponentTypes.DASH.get())) {
            dashMap.put(player, false);
            dash(player);
        }
    }

    public static boolean getPlayerDash(Player player) {
        if (!dashMap.containsKey(player)) {
            return false;
        }
        return dashMap.get(player);
    }

    public static void removeFromMap(Player player) {
        dashMap.remove(player);
    }

    private static void dash(Player player) {
        player.level().playSound(null, player.getOnPos(), AllSounds.DASH.get(), SoundSource.MASTER, 1, 1F);
        Vec3 currentVelocity = player.getDeltaMovement();
        player.hurtMarked = true;
        Vec3 lookVector = player.getLookAngle();
        Vec3 dashVelocity = lookVector.scale(.8);
        Vec3 newVelocity = currentVelocity.add(dashVelocity);
        player.setDeltaMovement(newVelocity);
        player.resetFallDistance();
    }


    public static void setDash(Player p, Boolean b) {
        dashMap.put(p, b);
    }
}
