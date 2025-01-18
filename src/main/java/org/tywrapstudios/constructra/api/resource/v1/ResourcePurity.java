package org.tywrapstudios.constructra.api.resource.v1;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringIdentifiable;

import java.util.Random;

public enum ResourcePurity implements StringIdentifiable {
    NONE("none", Formatting.RED),
    IMPURE("impure", Formatting.DARK_AQUA),
    NORMAL("normal", Formatting.GOLD),
    PURE("pure", Formatting.LIGHT_PURPLE),;

    private final String name;
    private final Formatting formatting;

    ResourcePurity(String name, Formatting formatting) {
        this.name = name;
        this.formatting = formatting;
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

    public Text toText() {
        return Text.translatable("purity." + asString()).formatted(formatting);
    }

    @Override
    public String asString() {
        return name;
    }
}
