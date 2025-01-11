package org.tywrapstudios.constructra.api.resource.v1;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

import static net.minecraft.util.Formatting.*;

public enum ResourceRarity implements StringIdentifiable {
    NONE(0, "none", WHITE),
    STARTER(1, "starter", GRAY),
    COMMON(2, "common", YELLOW),
    UNCOMMON(3, "uncommon", AQUA),
    RARE(4, "rare", LIGHT_PURPLE),
    EPIC(5, "epic", GOLD);

    public static final Codec<ResourceRarity> CODEC = StringIdentifiable.createBasicCodec(ResourceRarity::values);

    public static final IntFunction<ResourceRarity> ID_TO_VALUE = ValueLists.createIdToValueFunction(
            (ToIntFunction<ResourceRarity>) (value) -> value.index, values(), ValueLists.OutOfBoundsHandling.ZERO
    );
    public static final PacketCodec<ByteBuf, ResourceRarity> PACKET_CODEC = PacketCodecs.indexed(
            ID_TO_VALUE, (value) -> value.index
    );

    private final int index;
    private final String name;
    private final Formatting formatting;

    ResourceRarity(final int index, final String name, final Formatting formatting) {
        this.index = index;
        this.name = name;
        this.formatting = formatting;
    }

    public Formatting getFormatting() {
        return this.formatting;
    }

    @Override
    public String asString() {
        return name;
    }
}
