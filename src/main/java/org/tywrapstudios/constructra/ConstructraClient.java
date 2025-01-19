package org.tywrapstudios.constructra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.tywrapstudios.constructra.api.resource.v1.ResourceNode;
import org.tywrapstudios.constructra.client.rendering.ResourceNodeHudRenderer;
import org.tywrapstudios.constructra.network.payload.NodeQueryC2SPayload;
import org.tywrapstudios.constructra.network.payload.NodeQueryS2CPayload;

public class ConstructraClient implements ClientModInitializer {
    private static final ResourceNodeHudRenderer HUD_RENDERER = new ResourceNodeHudRenderer();
    private static ResourceNode<?> currentlyViewedNode = null;
    private static ResourceNode<?> lastViewedNode = ResourceNode.empty();

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player == null) return;

            HitResult hit = MinecraftClient.getInstance().crosshairTarget;
            if (hit != null && hit.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = ((BlockHitResult)hit).getBlockPos();

                if (currentlyViewedNode != lastViewedNode || lastViewedNode == null) {
                    ClientPlayNetworking.send(new NodeQueryC2SPayload(pos));
                } else if (!player.getWorld().getBlockState(pos).getBlock().equals(lastViewedNode.getResource().getHarvestBlock())) {
                    currentlyViewedNode = null;
                }
                if (currentlyViewedNode != null) {
                    lastViewedNode = currentlyViewedNode;
                    HUD_RENDERER.render(drawContext, currentlyViewedNode);
                }
            } else {
                currentlyViewedNode = null;
            }
        });

        //PayloadTypeRegistry.playC2S().register(NodeQueryC2SPayload.ID, NodeQueryC2SPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(NodeQueryS2CPayload.ID, (load, ctx) -> currentlyViewedNode = load.node());
    }
}
