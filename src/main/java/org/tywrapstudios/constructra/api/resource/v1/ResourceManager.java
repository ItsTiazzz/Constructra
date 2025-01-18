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
import java.util.concurrent.CopyOnWriteArrayList;

public class ResourceManager {
    public static class Nodes {
        // TODO: Make the NODES actually save with the world.
        protected static List<ResourceNode<?>> NODES = new CopyOnWriteArrayList<>();

        public static void addNode(ResourceNode<?> node, World world) {
            node.createOriginBlock(world);
            NODES.add(node);
        }

        public static <T extends Resource> void addNode(T resource, BlockPos pos, World world) {
            ResourceNode<T> node = new ResourceNode<>(resource, pos, world);
            addNode(node, world);
        }

        public static <T extends Resource> void addNode(T resource, BlockPos pos, boolean obstructed, World world) {
            ResourceNode<T> node = new ResourceNode<>(resource, pos, world, obstructed);
            addNode(node, world);
        }

        public static ResourceNode<?> getAtPos(BlockPos pos) {
            for (ResourceNode<?> node : NODES) {
                if (node.getCentre().equals(pos)) {
                    return node;
                }
            }
            return null;
        }

        public static ResourceNode<?> getAtPos(int x, int y, int z) {
            return getAtPos(new BlockPos(x, y, z));
        }

        public static void flush() {
            NODES.clear();
        }

        public static void tick(MinecraftServer server) {
            List<ResourceNode<?>> toRemove = new ArrayList<>();

            for (ResourceNode<?> node : NODES) {
                World world = node.getWorld();
                BlockPos pos = node.getCentre();
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

            NODES.removeAll(toRemove);
        }

        private static void verifyNode(ResourceNode<?> node, World world, BlockPos pos, List<ResourceNode<?>> toRemove) {
            Block block = world.getBlockState(pos).getBlock();
            if (!block.equals(node.getResource().getOriginBlock())) {
                toRemove.add(node);
                Constructra.LOGGER.debugWarning("Marked ResourceNode for removal at " + pos);
            }
        }
    }
}
