package net.makozort.makosmisc.networking.payloads;

import io.netty.buffer.ByteBuf;
import net.makozort.makosmisc.MakosMisc;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DashPayload() implements CustomPacketPayload {

    public static final DashPayload INSTANCE = new DashPayload();

    public static final CustomPacketPayload.Type<DashPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MakosMisc.MODID, "dash_payload"));

    public static final StreamCodec<ByteBuf, DashPayload> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    public DashPayload() {
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

