package team.galacticraft.galacticraft.common.core.world.gen;

import team.galacticraft.galacticraft.common.core.GCBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import java.util.Random;

public class WorldGenSpaceStation
{
    public boolean place(ChunkAccess chunk, BlockPos pos)
    {
        BlockState state = GCBlocks.decoBlock1.defaultBlockState();
        chunk.setBlockState(pos.offset(0, 0, 0), state, false);
        chunk.setBlockState(pos.offset(0, 0, 1), state, false);
        chunk.setBlockState(pos.offset(0, 0, 2), state, false);
        chunk.setBlockState(pos.offset(0, 0, 3), state, false);
        chunk.setBlockState(pos.offset(0, 0, 4), state, false);
        chunk.setBlockState(pos.offset(0, 0, 5), state, false);
        chunk.setBlockState(pos.offset(0, 0, 6), state, false);
        chunk.setBlockState(pos.offset(0, 1, 0), state, false);
        chunk.setBlockState(pos.offset(0, 1, 1), state, false);
        chunk.setBlockState(pos.offset(0, 1, 2), state, false);
        chunk.setBlockState(pos.offset(0, 1, 3), state, false);
        chunk.setBlockState(pos.offset(0, 1, 4), state, false);
        chunk.setBlockState(pos.offset(0, 1, 5), state, false);
        chunk.setBlockState(pos.offset(0, 1, 6), state, false);
        chunk.setBlockState(pos.offset(1, 0, 0), state, false);
        chunk.setBlockState(pos.offset(1, 0, 1), state, false);
        chunk.setBlockState(pos.offset(1, 0, 2), state, false);
        chunk.setBlockState(pos.offset(1, 0, 3), state, false);
        chunk.setBlockState(pos.offset(1, 0, 4), state, false);
        chunk.setBlockState(pos.offset(1, 0, 5), state, false);
        chunk.setBlockState(pos.offset(1, 0, 6), state, false);
        chunk.setBlockState(pos.offset(1, 1, 0), state, false);
        chunk.setBlockState(pos.offset(1, 1, 6), state, false);
        chunk.setBlockState(pos.offset(2, 0, 0), state, false);
        chunk.setBlockState(pos.offset(2, 0, 1), state, false);
        chunk.setBlockState(pos.offset(2, 0, 2), state, false);
        chunk.setBlockState(pos.offset(2, 0, 3), state, false);
        chunk.setBlockState(pos.offset(2, 0, 4), state, false);
        chunk.setBlockState(pos.offset(2, 0, 5), state, false);
        chunk.setBlockState(pos.offset(2, 0, 6), state, false);
        chunk.setBlockState(pos.offset(2, 1, 0), state, false);
        chunk.setBlockState(pos.offset(2, 1, 6), state, false);
        chunk.setBlockState(pos.offset(3, 0, 0), state, false);
        chunk.setBlockState(pos.offset(3, 0, 1), state, false);
        chunk.setBlockState(pos.offset(3, 0, 2), state, false);
        chunk.setBlockState(pos.offset(3, 0, 3), state, false);
        chunk.setBlockState(pos.offset(3, 0, 4), state, false);
        chunk.setBlockState(pos.offset(3, 0, 5), state, false);
        chunk.setBlockState(pos.offset(3, 0, 6), state, false);
        chunk.setBlockState(pos.offset(3, 1, 0), state, false);

        chunk.setBlockState(pos.offset(3, 1, 6), state, false);
        chunk.setBlockState(pos.offset(4, 0, 0), state, false);
        chunk.setBlockState(pos.offset(4, 0, 1), state, false);
        chunk.setBlockState(pos.offset(4, 0, 2), state, false);
        chunk.setBlockState(pos.offset(4, 0, 3), state, false);
        chunk.setBlockState(pos.offset(4, 0, 4), state, false);
        chunk.setBlockState(pos.offset(4, 0, 5), state, false);
        chunk.setBlockState(pos.offset(4, 0, 6), state, false);
        chunk.setBlockState(pos.offset(4, 1, 0), state, false);
        chunk.setBlockState(pos.offset(4, 1, 6), state, false);
        chunk.setBlockState(pos.offset(5, 0, 0), state, false);
        chunk.setBlockState(pos.offset(5, 0, 1), state, false);
        chunk.setBlockState(pos.offset(5, 0, 2), state, false);
        chunk.setBlockState(pos.offset(5, 0, 3), state, false);
        chunk.setBlockState(pos.offset(5, 0, 4), state, false);
        chunk.setBlockState(pos.offset(5, 0, 5), state, false);
        chunk.setBlockState(pos.offset(5, 0, 6), state, false);
        chunk.setBlockState(pos.offset(5, 1, 0), state, false);
        chunk.setBlockState(pos.offset(5, 1, 6), state, false);
        chunk.setBlockState(pos.offset(6, 1, 0), state, false);
        chunk.setBlockState(pos.offset(6, 1, 1), state, false);
        chunk.setBlockState(pos.offset(6, 1, 2), state, false);
        chunk.setBlockState(pos.offset(6, 1, 3), state, false);
        chunk.setBlockState(pos.offset(6, 1, 4), state, false);
        chunk.setBlockState(pos.offset(6, 1, 5), state, false);
        chunk.setBlockState(pos.offset(6, 1, 6), state, false);
        chunk.setBlockState(pos.offset(6, 2, 0), state, false);
        chunk.setBlockState(pos.offset(6, 2, 6), state, false);
        chunk.setBlockState(pos.offset(6, 3, 0), state, false);
        chunk.setBlockState(pos.offset(6, 3, 6), state, false);
        chunk.setBlockState(pos.offset(6, 4, 0), state, false);
        chunk.setBlockState(pos.offset(6, 4, 6), state, false);
        chunk.setBlockState(pos.offset(6, 5, 0), state, false);
        chunk.setBlockState(pos.offset(6, 5, 1), state, false);
        chunk.setBlockState(pos.offset(6, 5, 2), state, false);
        chunk.setBlockState(pos.offset(6, 5, 3), state, false);
        chunk.setBlockState(pos.offset(6, 5, 4), state, false);
        chunk.setBlockState(pos.offset(6, 5, 5), state, false);
        chunk.setBlockState(pos.offset(6, 5, 6), state, false);
        chunk.setBlockState(pos.offset(7, 1, 0), state, false);
        chunk.setBlockState(pos.offset(7, 1, 1), state, false);
        chunk.setBlockState(pos.offset(7, 1, 2), state, false);
        chunk.setBlockState(pos.offset(7, 1, 3), state, false);
        chunk.setBlockState(pos.offset(7, 1, 4), state, false);
        chunk.setBlockState(pos.offset(7, 1, 5), state, false);
        chunk.setBlockState(pos.offset(7, 1, 6), state, false);
        chunk.setBlockState(pos.offset(7, 2, 0), state, false);
        chunk.setBlockState(pos.offset(7, 2, 1), state, false);
        chunk.setBlockState(pos.offset(7, 2, 5), state, false);
        chunk.setBlockState(pos.offset(7, 2, 6), state, false);
        chunk.setBlockState(pos.offset(7, 3, 0), state, false);
        chunk.setBlockState(pos.offset(7, 3, 1), state, false);
        chunk.setBlockState(pos.offset(7, 3, 5), state, false);
        chunk.setBlockState(pos.offset(7, 3, 6), state, false);
        chunk.setBlockState(pos.offset(7, 4, 0), state, false);
        chunk.setBlockState(pos.offset(7, 4, 1), state, false);
        chunk.setBlockState(pos.offset(7, 4, 5), state, false);
        chunk.setBlockState(pos.offset(7, 4, 6), state, false);
        chunk.setBlockState(pos.offset(7, 5, 0), state, false);
        chunk.setBlockState(pos.offset(7, 5, 1), state, false);
        chunk.setBlockState(pos.offset(7, 5, 2), state, false);
        chunk.setBlockState(pos.offset(7, 5, 3), state, false);
        chunk.setBlockState(pos.offset(7, 5, 4), state, false);
        chunk.setBlockState(pos.offset(7, 5, 5), state, false);
        chunk.setBlockState(pos.offset(7, 5, 6), state, false);
        chunk.setBlockState(pos.offset(7, 6, 0), state, false);
        chunk.setBlockState(pos.offset(7, 6, 1), state, false);
        chunk.setBlockState(pos.offset(7, 6, 2), state, false);
        chunk.setBlockState(pos.offset(7, 6, 3), state, false);
        chunk.setBlockState(pos.offset(7, 6, 4), state, false);
        chunk.setBlockState(pos.offset(7, 6, 5), state, false);
        chunk.setBlockState(pos.offset(7, 6, 6), state, false);
        chunk.setBlockState(pos.offset(8, 1, 0), state, false);
        chunk.setBlockState(pos.offset(8, 1, 1), state, false);
        chunk.setBlockState(pos.offset(8, 1, 2), state, false);
        chunk.setBlockState(pos.offset(8, 1, 3), state, false);
        chunk.setBlockState(pos.offset(8, 1, 4), state, false);
        chunk.setBlockState(pos.offset(8, 1, 5), state, false);
        chunk.setBlockState(pos.offset(8, 1, 6), state, false);
        chunk.setBlockState(pos.offset(8, 2, 0), state, false);
        chunk.setBlockState(pos.offset(8, 2, 6), state, false);
        chunk.setBlockState(pos.offset(8, 4, 0), state, false);
        chunk.setBlockState(pos.offset(8, 4, 6), state, false);
        chunk.setBlockState(pos.offset(8, 5, 0), state, false);
        chunk.setBlockState(pos.offset(8, 5, 6), state, false);
        chunk.setBlockState(pos.offset(8, 6, 0), state, false);
        chunk.setBlockState(pos.offset(8, 6, 1), state, false);
        chunk.setBlockState(pos.offset(8, 6, 2), state, false);
        chunk.setBlockState(pos.offset(8, 6, 3), state, false);
        chunk.setBlockState(pos.offset(8, 6, 4), state, false);
        chunk.setBlockState(pos.offset(8, 6, 5), state, false);
        chunk.setBlockState(pos.offset(8, 6, 6), state, false);
        chunk.setBlockState(pos.offset(9, 1, 0), state, false);
        chunk.setBlockState(pos.offset(9, 1, 1), state, false);
        chunk.setBlockState(pos.offset(9, 1, 2), state, false);
        chunk.setBlockState(pos.offset(9, 1, 3), state, false);
        chunk.setBlockState(pos.offset(9, 1, 4), state, false);
        chunk.setBlockState(pos.offset(9, 1, 5), state, false);
        chunk.setBlockState(pos.offset(9, 1, 6), state, false);
        chunk.setBlockState(pos.offset(9, 5, 0), state, false);
        chunk.setBlockState(pos.offset(9, 5, 6), state, false);
        chunk.setBlockState(pos.offset(9, 6, 0), state, false);
        chunk.setBlockState(pos.offset(9, 6, 1), state, false);
        chunk.setBlockState(pos.offset(9, 6, 2), state, false);
        chunk.setBlockState(pos.offset(9, 6, 3), state, false);
        chunk.setBlockState(pos.offset(9, 6, 4), state, false);
        chunk.setBlockState(pos.offset(9, 6, 5), state, false);
        chunk.setBlockState(pos.offset(9, 6, 6), state, false);
        chunk.setBlockState(pos.offset(10, 1, 0), state, false);
        chunk.setBlockState(pos.offset(10, 1, 1), state, false);
        chunk.setBlockState(pos.offset(10, 1, 2), state, false);
        chunk.setBlockState(pos.offset(10, 1, 3), state, false);
        chunk.setBlockState(pos.offset(10, 1, 4), state, false);
        chunk.setBlockState(pos.offset(10, 1, 5), state, false);
        chunk.setBlockState(pos.offset(10, 1, 6), state, false);

        // world.setBlockState(pos.add(10, 2, 3, 3, 0), 3);

        chunk.setBlockState(pos.offset(10, 5, 0), state, false);
        chunk.setBlockState(pos.offset(10, 5, 6), state, false);
        chunk.setBlockState(pos.offset(10, 6, 0), state, false);
        chunk.setBlockState(pos.offset(10, 6, 1), state, false);
        chunk.setBlockState(pos.offset(10, 6, 2), state, false);
        chunk.setBlockState(pos.offset(10, 6, 3), state, false);
        chunk.setBlockState(pos.offset(10, 6, 4), state, false);
        chunk.setBlockState(pos.offset(10, 6, 5), state, false);
        chunk.setBlockState(pos.offset(10, 6, 6), state, false);
        chunk.setBlockState(pos.offset(11, 1, 0), state, false);
        chunk.setBlockState(pos.offset(11, 1, 1), state, false);
        chunk.setBlockState(pos.offset(11, 1, 2), state, false);
        chunk.setBlockState(pos.offset(11, 1, 3), state, false);
        chunk.setBlockState(pos.offset(11, 1, 4), state, false);
        chunk.setBlockState(pos.offset(11, 1, 5), state, false);
        chunk.setBlockState(pos.offset(11, 1, 6), state, false);
        chunk.setBlockState(pos.offset(11, 5, 0), state, false);
        chunk.setBlockState(pos.offset(11, 5, 6), state, false);
        chunk.setBlockState(pos.offset(11, 6, 0), state, false);
        chunk.setBlockState(pos.offset(11, 6, 1), state, false);
        chunk.setBlockState(pos.offset(11, 6, 2), state, false);
        chunk.setBlockState(pos.offset(11, 6, 3), state, false);
        chunk.setBlockState(pos.offset(11, 6, 4), state, false);
        chunk.setBlockState(pos.offset(11, 6, 5), state, false);
        chunk.setBlockState(pos.offset(11, 6, 6), state, false);
        chunk.setBlockState(pos.offset(12, 1, 0), state, false);
        chunk.setBlockState(pos.offset(12, 1, 1), state, false);
        chunk.setBlockState(pos.offset(12, 1, 2), state, false);
        chunk.setBlockState(pos.offset(12, 1, 3), state, false);
        chunk.setBlockState(pos.offset(12, 1, 4), state, false);
        chunk.setBlockState(pos.offset(12, 1, 5), state, false);
        chunk.setBlockState(pos.offset(12, 1, 6), state, false);
        chunk.setBlockState(pos.offset(12, 2, 0), state, false);
        chunk.setBlockState(pos.offset(12, 2, 6), state, false);
        chunk.setBlockState(pos.offset(12, 4, 0), state, false);
        chunk.setBlockState(pos.offset(12, 4, 6), state, false);
        chunk.setBlockState(pos.offset(12, 5, 0), state, false);
        chunk.setBlockState(pos.offset(12, 5, 6), state, false);
        chunk.setBlockState(pos.offset(12, 6, 0), state, false);
        chunk.setBlockState(pos.offset(12, 6, 1), state, false);
        chunk.setBlockState(pos.offset(12, 6, 2), state, false);
        chunk.setBlockState(pos.offset(12, 6, 3), state, false);
        chunk.setBlockState(pos.offset(12, 6, 4), state, false);
        chunk.setBlockState(pos.offset(12, 6, 5), state, false);
        chunk.setBlockState(pos.offset(12, 6, 6), state, false);
        chunk.setBlockState(pos.offset(13, 1, 0), state, false);
        chunk.setBlockState(pos.offset(13, 1, 1), state, false);
        chunk.setBlockState(pos.offset(13, 1, 2), state, false);
        chunk.setBlockState(pos.offset(13, 1, 3), state, false);
        chunk.setBlockState(pos.offset(13, 1, 4), state, false);
        chunk.setBlockState(pos.offset(13, 1, 5), state, false);
        chunk.setBlockState(pos.offset(13, 1, 6), state, false);
        chunk.setBlockState(pos.offset(13, 2, 0), state, false);
        chunk.setBlockState(pos.offset(13, 2, 1), state, false);
        chunk.setBlockState(pos.offset(13, 2, 5), state, false);
        chunk.setBlockState(pos.offset(13, 2, 6), state, false);
        chunk.setBlockState(pos.offset(13, 3, 0), state, false);
        chunk.setBlockState(pos.offset(13, 3, 6), state, false);
        chunk.setBlockState(pos.offset(13, 4, 0), state, false);
        chunk.setBlockState(pos.offset(13, 4, 1), state, false);
        chunk.setBlockState(pos.offset(13, 4, 5), state, false);
        chunk.setBlockState(pos.offset(13, 4, 6), state, false);
        chunk.setBlockState(pos.offset(13, 5, 0), state, false);
        chunk.setBlockState(pos.offset(13, 5, 1), state, false);
        chunk.setBlockState(pos.offset(13, 5, 2), state, false);
        chunk.setBlockState(pos.offset(13, 5, 3), state, false);
        chunk.setBlockState(pos.offset(13, 5, 4), state, false);
        chunk.setBlockState(pos.offset(13, 5, 5), state, false);
        chunk.setBlockState(pos.offset(13, 5, 6), state, false);
        chunk.setBlockState(pos.offset(13, 6, 0), state, false);
        chunk.setBlockState(pos.offset(13, 6, 1), state, false);
        chunk.setBlockState(pos.offset(13, 6, 2), state, false);
        chunk.setBlockState(pos.offset(13, 6, 3), state, false);
        chunk.setBlockState(pos.offset(13, 6, 4), state, false);
        chunk.setBlockState(pos.offset(13, 6, 5), state, false);
        chunk.setBlockState(pos.offset(13, 6, 6), state, false);

        chunk.setBlockState(pos.offset(8, 3, 0), Blocks.GLASS_PANE.defaultBlockState().setValue(CrossCollisionBlock.WEST, true), false);
        chunk.setBlockState(pos.offset(8, 3, 6), Blocks.GLASS_PANE.defaultBlockState().setValue(CrossCollisionBlock.WEST, true), false);
        chunk.setBlockState(pos.offset(12, 3, 0), Blocks.GLASS_PANE.defaultBlockState().setValue(CrossCollisionBlock.EAST, true), false);
        chunk.setBlockState(pos.offset(12, 3, 6), Blocks.GLASS_PANE.defaultBlockState().setValue(CrossCollisionBlock.EAST, true), false);
        chunk.setBlockState(pos.offset(13, 3, 1), Blocks.GLASS_PANE.defaultBlockState().setValue(CrossCollisionBlock.NORTH, true), false);
        chunk.setBlockState(pos.offset(13, 3, 5), Blocks.GLASS_PANE.defaultBlockState().setValue(CrossCollisionBlock.SOUTH, true), false);

        return true;
    }
}
