package org.tywrapstudios.constructra.api.resource.v1;

import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.tywrapstudios.constructra.registry.CaRegistries;
import org.tywrapstudios.constructra.registry.Content;

public interface Resource {
    Identifier getIdentifier();
    ItemConvertible getRetrievableItem();
    Block getHarvestBlock();
    ResourceRarity getRarity();

    default Text getName() {
        return Text.translatable(getTranslationKey());
    }

    default String getTranslationKey() {
        return "resource." + this.getIdentifier().getNamespace() + "." + this.getIdentifier().getPath();
    }
}
