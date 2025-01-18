package org.tywrapstudios.constructra.resource;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.tywrapstudios.constructra.api.resource.v1.ImplementedResource;
import org.tywrapstudios.constructra.api.resource.v1.ResourceRarity;

public class Resources {
    public static final ImplementedResource IRON;
    public static final ImplementedResource GOLD;

    static {
        IRON = new ImplementedResource(Items.RAW_IRON, ResourceRarity.STARTER, Blocks.IRON_ORE, Identifier.ofVanilla("iron"));
        GOLD = new ImplementedResource(Items.RAW_GOLD, ResourceRarity.STARTER, Blocks.GOLD_ORE, Identifier.ofVanilla("gold"));
    }
}
