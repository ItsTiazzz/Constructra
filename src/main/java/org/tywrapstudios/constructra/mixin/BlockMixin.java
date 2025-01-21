package org.tywrapstudios.constructra.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.tywrapstudios.constructra.api.resource.v1.ResourceManager;

@Debug(export = true)
@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock {
    public BlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (world instanceof ServerWorld serverWorld) {
            if (ResourceManager.Nodes.isInNode(pos, serverWorld)) { 
                world.setBlockState(pos, state);
            };
        }
        super.onStateReplaced(state, world, pos, newState, moved)
    }
}
