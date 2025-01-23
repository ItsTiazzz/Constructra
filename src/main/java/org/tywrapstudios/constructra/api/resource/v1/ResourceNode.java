package org.tywrapstudios.constructra.api.resource.v1;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.registry.CaRegistries;
import org.tywrapstudios.constructra.registry.Resources;

import java.util.Random;
import java.util.function.Supplier;

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
    private int totalHarvests;
    private long lastHarvestTime;
    public static final Supplier<Integer> HARVESTS_TO_DE_OBSTRUCT = () -> Constructra.config().resources.consecutive_harvests_for_destruction;

    protected ResourceNode(T resource, ResourcePurity purity, BlockPos centre, boolean obstructed, int totalHarvests) {
        this.resource = resource;
        this.purity = purity;
        this.centre = centre;
        this.obstructed = obstructed;
        this.totalHarvests = totalHarvests;
        this.lastHarvestTime = 0;
    }

    public ResourceNode(T resource, ResourcePurity purity, BlockPos centre, boolean obstructed) {
        this(resource, purity, centre, obstructed, 0);
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

    public long getLastHarvestTime() {
        return lastHarvestTime;
    }

    public int getTotalHarvests() {
        return totalHarvests;
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

    public boolean tryHarvest(ServerWorld world) {
        long currentTime = world.getTime();
        long harvestCooldown = (long)(20 * purity.getMiningTimeMultiplier()); // Convert purity to ticks

        if (currentTime - lastHarvestTime < harvestCooldown) {
            return false;
        }

        // Spawn the retrievable item
        ItemStack harvestStack = new ItemStack(resource.getRetrievableItem());
        ItemEntity itemEntity = new ItemEntity(world,
                centre.getX() + 0.5,
                centre.getY() + 0.5,
                centre.getZ() + 0.5,
                harvestStack);
        world.spawnEntity(itemEntity);
        totalHarvests++;

        // Handle total harvests for obstructed nodes
        if (isObstructed()) {
            if (totalHarvests >= HARVESTS_TO_DE_OBSTRUCT.get()) {
                deObstruct();
            }
        }

        lastHarvestTime = currentTime;
        return true;
    }

    public static ResourceNode<?> createDefaulted() {
        return new ResourceNode<>(Resources.IRON, ResourcePurity.NONE, BlockPos.ORIGIN, false);
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
