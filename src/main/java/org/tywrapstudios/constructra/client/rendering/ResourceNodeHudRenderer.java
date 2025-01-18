package org.tywrapstudios.constructra.client.rendering;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.tywrapstudios.constructra.api.resource.v1.ResourceNode;

public class ResourceNodeHudRenderer {
    public void render(DrawContext context, ResourceNode<?> node) {
        MinecraftClient client = MinecraftClient.getInstance();
        int x = context.getScaledWindowWidth() / 2 + 10;
        int y = context.getScaledWindowHeight() / 2;

        Text name = node.getResource().getName();
        Text purity = node.getPurity().toText();

        //context.drawText(client.textRenderer, name, x, y, 0xFFFFFF, true);
        context.drawTextWithBackground(client.textRenderer, name, x, y, client.textRenderer.getWidth(name), 0xFFFFFF);
        //context.drawText(client.textRenderer, purity, x, y + 12, 0xFFFFFF, true);
        context.drawTextWithBackground(client.textRenderer, purity, x, y + 12, client.textRenderer.getWidth(purity), 0xFFFFFF);

        if (node.isObstructed()) {
            Text miningPrompt = Text.translatable("text.constructra.prompt.mining_instruction", client.options.attackKey.getBoundKeyLocalizedText().getString());
            context.drawText(client.textRenderer, miningPrompt, x, y + 24, 0xFFFFFF, true);
        }
    }
}
