package team.galacticraft.galacticraft.common.core.blocks;

import team.galacticraft.galacticraft.common.core.tile.TileEntityCoalGenerator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import org.jetbrains.annotations.Nullable;
import java.util.Random;

public class BlockCoalGenerator extends BlockMachineBase
{
    public BlockCoalGenerator(Properties builder)
    {
        super(builder);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand)
    {
        BlockEntity tile = worldIn.getBlockEntity(pos);

        if (tile instanceof TileEntityCoalGenerator)
        {
            TileEntityCoalGenerator tileEntity = (TileEntityCoalGenerator) tile;
            if (tileEntity.heatGJperTick > 0)
            {
                float particlePosX = pos.getX() + 0.5F;
                float particlePosY = pos.getY() + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
                float particlePosZ = pos.getZ() + 0.5F;
                float particleSize0 = 0.52F;
                float particleSize1 = rand.nextFloat() * 0.6F - 0.3F;

                switch (stateIn.getValue(FACING))
                {
                case NORTH:
                    worldIn.addParticle(ParticleTypes.SMOKE, particlePosX + particleSize1, particlePosY, particlePosZ - particleSize0, 0.0D, 0.0D, 0.0D);
                    worldIn.addParticle(ParticleTypes.FLAME, particlePosX + particleSize1, particlePosY, particlePosZ - particleSize0, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    worldIn.addParticle(ParticleTypes.SMOKE, particlePosX + particleSize0, particlePosY, particlePosZ + particleSize1, 0.0D, 0.0D, 0.0D);
                    worldIn.addParticle(ParticleTypes.FLAME, particlePosX + particleSize0, particlePosY, particlePosZ + particleSize1, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    worldIn.addParticle(ParticleTypes.SMOKE, particlePosX + particleSize1, particlePosY, particlePosZ + particleSize0, 0.0D, 0.0D, 0.0D);
                    worldIn.addParticle(ParticleTypes.FLAME, particlePosX + particleSize1, particlePosY, particlePosZ + particleSize0, 0.0D, 0.0D, 0.0D);
                    break;
                case WEST:
                    worldIn.addParticle(ParticleTypes.SMOKE, particlePosX - particleSize0, particlePosY, particlePosZ + particleSize1, 0.0D, 0.0D, 0.0D);
                    worldIn.addParticle(ParticleTypes.FLAME, particlePosX - particleSize0, particlePosY, particlePosZ + particleSize1, 0.0D, 0.0D, 0.0D);
                    break;
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter world)
    {
        return new TileEntityCoalGenerator();
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
