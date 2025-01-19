package org.tywrapstudios.constructra.api.resource.v1;

import net.minecraft.util.StringIdentifiable;

public enum ResourceRarity implements StringIdentifiable {
    NONE("none"),
    STARTER("starter"),
    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    EPIC("epic");

    private final String name;

    ResourceRarity(final String name) {
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}
