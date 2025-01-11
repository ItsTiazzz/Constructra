package org.tywrapstudios.constructra.registry;

import net.minecraft.component.type.FoodComponent;

public enum Foods {
    NONE(0, 0.0f);

    private final FoodComponent foodComponent;

    Foods(FoodComponent foodComponent) {
        this.foodComponent = foodComponent;
    }

    Foods(int hunger, float saturation) {
        this(simpleBuilder(hunger, saturation).build());
    }

    public FoodComponent get() {
        return foodComponent;
    }

    private static FoodComponent.Builder simpleBuilder(int hunger, float saturationModifier) {
        return new FoodComponent.Builder().nutrition(hunger).saturationModifier(saturationModifier);
    }
}
