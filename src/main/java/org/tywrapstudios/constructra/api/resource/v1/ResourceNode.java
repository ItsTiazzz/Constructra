package org.tywrapstudios.constructra.api.resource.v1;

import net.minecraft.util.math.BlockPos;
import org.tywrapstudios.constructra.Constructra;

public class ResourceNode<T extends Resource> {
    private T resource;
    private final ResourcePurity purity;
    private final BlockPos centre;

    public ResourceNode(ResourcePurity purity, BlockPos centre) {
        this.purity = purity;
        this.centre = centre;
    }

    public ResourceNode(BlockPos centre) {
        this(ResourcePurity.random(), centre);
    }

    public T getResource() {
        return resource;
    }

    public ResourcePurity getPurity() {
        String conf = Constructra.config().resources.default_resource_purity;
        if (conf.equals("RANDOM")) {
            return purity;
        } else {
            return ResourcePurity.fromString(conf);
        }
    }

    public BlockPos getCentre() {
        return centre;
    }
}
