package net.makozort.makosmisc.event;

import net.makozort.makosmisc.handlers.DashHandler;
import net.makozort.makosmisc.reg.AllEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ModEvents {


    @SubscribeEvent
    private void playerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        DashHandler.removeFromMap(player);
    }

    @SubscribeEvent
    public void onFall(LivingFallEvent event) {
        if (event.getEntity() instanceof Player player && !DashHandler.getPlayerDash(player) && event.getDistance() <= 35) {
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void onLiving(PlayerTickEvent.Pre event) {
        Player p = event.getEntity();
        if (p.level() instanceof ServerLevel serverLevel) {
            if (p instanceof Player player) {
                if (!DashHandler.getPlayerDash(p)) {
                    if (player.isInFluidType() || player.onGround()) {
                        DashHandler.setDash(p, true);
                    }
                    if (!p.hasEffect(AllEffects.FLIGHT_EFFECT) || !p.isCreative() || !p.isSpectator()) {
                        serverLevel.sendParticles(ParticleTypes.END_ROD, player.getX(), player.getY(), player.getZ(), 1, 0, 0, 0, 0);
                    }
                }
            }
        }
    }
}
