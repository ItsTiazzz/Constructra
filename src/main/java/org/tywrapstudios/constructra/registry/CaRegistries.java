package org.tywrapstudios.constructra.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.*;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.api.resource.v1.Resource;

public class CaRegistries {
    public static final DefaultedRegistry<Resource> RESOURCE;

    static {
        RESOURCE = FabricRegistryBuilder.createDefaulted(Keys.RESOURCE,Constructra.id("empty")).buildAndRegister();
    }

    public static class Keys {
        public static final RegistryKey<Registry<Resource>> RESOURCE = of("resource");

        private static <T> RegistryKey<Registry<T>> of(String id) {
            return RegistryKey.ofRegistry(Constructra.id(id));
        }
    }

    public static void initialize() {
    }
}
