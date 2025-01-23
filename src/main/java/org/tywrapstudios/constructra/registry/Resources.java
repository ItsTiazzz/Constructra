package org.tywrapstudios.constructra.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.tywrapstudios.constructra.api.resource.v1.ImplementedResource;
import org.tywrapstudios.constructra.api.resource.v1.Resource;
import org.tywrapstudios.constructra.api.resource.v1.ResourceRarity;

import static org.tywrapstudios.constructra.Constructra.id;
import static org.tywrapstudios.constructra.registry.MainRegistry.blockKey;
import static org.tywrapstudios.constructra.registry.MainRegistry.itemKey;

public class Resources {
    public static final Resource IRON;
    public static final Resource GOLD;
    public static final Block DEV_BLOCK;
    public static final Resource DEV;

    static {
        IRON = create(Items.RAW_IRON, ResourceRarity.STARTER, Blocks.IRON_ORE, Identifier.ofVanilla("iron"));
        GOLD = create(Items.RAW_GOLD, ResourceRarity.STARTER, Blocks.GOLD_ORE, Identifier.ofVanilla("gold"));
        DEV_BLOCK = block("dev_block");
        DEV = create(Items.EMERALD, ResourceRarity.EPIC, DEV_BLOCK, "dev_resource");
    }

    private static Resource create(ItemConvertible retrievableItem, ResourceRarity rarity, Block harvestBlock, String id) {
        return create(retrievableItem, rarity, harvestBlock, id(id));
    }

    private static Resource create(ItemConvertible retrievableItem, ResourceRarity rarity, Block harvestBlock, Identifier identifier) {
        return create(new ImplementedResource(retrievableItem, rarity, harvestBlock, identifier));
    }

    private static Resource create(Resource resource) {
        return Registry.register(CaRegistries.RESOURCE, MainRegistry.resourceKey(resource.getIdentifier()), resource);
    }

    private static Block block(String id) {
        Block block = new Block(AbstractBlock.Settings.create().registryKey(blockKey(id)));
        Registry.register(Registries.ITEM, itemKey(id), new BlockItem(block, new Item.Settings().registryKey(itemKey(id))));
        return Registry.register(Registries.BLOCK, blockKey(id), block);
    }

    public static void register() {
    }
}
