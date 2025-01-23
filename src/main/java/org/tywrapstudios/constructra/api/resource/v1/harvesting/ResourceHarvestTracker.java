package org.tywrapstudios.constructra.api.resource.v1.harvesting;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.tywrapstudios.constructra.Constructra;
import org.tywrapstudios.constructra.api.resource.v1.ResourceManager;
import org.tywrapstudios.constructra.api.resource.v1.ResourceNode;

import java.util.HashMap;
import java.util.Map;

public class ResourceHarvestTracker {
    private static final Map<PlayerEntity, BlockPos> ACTIVELY_HARVESTING = new HashMap<>();
    private static int TIME_OF_LAST_HARVEST = 0;

    public static void startHarvesting(PlayerEntity player, BlockPos pos) {
        ACTIVELY_HARVESTING.put(player, pos);
    }

    public static void stopHarvesting(PlayerEntity player) {
        ACTIVELY_HARVESTING.remove(player);
    }

    public static void tick(MinecraftServer server) {
        for (Map.Entry<PlayerEntity, BlockPos> entry : ACTIVELY_HARVESTING.entrySet()) {
            PlayerEntity player = entry.getKey();
            BlockPos pos = entry.getValue();

            ResourceNode<?> node = ResourceManager.Nodes.getAtPos(pos, (ServerWorld)player.getWorld());
            if (node != null) {
                if (node.tryHarvest((ServerWorld) player.getWorld())) {
                    int currentTime = server.getTicks();
                    int ticks = currentTime - TIME_OF_LAST_HARVEST;
                    TIME_OF_LAST_HARVEST = currentTime;
                    Constructra.LOGGER.debug("Harvesting took " + ticks + " ticks (" + (float) ticks/20 + " seconds)");
                }
            }
        }
    }
}
