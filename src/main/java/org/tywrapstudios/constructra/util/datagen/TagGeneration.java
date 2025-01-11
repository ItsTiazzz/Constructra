package org.tywrapstudios.constructra.util.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.registry.ComponentItemRegistry;
import org.tywrapstudios.constructra.registry.FuelItemRegistry;
import org.tywrapstudios.constructra.registry.Tags;

import java.util.concurrent.CompletableFuture;

public class TagGeneration {
    public static class IItems extends FabricTagProvider.ItemTagProvider {
        public IItems(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
            super(output, completableFuture, null);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            for (Item item : ComponentItemRegistry.COMPONENT_ITEMS) {
                getOrCreateTagBuilder(Tags.IItems.CRAFTING_COMPONENTS.get())
                        .add(item);
                Constructra.LOGGER.debug("You're it! Component Item: " + item);
            }
            for (Item item : FuelItemRegistry.BIO_FUEL_ITEMS) {
                getOrCreateTagBuilder(Tags.IItems.BIOLOGICAL_FUELS.get())
                        .add(item);
                Constructra.LOGGER.debug("You're it! Biofuel Item: " + item);
            }
            for (Item item : FuelItemRegistry.FUEL_ITEMS) {
                getOrCreateTagBuilder(Tags.IItems.INDUSTRIAL_FUELS.get())
                        .add(item);
                Constructra.LOGGER.debug("You're it! Fuel Item: " + item);
            }
        }
    }
}
