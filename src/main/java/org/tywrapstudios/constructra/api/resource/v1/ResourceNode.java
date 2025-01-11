package org.tywrapstudios.constructra.api.resource.v1;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.tywrapstudios.constructra.Constructra;

import java.util.Random;

public class ResourceNode<T extends Resource> {
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
        assert this.resource != null;
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
        return this.obstructed;
    }

    public void deObstruct() {
        setObstructed(false);
    }

    public void setObstructed(boolean obstructed) {
        this.obstructed = obstructed;
    }

    public boolean createOriginBlock(World world) {
        try {
            Constructra.LOGGER.debug("Creating origin block for: " + this);
            world.setBlockState(centre, resource.getOriginBlock().getDefaultState());
            return true;
        } catch (Exception e) {
            if (this.getResource() != null) {
                Constructra.LOGGER.error("Failed to place Origin for Resource: " + resource.getIdentifier());
            }
            e.printStackTrace();
            return false;
        }
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
