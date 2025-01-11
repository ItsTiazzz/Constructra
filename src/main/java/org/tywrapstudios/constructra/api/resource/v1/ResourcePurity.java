package org.tywrapstudios.constructra.api.resource.v1;

import java.util.Random;

public enum ResourcePurity {
    NONE,
    IMPURE,
    NORMAL,
    PURE;

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
}
