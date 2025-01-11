package org.tywrapstudios.constructra.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.tywrapstudios.constructra.Constructra;

public class Tags {
    public enum IItems {
        INDUSTRIAL_FUELS("industrial_fuels"),
        BIOLOGICAL_FUELS("biological_fuels"),
        CRAFTING_COMPONENTS("crafting_components"),
        RADIOACTIVE("radioactive"),;

        private final TagKey<Item> tagKey;

        IItems(String name) {
            this.tagKey = of(Constructra.id(name));
        }

        public TagKey<Item> get() { return tagKey; }

        private static TagKey<Item> of(Identifier id) {
            return TagKey.of(Registries.ITEM.getKey(), id);
        }
    }
}
