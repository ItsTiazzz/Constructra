package org.tywrapstudios.constructra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.tywrapstudios.constructra.api.resource.v1.ResourceManager;
import org.tywrapstudios.constructra.api.resource.v1.ResourceNode;
import org.tywrapstudios.constructra.client.rendering.ResourceNodeHudRenderer;

public class ConstructraClient implements ClientModInitializer {
    private static final ResourceNodeHudRenderer HUD_RENDERER = new ResourceNodeHudRenderer();

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player == null) return;

            HitResult hit = player.raycast(20.0D, tickDelta.getTickDelta(false), false);
            if (hit.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = ((BlockHitResult)hit).getBlockPos();

                ResourceNode<?> node = ResourceManager.Nodes.getAtPos(pos);
                if (node != null) {
                    HUD_RENDERER.render(drawContext, node);
                }
            }
        });
    }
}
