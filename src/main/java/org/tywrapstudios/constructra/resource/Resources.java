package org.tywrapstudios.constructra.resource;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.api.resource.v1.ImplementedResource;
import org.tywrapstudios.constructra.api.resource.v1.ResourceRarity;

// TODO: Replace this and with that EnumArgumentType with the more robust Registry system Minecraft has.
public enum Resources {
    EMPTY(new ImplementedResource(Items.AIR, ResourceRarity.NONE, Blocks.BARRIER, Constructra.id("empty"))),
    IRON(new ImplementedResource(Items.RAW_IRON, ResourceRarity.STARTER, Blocks.IRON_ORE, Identifier.ofVanilla("iron"))),
    GOLD(new ImplementedResource(Items.RAW_GOLD, ResourceRarity.STARTER, Blocks.GOLD_ORE, Identifier.ofVanilla("gold"))),;

    private final ImplementedResource resource;

    Resources(ImplementedResource resource) {
        this.resource = resource;
    }

    public ImplementedResource get() {
        return resource;
    }

    public static ImplementedResource getFromId(Identifier id) {
        for (Resources resource : Resources.values()) {
            if (resource.get().getIdentifier().equals(id)) {
                return resource.get();
            }
        }
        return null;
    }
}
