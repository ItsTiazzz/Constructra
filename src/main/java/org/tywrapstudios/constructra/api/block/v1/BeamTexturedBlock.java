package org.tywrapstudios.constructra.api.block.v1;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.Direction;

public class BeamTexturedBlock extends Block {
    private static final EnumProperty<Direction> FACING;

    public BeamTexturedBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.appendProperties(builder);
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }
}
