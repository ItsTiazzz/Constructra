package org.tywrapstudios.constructra.api.resource.v1;

import net.minecraft.block.Block;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.tywrapstudios.constructra.Constructra;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    public static class Nodes {
        // TODO: Make the NODES actually save with the world.
        private static final String STORAGE_ID = "constructra_resource_nodes";

        public static ResourceNodesState getOrCreateState(ServerWorld world) {
            return world.getPersistentStateManager().getOrCreate(ResourceNodesState.TYPE, STORAGE_ID);
        }

        public static void addNode(ResourceNode<?> node, World world) {
            if (world instanceof ServerWorld serverWorld) {
                ResourceNodesState state = getOrCreateState(serverWorld);
                node.createOriginBlock(world);
                state.getNodes().add(node);
                state.markDirty();
            }
        }

        public static <T extends Resource> void addNode(T resource, BlockPos pos, World world) {
            ResourceNode<T> node = new ResourceNode<>(resource, pos);
            addNode(node, world);
        }

        public static <T extends Resource> void addNode(T resource, BlockPos pos, boolean obstructed, World world) {
            ResourceNode<T> node = new ResourceNode<>(resource, pos, obstructed);
            addNode(node, world);
        }

        public static ResourceNode<?> getAtPos(BlockPos pos, ServerWorld world) {
            ResourceNodesState state = getOrCreateState(world);
            for (ResourceNode<?> node : state.getNodes()) {
                if (node.getCentre().equals(pos)) {
                    return node;
                }
            }
            return null;
        }

        public static ResourceNode<?> getAtPos(int x, int y, int z, ServerWorld world) {
            return getAtPos(new BlockPos(x, y, z), world);
        }

        public static void flush(ServerWorld world) {
            flush("Unknown reason", world);
        }

        public static void flush(String reason, ServerWorld world) {
            ResourceNodesState state = getOrCreateState(world);
            state.getNodes().clear();
            Constructra.LOGGER.warn("Flushed all resource nodes: " + reason);
        }

        public static void tick(MinecraftServer server) {
            List<ResourceNode<?>> toRemove = new ArrayList<>();
            ResourceNodesState state = getOrCreateState(server.getOverworld());

            for (ResourceNode<?> node : state.getNodes()) {
                BlockPos pos = node.getCentre();
                World world = server.getOverworld();
                verifyNode(node, world, pos, toRemove);

                if (world instanceof ServerWorld serverWorld && Constructra.config().util_config.debug_mode) {
                    serverWorld.spawnParticles(ParticleTypes.SCRAPE, pos.getX(), pos.getY(), pos.getZ(), 10, 0, 0, 0, 0);
                    serverWorld.spawnParticles(ParticleTypes.SCRAPE, pos.getX()+1, pos.getY(), pos.getZ(), 10, 0, 0, 0, 0);
                    serverWorld.spawnParticles(ParticleTypes.SCRAPE, pos.getX()+1, pos.getY(), pos.getZ()+1, 10, 0, 0, 0, 0);
                    serverWorld.spawnParticles(ParticleTypes.SCRAPE, pos.getX(), pos.getY(), pos.getZ()+1, 10, 0, 0, 0, 0);
                    serverWorld.spawnParticles(ParticleTypes.SCRAPE, pos.getX(), pos.getY()+1, pos.getZ(), 10, 0, 0, 0, 0);
                    serverWorld.spawnParticles(ParticleTypes.SCRAPE, pos.getX()+1, pos.getY()+1, pos.getZ(), 10, 0, 0, 0, 0);
                    serverWorld.spawnParticles(ParticleTypes.SCRAPE, pos.getX()+1, pos.getY()+1, pos.getZ()+1, 10, 0, 0, 0, 0);
                    serverWorld.spawnParticles(ParticleTypes.SCRAPE, pos.getX(), pos.getY()+1, pos.getZ()+1, 10, 0, 0, 0, 0);
                }
            }

            state.getNodes().removeAll(toRemove);
        }

        private static void verifyNode(ResourceNode<?> node, World world, BlockPos pos, List<ResourceNode<?>> toRemove) {
            if (world.getRegistryKey() != World.OVERWORLD) {
                toRemove.add(node);
                Constructra.LOGGER.error("Found ResourceNode that isn't in Overworld, marked for removal. This should not happen, please report this.");
                return;
            }
            Block block = world.getBlockState(pos).getBlock();
            if (!block.equals(node.getResource().getHarvestBlock())) {
                toRemove.add(node);
                Constructra.LOGGER.warn("Marked ResourceNode for removal at " + pos);
            }
        }
    }
}
