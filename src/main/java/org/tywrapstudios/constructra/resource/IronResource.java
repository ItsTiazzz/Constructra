package org.tywrapstudios.constructra.resource;

import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.tywrapstudios.constructra.api.resource.v1.AbstractResource;
import org.tywrapstudios.constructra.api.resource.v1.ResourceRarity;

public class IronResource extends AbstractResource {
    public IronResource() {
        super(Items.RAW_IRON, ResourceRarity.STARTER, Identifier.of("iron"));
    }
}
