package team.galacticraft.galacticraft.common.core.blocks;

import net.minecraft.world.level.block.EntityBlock;
import org.jetbrains.annotations.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BlockAdvancedTile extends BlockAdvanced implements EntityBlock
{
    public BlockAdvancedTile(Properties builder)
    {
        super(builder);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter world)
    {
        return null;
    }

    @Override
    public boolean isEntityBlock()
    {
        return true;
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock())
        {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof Container)
            {
                Containers.dropContents(worldIn, pos, (Container) tileentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }
}
