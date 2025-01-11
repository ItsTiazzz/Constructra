package org.tywrapstudios.constructra.registry;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.tywrapstudios.constructra.api.item.v1.EasyItemGroup;

import java.util.ArrayList;
import java.util.List;

import static org.tywrapstudios.constructra.registry.Content.itemKey;

public class FuelItemRegistry {
    public static List<Item> FUEL_ITEMS = new ArrayList<>();
    public static List<Item> BIO_FUEL_ITEMS = new ArrayList<>();

    public static final Item BATTERY;
    public static final Item BIOMASS;
    public static final Item COAL;
    public static final Item COMPACTED_COAL;
    public static final Item CRUDE_OIL;
    public static final Item NEXONIUM_FUEL_ROD;
    public static final Item HEAVY_OIL_RESIDUE;
    public static final Item IONIZED_FUEL;
    // LEAVES (tag)
    public static final Item LIQUID_BIOFUEL;
    // MYCELIA (tag)
    public static final Item PACKAGED_FUEL;
    public static final Item PACKAGED_HEAVY_OIL_RESIDUE;
    public static final Item PACKAGED_IONIZED_FUEL;
    public static final Item PACKAGED_LIQUID_BIOFUEL;
    public static final Item PACKAGED_OIL;
    public static final Item PACKAGED_ROCKET_FUEL;
    public static final Item PACKAGED_TURBO_FUEL;
    public static final Item PETROLEUM_COKE;
    public static final Item PLUTONIUM_FUEL_ROD;
    public static final Item ROCKET_FUEL;
    public static final Item SOLID_BIOFUEL;
    public static final Item TURBO_FUEL;
    public static final Item URANIUM_FUEL_ROD;
    // WOOD (tag)


    static {
        BATTERY = create("battery");
        BIOMASS = createBio("biomass");
        COAL = Items.COAL;
        COMPACTED_COAL = Items.COAL_BLOCK;
        CRUDE_OIL = create("crude_oil");
        NEXONIUM_FUEL_ROD = create("nexonium_fuel_rod");
        HEAVY_OIL_RESIDUE = create("heavy_oil_residue");
        IONIZED_FUEL = create("ionized_fuel");
        LIQUID_BIOFUEL = createBio("liquid_biofuel");
        PACKAGED_FUEL = create("packaged_fuel");
        PACKAGED_HEAVY_OIL_RESIDUE = create("packaged_heavy_oil_residue");
        PACKAGED_IONIZED_FUEL = create("packaged_ionized_fuel");
        PACKAGED_LIQUID_BIOFUEL = create("packaged_liquid_biofuel");
        PACKAGED_OIL = create("packaged_oil");
        PACKAGED_ROCKET_FUEL = create("packaged_rocket_fuel");
        PACKAGED_TURBO_FUEL = create("packaged_turbo_fuel");
        PETROLEUM_COKE = create("petroleum_coke");
        PLUTONIUM_FUEL_ROD = create("plutonium_fuel_rod");
        ROCKET_FUEL = create("rocket_fuel");
        SOLID_BIOFUEL = createBio("solid_biofuel");
        TURBO_FUEL = create("turbo_fuel");
        URANIUM_FUEL_ROD = create("uranium_fuel_rod");
    }

    private static Item create(String id) {
        Item item = new Item(new Item.Settings()
                .registryKey(itemKey(id)));
        return create(id, item, false);
    }

    private static Item createBio(String id) {
        Item item = new Item(new Item.Settings()
                .registryKey(itemKey(id)));
        return create(id, item, true);
    }

    private static Item create(String id, Item item, boolean bio) {
        if (bio) {
            BIO_FUEL_ITEMS.add(item);
        } else {
            FUEL_ITEMS.add(item);
        }
        return Registry.register(Registries.ITEM, itemKey(id), item);
    }

    public static void register() {
    }

    public static class Group extends EasyItemGroup {
        Group() {
            super("fuels", FUEL_ITEMS, BIOMASS);
        }

        @Override
        public void register() {
            this.ENTRIES.addAll(BIO_FUEL_ITEMS);
            super.register();
        }

        public static Group INSTANCE = new Group();
    }
}
