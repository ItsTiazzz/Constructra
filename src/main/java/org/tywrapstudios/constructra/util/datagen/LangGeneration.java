package org.tywrapstudios.constructra.util.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryWrapper;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.registry.ComponentItems;
import org.tywrapstudios.constructra.registry.MainRegistry;
import org.tywrapstudios.constructra.registry.FuelItems;

import java.util.concurrent.CompletableFuture;

public class LangGeneration extends FabricLanguageProvider {
    protected LangGeneration(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        for (ItemConvertible item : MainRegistry.ALL_ITEM_CONVERTIBLE_CONTENT) {
            if (item.equals(ComponentItems.BBlock.AI_LIMITER)) {
                translationBuilder.add((Block) item, "AI Limiter");
                continue;
            }
            autoGenerateName(translationBuilder, item);
        }

        translationBuilder.add("tag.item.constructra.industrial_fuels", "Industrial Fuels");
        translationBuilder.add("tag.item.constructra.biological_fuels", "Biological Fuels");
        translationBuilder.add("tag.item.constructra.crafting_components", "Component Items");
        translationBuilder.add(ComponentItems.Group.INSTANCE.langEntry, "Constructra - Components");
        translationBuilder.add(FuelItems.Group.INSTANCE.langEntry, "Constructra - Fuels");
        translationBuilder.add("purity.none", "None");
        translationBuilder.add("purity.impure", "Impure");
        translationBuilder.add("purity.normal", "Normal");
        translationBuilder.add("purity.pure", "Pure");
        translationBuilder.add("resource.minecraft.iron", "Iron");
        translationBuilder.add("resource.minecraft.gold", "Gold");
        translationBuilder.add("resource.constructra.empty", "Empty");
        translationBuilder.add("text.constructra.prompt.mining_instruction", "Hold %s to start mining");
        translationBuilder.add("text.constructra.prompt.mining_instruction_obstructed", "Hold %s to start mining ");
        translationBuilder.add("text.constructra.info.obstructed", "(Obstructed)");
        translationBuilder.add("text.constructra.command.constructra",
                """
                        Hello %s!
                        Welcome to Constructra, a mod centred around the creation of machines, tools and automation.
                        You can access the commands by using /constructra, /ca is a shorthand route!
                        If you don't know where to start, feel free to generate yourself either a codex item or press the keybind button to open the codex menu!
                        Have a good day!
                        Nexatek, Build the Future, One Bolt at a Time.""");
        translationBuilder.add("text.constructra.command.error.invalid_enum", "Invalid enum value");
        translationBuilder.add("text.constructra.command.error.non-overworld", "Attempted to manually place ResourceNode in non-overworld World. Skipping.");
        translationBuilder.add("text.constructra.command.flush", "Flushed all nodes from world.");
        translationBuilder.add("text.constructra.command.purge_start", "Starting Node Purge with range %s...");
        translationBuilder.add("text.constructra.command.purge", "Found and removed Node of type [%s] at [%s].");
        translationBuilder.add("text.constructra.command.purge_end_empty", "No Nodes were purged. Perhaps try a larger <range>?");
        translationBuilder.add("text.constructra.command.purge_end_inefficient", "The amount of Purged Nodes equalled the initial amount of Nodes in the world. For a more efficient Full Node Removal, consider using /[ca||constructra] nodes flush.");
        translationBuilder.add("text.constructra.command.purge_end", "End of Node Purge. Purged %s/%s Nodes in World. (%s left)");
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
