package org.tywrapstudios.constructra.util.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryWrapper;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.registry.ComponentItemRegistry;
import org.tywrapstudios.constructra.registry.Content;
import org.tywrapstudios.constructra.registry.FuelItemRegistry;

import java.util.concurrent.CompletableFuture;

public class LangGeneration extends FabricLanguageProvider {
    protected LangGeneration(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        for (ItemConvertible item : Content.ALL_ITEM_CONVERTIBLE_CONTENT) {
            if (item.equals(ComponentItemRegistry.AI_LIMITER)) {
                translationBuilder.add((Item) item, "AI Limiter");
                continue;
            }
            autoGenerateName(translationBuilder, item);
        }

        translationBuilder.add("tag.item.constructra.industrial_fuels", "Industrial Fuels");
        translationBuilder.add("tag.item.constructra.biological_fuels", "Biological Fuels");
        translationBuilder.add("tag.item.constructra.crafting_components", "Component Items");
        translationBuilder.add(ComponentItemRegistry.Group.INSTANCE.langEntry, "Constructra - Components");
        translationBuilder.add(FuelItemRegistry.Group.INSTANCE.langEntry, "Constructra - Fuels");
    }

    private static void autoGenerateName(TranslationBuilder translationBuilder, ItemConvertible item) {
        String itemKey = item.asItem().getTranslationKey();
        String generatedName = "";
        if (itemKey.startsWith("item")) {
            generatedName = itemKey.replace("item.constructra.", "");
        } else if (itemKey.startsWith("block")) {
            generatedName = itemKey.replace("block.constructra.", "");
        }
        String[] parts = generatedName.split("_");
        StringBuilder builder = new StringBuilder();
        for (String part : parts) {
            builder.append(part.replaceFirst(Character.toString(part.charAt(0)), Character.toString(Character.toUpperCase(part.charAt(0)))));
            builder.append(" ");
            Constructra.LOGGER.debug("What's your name? Uhh: " + builder);
        }
        builder.deleteCharAt(builder.length() - 1);
        generatedName = builder.toString();
        Constructra.LOGGER.debug("Yeah, it's: " + generatedName);
        translationBuilder.add(itemKey, generatedName);
    }
}
