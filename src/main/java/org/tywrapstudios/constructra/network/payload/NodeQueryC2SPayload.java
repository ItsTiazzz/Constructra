package org.tywrapstudios.constructra.network.payload;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import org.tywrapstudios.constructra.network.NetworkConstants;

public record NodeQueryC2SPayload(BlockPos pos) implements CustomPayload {
    public static final CustomPayload.Id<NodeQueryC2SPayload> ID = new CustomPayload.Id<>(NetworkConstants.NODE_QUERY_REQUEST_C2S);
    public static final PacketCodec<RegistryByteBuf, NodeQueryC2SPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, NodeQueryC2SPayload::pos, NodeQueryC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
