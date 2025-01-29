package org.tywrapstudios.constructra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.tywrapstudios.constructra.client.logic.ClientActionTracker;
import org.tywrapstudios.constructra.client.rendering.ResourceNodeHudRenderer;

@Environment(EnvType.CLIENT)
public class ConstructraClient implements ClientModInitializer {
    private static final ResourceNodeHudRenderer HUD_RENDERER = new ResourceNodeHudRenderer();

    @Override
    public void onInitializeClient() {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientActionTracker.initialize(client);

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (ClientActionTracker.CURRENT_NODE == null) return;
            if (ClientActionTracker.WATCHING_NODE) HUD_RENDERER.render(drawContext, ClientActionTracker.CURRENT_NODE);
        });
    }
}
