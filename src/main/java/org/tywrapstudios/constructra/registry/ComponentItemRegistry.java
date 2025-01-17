package org.tywrapstudios.constructra.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.tywrapstudios.constructra.api.item.v1.EasyItemGroup;

import java.util.ArrayList;
import java.util.List;

import static org.tywrapstudios.constructra.registry.Content.blockKey;
import static org.tywrapstudios.constructra.registry.Content.itemKey;

public class ComponentItemRegistry {
    public static final List<Item> COMPONENT_ITEMS = new ArrayList<>();

    public static final Item ALCLAD_ALUMINIUM_SHEET;
    public static final Item ALUMINIUM_SHEET;
    public static final Item ALUMINIUM_INGOT;
    public static final Item ALUMINIUM_SCRAP;
    public static final Item AUTOMATED_WIRING;
    public static final Item BLACK_POWDER;
    public static final Item CABLE;
    public static final Item TERBYSIUM_INGOT;
    public static final Item CIRCUIT_BOARD;
    public static final Item COMPUTER;
    public static final Item COOLING_SYSTEM;
    public static final Item COPPER_INGOT;
    public static final Item COPPER_POWDER;
    public static final Item COPPER_SHEET;
    public static final Item CRYSTAL_OSCILLATOR;
    public static final Item ELECTROMAGNETIC_CONTROL_ROD;
    public static final Item EMPTY_CANISTER;
    public static final Item EMPTY_FLUID_TANK;
    public static final Item ENCASED_PLUTONIUM_CELL;
    public static final Item ENCASED_URANIUM_CELL;
    public static final Item FABRIC;
    public static final Item FUEL;
    public static final Item HEAT_SINK;
    public static final Item HEAVY_MODULAR_FRAME;
    public static final Item HIGH_SPEED_CONNECTOR;
    public static final Item IRON_INGOT;
    public static final Item IRON_PLATE;
    public static final Item IRON_ROD;
    public static final Item MODULAR_ENGINE;
    public static final Item MOTOR;
    public static final Item NITRIC_ACID;
    public static final Item NITROGEN_GAS;
    public static final Item NON_FISSILE_URANIUM;
    public static final Item PACKAGED_ALUMINA_SOLUTION;
    public static final Item PACKAGED_NITRIC_ACID;
    public static final Item PACKAGED_NITROGEN_GAS;
    public static final Item PACKAGED_SULFURIC_ACID;
    public static final Item PACKAGED_WATER;
    public static final Item PACKAGED_PETROLEUM_COKE;
    public static final Item PLASTIC;
    public static final Item PLUTONIUM_PELLET;
    public static final Item PLUTONIUM_WASTE;
    public static final Item POLYMER_RESIN;
    public static final Item QUANTUM_COMPUTER;
    public static final Item QUARTZ_CRYSTAL;
    public static final Item TERBYSIUM_WIRE;
    public static final Item RADIO_CONTROL_UNIT;
    public static final Item REINFORCED_IRON_PLATE;
    public static final Item ROTOR;
    public static final Item RUBBER;
    public static final Item SCREW;
    public static final Item SILICA;
    public static final Item SMART_PLATING;
    public static final Item STATOR;
    public static final Item STEEL_INGOT;
    public static final Item STEEL_PIPE;
    public static final Item SUPER_COMPUTER;
    public static final Item SUPERPOSITION_OSCILLATOR;
    public static final Item TURBO_MOTOR;
    public static final Item URANIUM_WASTE;
    public static final Item WIRE;

    static {
        ALCLAD_ALUMINIUM_SHEET = create("alclad_aluminium_sheet");
        ALUMINIUM_SHEET = create("aluminium_sheet");
        ALUMINIUM_INGOT = create("aluminium_ingot");
        ALUMINIUM_SCRAP = create("aluminium_scrap");
        AUTOMATED_WIRING = create("automated_wiring");
        BLACK_POWDER = create("black_powder");
        CABLE = create("cable");
        TERBYSIUM_INGOT =create("terbysium_ingot");
        CIRCUIT_BOARD = create("circuit_board");
        COMPUTER = create("computer");
        COOLING_SYSTEM = create("cooling_system");
        COPPER_INGOT = Items.COPPER_INGOT;
        COPPER_POWDER = create("copper_powder");
        COPPER_SHEET = create("copper_sheet");
        CRYSTAL_OSCILLATOR = create("crystal_oscillator");
        ELECTROMAGNETIC_CONTROL_ROD = create("electromagnetic_control_rod");
        EMPTY_CANISTER = create("empty_canister");
        EMPTY_FLUID_TANK = create("empty_fluid_tank");
        ENCASED_PLUTONIUM_CELL = create("encased_plutonium_cell");
        ENCASED_URANIUM_CELL = create("encased_uranium_cell");
        FABRIC = create("fabric");
        FUEL = create("fuel");
        HEAT_SINK = create("heat_sink");
        HEAVY_MODULAR_FRAME = create("heavy_modular_frame");
        HIGH_SPEED_CONNECTOR = create("high_speed_connector");
        IRON_INGOT = Items.IRON_INGOT;
        IRON_PLATE = create("iron_plate");
        IRON_ROD = create("iron_rod");
        MODULAR_ENGINE = create("modular_engine");
        MOTOR = create("motor");
        NITRIC_ACID = create("nitric_acid");
        NITROGEN_GAS = create("nitrogen_gas");
        NON_FISSILE_URANIUM = create("non_fissile_uranium");
        PACKAGED_ALUMINA_SOLUTION = create("packaged_alumina_solution");
        PACKAGED_NITRIC_ACID = create("packaged_nitric_acid");
        PACKAGED_NITROGEN_GAS = create("packaged_nitrogen_gas");
        PACKAGED_SULFURIC_ACID = create("packaged_sulfuric_acid");
        PACKAGED_WATER = create("packaged_water");
        PACKAGED_PETROLEUM_COKE = create("packaged_petroleum_coke");
        PLASTIC = create("plastic");
        PLUTONIUM_PELLET = create("plutonium_pellet");
        PLUTONIUM_WASTE  = create("plutonium_waste");
        POLYMER_RESIN = create("polymer_resin");
        QUANTUM_COMPUTER = create("quantum_computer");
        QUARTZ_CRYSTAL = create("quartz_crystal");
        TERBYSIUM_WIRE = create("terbysium_wire");
        RADIO_CONTROL_UNIT = create("radio_control_unit");
        REINFORCED_IRON_PLATE = create("reinforced_iron_plate");
        ROTOR = create("rotor");
        RUBBER = create("rubber");
        SCREW = create("screw");
        SILICA  = create("silica");
        SMART_PLATING = create("smart_plating");
        STATOR = create("stator");
        STEEL_INGOT = create("steel_ingot");
        STEEL_PIPE = create("steel_pipe");
        SUPER_COMPUTER = create("super_computer");
        SUPERPOSITION_OSCILLATOR = create("superposition_oscillator");
        TURBO_MOTOR = create("turbo_motor");
        URANIUM_WASTE  = create("uranium_waste");
        WIRE = create("wire");
    }

    private static Item create(String id) {
        Item item = new Item(new Item.Settings()
                .registryKey(itemKey(id)));
        return create(id, item);
    }

    private static Item create(String id, Item item) {
        COMPONENT_ITEMS.add(item);
        return Registry.register(Registries.ITEM, itemKey(id), item);
    }

    public static void register() {
    }

    public static class BBlock {
        public static final List<Block> COMPONENT_BLOCKS = new ArrayList<>();
        public static final List<Block> COMPONENT_BLOCKS_NON_CUBE = new ArrayList<>();

        public static final Block ADAPTIVE_CONTROL_UNIT;
        public static final Block AI_LIMITER;
        public static final Block ASSEMBLY_DIRECTOR_SYSTEM;
        public static final Block CONCRETE;
        public static final Block ENCASED_INDUSTRIAL_BEAM;
        public static final Block FUSED_MODULAR_FRAME;
        public static final Block HEAVY_MODULAR_FRAME;
        public static final Block MODULAR_FRAME;
        public static final Block NUCLEAR_PASTA;
        public static final Block PRESSURE_CONVERSION_CUBE;
        public static final Block STEEL_BEAM;
        public static final Block VERSATILE_FRAMEWORK;

        static {
            ADAPTIVE_CONTROL_UNIT = createNonCube("adaptive_control_unit");
            AI_LIMITER = create("ai_limiter");
            ASSEMBLY_DIRECTOR_SYSTEM = createNonCube("assembly_director_system");
            CONCRETE = create("concrete");
            ENCASED_INDUSTRIAL_BEAM = create("encased_industrial_beam");
            FUSED_MODULAR_FRAME = createNonCube("fused_modular_frame");
            HEAVY_MODULAR_FRAME = createNonCube("heavy_modular_frame");
            MODULAR_FRAME = createNonCube("modular_frame");
            NUCLEAR_PASTA = createNonCube("nuclear_pasta");
            PRESSURE_CONVERSION_CUBE = createNonCube("pressure_conversion_cube");
            STEEL_BEAM = create("steel_beam");
            VERSATILE_FRAMEWORK = createNonCube("versatile_framework");
        }

        private static Block create(String id, Block block) {
            BlockItem item = new BlockItem(block, new Item.Settings()
                    .registryKey(itemKey(id)));
            COMPONENT_BLOCKS.add(block);
            Registry.register(Registries.ITEM, itemKey(id), item);
            return Registry.register(Registries.BLOCK, blockKey(id), block);
        }

        private static Block create(String id) {
            return create(id, new Block(AbstractBlock.Settings.create()));
        }

        private static Block createNonCube(String id, Block block) {
            BlockItem item = new BlockItem(block, new Item.Settings()
                    .registryKey(itemKey(id))
                    .useBlockPrefixedTranslationKey());
            COMPONENT_BLOCKS_NON_CUBE.add(block);
            Registry.register(Registries.ITEM, itemKey(id), item);
            return Registry.register(Registries.BLOCK, blockKey(id), block);
        }

        private static Block createNonCube(String id) {
            return createNonCube(id, new Block(AbstractBlock.Settings.create()
                    .registryKey(blockKey(id))));
        }
    }

    public static class Group extends EasyItemGroup {
        private Group() {
            super("components", COMPONENT_ITEMS, FABRIC);
        }

        public static final Group INSTANCE = new Group();
    }
}
