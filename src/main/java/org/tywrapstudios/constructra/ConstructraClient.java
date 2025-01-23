package org.tywrapstudios.constructra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.tywrapstudios.constructra.api.resource.v1.ResourceNode;
import org.tywrapstudios.constructra.client.rendering.ResourceNodeHudRenderer;
import org.tywrapstudios.constructra.network.payload.HarvestEndEventC2SPayload;
import org.tywrapstudios.constructra.network.payload.HarvestStartEventC2SPayload;
import org.tywrapstudios.constructra.network.payload.NodeQueryC2SPayload;
import org.tywrapstudios.constructra.network.payload.NodeQueryS2CPayload;

public class ConstructraClient implements ClientModInitializer {
    private static final ResourceNodeHudRenderer HUD_RENDERER = new ResourceNodeHudRenderer();
    @Nullable private static ResourceNode<?> CURRENT_NODE = null;
    @NotNull private static ResourceNode<?> LAST_NODE = ResourceNode.createDefaulted();
    private static boolean HARVESTING_NODE = false;

    @Override
    public void onInitializeClient() {
        MinecraftClient client = MinecraftClient.getInstance();

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            ClientPlayerEntity player = client.player;
            if (player == null) return;

            HitResult hit = client.crosshairTarget;
            if (hit != null && hit.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = ((BlockHitResult)hit).getBlockPos();

                if (CURRENT_NODE != LAST_NODE) {
                    ClientPlayNetworking.send(new NodeQueryC2SPayload(pos));
                } else if (!player.getWorld().getBlockState(pos).getBlock().equals(LAST_NODE.getResource().getHarvestBlock())) {
                    CURRENT_NODE = null;
                }
                if (CURRENT_NODE != null) {
                    LAST_NODE = CURRENT_NODE;
                    HUD_RENDERER.render(drawContext, CURRENT_NODE);
                }
            } else {
                CURRENT_NODE = null;
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(NodeQueryS2CPayload.ID, (load, ctx) -> CURRENT_NODE = load.node());

        ClientTickEvents.END_WORLD_TICK.register(world -> {
            if (client.options.attackKey.isPressed() && CURRENT_NODE != null) {
                if (HARVESTING_NODE) return;
                HARVESTING_NODE = true;

                ClientPlayNetworking.send(new HarvestStartEventC2SPayload(CURRENT_NODE.getCentre()));
                Constructra.LOGGER.debug("Harvest Start Event: " + HARVESTING_NODE);
            } else {
                if (HARVESTING_NODE) {
                    HARVESTING_NODE = false;
                    ClientPlayNetworking.send(new HarvestEndEventC2SPayload(1));
                    Constructra.LOGGER.debug("Harvest End Event: " + HARVESTING_NODE);
                }
            }
        });
    }
}
