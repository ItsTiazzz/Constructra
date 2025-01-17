package org.tywrapstudios.constructra.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.tywrapstudios.constructra.Constructra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.tywrapstudios.constructra.Constructra.id;

public class Content {
    public static List<ItemConvertible> ALL_ITEM_CONVERTIBLE_CONTENT = new ArrayList<>();

    public static void registerAll() {
        ComponentItemRegistry.register();
        ComponentItemRegistry.Group.INSTANCE.register();
        FuelItemRegistry.register();
        FuelItemRegistry.Group.INSTANCE.register();
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(ComponentItemRegistry.COMPONENT_ITEMS);
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(ComponentItemRegistry.BBlock.COMPONENT_BLOCKS);
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(ComponentItemRegistry.BBlock.COMPONENT_BLOCKS_NON_CUBE);
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(FuelItemRegistry.FUEL_ITEMS);
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(FuelItemRegistry.BIO_FUEL_ITEMS);
        Constructra.LOGGER.debug("ItemConvertible List size: " + ALL_ITEM_CONVERTIBLE_CONTENT.size());
    }

    protected static RegistryKey<Item> itemKey(String P) {
        return RegistryKey.of(RegistryKeys.ITEM, id(P));
    }

    protected static RegistryKey<Block> blockKey(String P) {
        return RegistryKey.of(RegistryKeys.BLOCK, id(P));
    }
}
