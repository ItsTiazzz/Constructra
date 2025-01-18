package org.tywrapstudios.constructra.client.rendering;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.tywrapstudios.constructra.api.resource.v1.ResourceNode;

import java.util.ArrayList;
import java.util.List;

public class ResourceNodeHudRenderer {
    public void render(DrawContext context, ResourceNode<?> node) {
        MinecraftClient client = MinecraftClient.getInstance();
        int x = context.getScaledWindowWidth() / 2 + 10;
        int y = context.getScaledWindowHeight() / 2;

        Text name = node.getResource().getName();
        Text purity = node.getPurity().toText();

        List<Text> texts = new ArrayList<>();
        texts.add(name);
        texts.add(purity);
        context.drawTooltip(client.textRenderer, texts, x, y);

        if (node.isObstructed()) {
            Text miningPrompt = Text.translatable("text.constructra.prompt.mining_instruction", client.options.attackKey.getBoundKeyLocalizedText().getString())
                    .formatted(Formatting.GOLD);
            context.drawText(client.textRenderer, miningPrompt, x, y + 20, 0xFFFFFF, true);
        }
    }
}
