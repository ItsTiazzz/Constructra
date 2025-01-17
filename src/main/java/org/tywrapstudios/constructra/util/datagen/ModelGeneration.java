package org.tywrapstudios.constructra.util.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;
import org.tywrapstudios.constructra.registry.ComponentItemRegistry;
import org.tywrapstudios.constructra.registry.FuelItemRegistry;

import static org.tywrapstudios.constructra.registry.ComponentItemRegistry.BBlock;

public class ModelGeneration extends FabricModelProvider {
    public ModelGeneration(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        for (Block block : BBlock.COMPONENT_BLOCKS) {
            if (BBlock.COMPONENT_BLOCKS_NON_CUBE.contains(block)) {
                continue;
            }
            generator.registerSimpleCubeAll(block);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        for (Item item : ComponentItemRegistry.COMPONENT_ITEMS) {
            simpleItem(item, generator);
        }
        for (Item item : FuelItemRegistry.BIO_FUEL_ITEMS) {
            simpleItem(item, generator);
        }
        for (Item item : FuelItemRegistry.FUEL_ITEMS) {
            simpleItem(item, generator);
        }
    }

    public static void simpleItem(Item item, ItemModelGenerator generator) {
        generator.register(item, Models.GENERATED);
    }
}
