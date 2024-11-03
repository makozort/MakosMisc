package net.makozort.makosmisc.event;

import net.makozort.makosmisc.MakosMisc;
import net.makozort.makosmisc.networking.payloads.DashPayload;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;


@EventBusSubscriber(value = Dist.CLIENT, modid = MakosMisc.MODID)
public class ClientEvents {

    private static final long DOUBLE_PRESS_INTERVAL = 300;
    private static long lastJumpKeyPressTime = 0;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        int jumpKeyCode = Minecraft.getInstance().options.keyJump.getKey().getValue();

        if (event.getKey() == jumpKeyCode && event.getAction() == GLFW.GLFW_PRESS) {
            long currentTime = System.currentTimeMillis();
            long timeSinceLastPress = currentTime - lastJumpKeyPressTime;

            if (timeSinceLastPress <= DOUBLE_PRESS_INTERVAL) {
                PacketDistributor.sendToServer(new DashPayload());
                lastJumpKeyPressTime = 0;
            } else {
                lastJumpKeyPressTime = currentTime;
            }
        }
    }
}
