package team.galacticraft.galacticraft.common.core.blocks;

import team.galacticraft.galacticraft.common.core.tile.TileEntityIngotCompressor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class BlockIngotCompressor extends BlockMachineBase
{
    public BlockIngotCompressor(Properties builder)
    {
        super(builder);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter world)
    {
        return new TileEntityIngotCompressor();
    }

    @Override
    public boolean isEntityBlock()
    {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }
}
