package org.tywrapstudios.constructra.api.resource.v1;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public enum ResourcePurity implements StringIdentifiable {
    NONE(0, "none", Formatting.RED),
    IMPURE(1, "impure", Formatting.DARK_AQUA),
    NORMAL(2, "normal", Formatting.GOLD),
    PURE(3, "pure", Formatting.LIGHT_PURPLE),;

    private final int index;
    private final String name;
    private final Formatting formatting;

    public static final IntFunction<ResourcePurity> ID_TO_VALUE = ValueLists.createIdToValueFunction(
            (ToIntFunction<ResourcePurity>) (value) -> value.index, values(), ValueLists.OutOfBoundsHandling.ZERO
    );
    public static final PacketCodec<ByteBuf, ResourcePurity> PACKET_CODEC = PacketCodecs.indexed(
            ID_TO_VALUE, (value) -> value.index
    );

    ResourcePurity(int index, String name, Formatting formatting) {
        this.index = index;
        this.name = name;
        this.formatting = formatting;
    }

    public int getIndex() {
        return this.index;
    }

    public static ResourcePurity indexed(int index) {
        return switch (index) {
            case 1 -> ResourcePurity.IMPURE;
            case 2 -> ResourcePurity.NORMAL;
            case 3 -> ResourcePurity.PURE;
            default -> ResourcePurity.NONE;
        };
    }

    public static ResourcePurity random(Random random) {
        int i = random.nextInt(3);
        return switch (i) {
            case 0 -> IMPURE;
            case 1 -> NORMAL;
            case 2 -> PURE;
            default -> NONE;
        };
    }

    public static ResourcePurity random() {
        return random(new Random());
    }

    public static ResourcePurity fromString(String string) {
        return switch (string) {
            case "IMPURE" -> IMPURE;
            case "NORMAL" -> NORMAL;
            case "PURE" -> PURE;
            case "RANDOM" -> random();
            default -> NONE;
        };
    }

    public float getMiningTimeMultiplier() {
        return switch (this) {
            case PURE -> 0.5f;
            case NORMAL -> 1.0f;
            case IMPURE -> 1.5f;
            case NONE -> 2.0f;
        };
    }

    public Text toText() {
        return Text.translatable("purity." + asString()).formatted(formatting);
    }

    @Override
    public String asString() {
        return name;
    }
}
