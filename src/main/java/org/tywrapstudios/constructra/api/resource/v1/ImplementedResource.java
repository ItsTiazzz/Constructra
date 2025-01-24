package org.tywrapstudios.constructra.api.resource.v1;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

public class ImplementedResource implements Resource {
    private final Identifier identifier;
    private final ItemConvertible retrievableItem;
    private final ResourceRarity rarity;
    private final Block harvestBlock;

    /**
     * Constructs a new ImplementedResource, there is also the option of extending this class or implementing {@link Resource} to create your own Resource class.
     * @param retrievableItem the item that will be harvested from this Resource.
     * @param rarity the rarity of this type of Resource.
     * @param harvestBlock the block that the Resource will place in the world to be harvested from.
     * @param identifier the {@link Identifier} of this Resource, can be used upon Registry.
     */
    public ImplementedResource(ItemConvertible retrievableItem, ResourceRarity rarity, Block harvestBlock, Identifier identifier) {
        this.identifier = identifier;
        this.retrievableItem = retrievableItem;
        this.rarity = rarity;
        this.harvestBlock = harvestBlock;
    }

    @Override
    public Identifier getIdentifier() {
        return this.identifier;
    }

    @Override
    public ItemConvertible getRetrievableItem() {
        return this.retrievableItem;
    }

    @Override
    public ResourceRarity getRarity() {
        return this.rarity;
    }

    @Override
    public Block getHarvestBlock() {
        return this.harvestBlock;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "harvestBlock=" + harvestBlock +
                ", rarity=" + rarity +
                ", retrievableItem=" + retrievableItem +
                '}';
    }
}
