package org.tywrapstudios.constructra.api.item.v1;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.tywrapstudios.constructra.Constructra.id;

public abstract class EasyItemGroup {
    public String id;
    public String langEntry;
    private final List<ItemConvertible> ENTRIES = new ArrayList<>();
    private ItemConvertible ICON;

    protected EasyItemGroup(String name, List<? extends ItemConvertible> items, ItemConvertible icon) {
        id = name;
        ENTRIES.addAll(items);
        langEntry = "itemGroup.constructra." + id;
        ICON = icon;
    }

    private final Supplier<ItemGroup> GROUP = () -> FabricItemGroup.builder()
            .displayName(Text.translatable(langEntry))
            .icon(() -> new ItemStack(ICON))
            .entries((displayContext, entries) -> {
                for (ItemConvertible item : ENTRIES) {
                    entries.add(item);
                }
            })
            .build();

    private RegistryKey<ItemGroup> key() {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, id(id));
    }

    public void register() {
        Registry.register(Registries.ITEM_GROUP, key(), GROUP.get());
    }

    public ItemGroup get() {
        return GROUP.get();
    }
}
