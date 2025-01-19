package org.tywrapstudios.constructra.api.resource.v1;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.registry.CaRegistries;
import org.tywrapstudios.constructra.registry.Resources;

import java.util.Random;

public class ResourceNode<T extends Resource> {
    public static final PacketCodec<ByteBuf, ResourceNode<?>> PACKET_CODEC = new PacketCodec<>() {
        @Override
        public ResourceNode<?> decode(ByteBuf buf) {
            PacketByteBuf packetBuf = new PacketByteBuf(buf);
            Resource resource = CaRegistries.RESOURCE.get(Identifier.PACKET_CODEC.decode(buf));
            ResourcePurity purity = ResourcePurity.PACKET_CODEC.decode(buf);
            BlockPos pos = packetBuf.readBlockPos();
            boolean obstructed = packetBuf.readBoolean();

            return new ResourceNode<>(resource, purity, pos, obstructed);
        }

        @Override
        public void encode(ByteBuf buf, ResourceNode<?> value) {
            PacketByteBuf packetBuf = new PacketByteBuf(buf);
            Identifier.PACKET_CODEC.encode(buf, value.getResource().getIdentifier());
            ResourcePurity.PACKET_CODEC.encode(buf, value.getPurity());
            packetBuf.writeBlockPos(value.getCentre());
            packetBuf.writeBoolean(value.isObstructed());
        }
    };

    private final T resource;
    private final ResourcePurity purity;
    private final BlockPos centre;
    private boolean obstructed;

    public ResourceNode(T resource, ResourcePurity purity, BlockPos centre, boolean obstructed) {
        this.resource = resource;
        this.purity = purity;
        this.centre = centre;
        this.obstructed = obstructed;
    }

    public ResourceNode(T resource, BlockPos centre, boolean obstructed) {
        this(resource, ResourcePurity.random(), centre, obstructed);
    }

    public ResourceNode(T resource, BlockPos centre) {
        this(resource, centre, new Random().nextBoolean());
    }

    public T getResource() {
        assert this.resource != null : "Resource of Node is null: " + this;
        return this.resource;
    }

    public ResourcePurity getPurity() {
        String conf = Constructra.config().resources.default_resource_purity;
        if (conf.equals("RANDOM")) {
            return this.purity;
        } else {
            return ResourcePurity.fromString(conf);
        }
    }

    public BlockPos getCentre() {
        return this.centre;
    }

    public boolean isObstructed() {
        return this.obstructed && Constructra.config().resources.do_obstructions;
    }

    public void deObstruct() {
        setObstructed(false);
    }

    public void setObstructed(boolean obstructed) {
        this.obstructed = obstructed;
    }

    protected boolean createOriginBlock(World world) {
        try {
            Constructra.LOGGER.debug("Creating origin block for: " + this);
            world.setBlockState(centre, resource.getHarvestBlock().getDefaultState());
            return true;
        } catch (Exception e) {
            if (this.getResource() != null) {
                Constructra.LOGGER.error("Failed to place Origin for Resource: " + resource.getIdentifier());
            }
            e.printStackTrace();
            return false;
        }
    }

    public static ResourceNode<?> empty() {
        return new ResourceNode<>(Resources.EMPTY, ResourcePurity.NONE, BlockPos.ORIGIN, false);
    }

    public String toSimpleString() {
        return "ResourceNode{" +
                "resource=" + resource.getIdentifier() +
                "}";
    }

    @Override
    public String toString() {
        return "ResourceNode{" +
                "resource=" + resource +
                ", purity=" + purity +
                ", centre=" + centre +
                ", obstructed=" + obstructed +
                '}';
    }
}
