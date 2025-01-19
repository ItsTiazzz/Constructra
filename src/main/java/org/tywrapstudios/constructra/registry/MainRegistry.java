package org.tywrapstudios.constructra.registry;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.api.resource.v1.Resource;
import org.tywrapstudios.constructra.command.CaCommandImpl;

import java.util.ArrayList;
import java.util.List;

import static org.tywrapstudios.constructra.Constructra.id;

public class MainRegistry {
    public static List<ItemConvertible> ALL_ITEM_CONVERTIBLE_CONTENT = new ArrayList<>();

    public static void registerAll() {
        ComponentItems.register();
        ComponentItems.Group.INSTANCE.register();
        FuelItems.register();
        FuelItems.Group.INSTANCE.register();
        CommandRegistrationCallback.EVENT.register((dispatcher, access, env) -> CaCommandImpl.register(dispatcher, access));
        Resources.register();
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(ComponentItems.COMPONENT_ITEMS);
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(ComponentItems.BBlock.COMPONENT_BLOCKS);
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(ComponentItems.BBlock.COMPONENT_BLOCKS_NON_CUBE);
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(FuelItems.FUEL_ITEMS);
        ALL_ITEM_CONVERTIBLE_CONTENT.addAll(FuelItems.BIO_FUEL_ITEMS);
        Constructra.LOGGER.debug("ItemConvertible List size: " + ALL_ITEM_CONVERTIBLE_CONTENT.size());
    }

    protected static RegistryKey<Resource> resourceKey(String P) {
        return resourceKey(id(P));
    }

    protected static RegistryKey<Resource> resourceKey(Identifier P) {
        return RegistryKey.of(CaRegistries.Keys.RESOURCE, P);
    }

    protected static RegistryKey<Item> itemKey(String P) {
        return RegistryKey.of(RegistryKeys.ITEM, id(P));
    }

    protected static RegistryKey<Block> blockKey(String P) {
        return RegistryKey.of(RegistryKeys.BLOCK, id(P));
    }
}
