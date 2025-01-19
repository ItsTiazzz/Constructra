package org.tywrapstudios.constructra.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.api.resource.v1.ImplementedResource;
import org.tywrapstudios.constructra.api.resource.v1.Resource;
import org.tywrapstudios.constructra.api.resource.v1.ResourceRarity;

public class Resources {
    public static final Resource EMPTY;
    public static final Resource IRON;
    public static final Resource GOLD;

    static {
        EMPTY = create(Items.AIR, ResourceRarity.NONE, Blocks.BARRIER, "empty");
        IRON = create(Items.RAW_IRON, ResourceRarity.STARTER, Blocks.IRON_ORE, Identifier.ofVanilla("iron"));
        GOLD = create(Items.RAW_GOLD, ResourceRarity.STARTER, Blocks.GOLD_ORE, Identifier.ofVanilla("gold"));
    }

    private static Resource create(ItemConvertible retrievableItem, ResourceRarity rarity, Block harvestBlock, String id) {
        return create(retrievableItem, rarity, harvestBlock, Constructra.id(id));
    }

    private static Resource create(ItemConvertible retrievableItem, ResourceRarity rarity, Block harvestBlock, Identifier identifier) {
        return create(new ImplementedResource(retrievableItem, rarity, harvestBlock, identifier));
    }

    private static Resource create(Resource resource) {
        return Registry.register(CaRegistries.RESOURCE, MainRegistry.resourceKey(resource.getIdentifier()), resource);
    }

    public static void register() {
    }
}
