package org.tywrapstudios.constructra.api.resource.v1;

import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

public abstract class AbstractResource implements Resource {
    private final Identifier identifier;
    private final ItemConvertible retrievableItem;
    private final ResourceRarity rarity;

    public AbstractResource(ItemConvertible retrievableItem, ResourceRarity rarity, Identifier identifier) {
        this.identifier = identifier;
        this.retrievableItem = retrievableItem;
        this.rarity = rarity;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public ItemConvertible getRetrievableItem() {
        return retrievableItem;
    }

    @Override
    public ResourceRarity getRarity() {
        return rarity;
    }
}
