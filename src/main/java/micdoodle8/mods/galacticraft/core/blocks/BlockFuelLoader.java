package micdoodle8.mods.galacticraft.core.blocks;

import micdoodle8.mods.galacticraft.core.inventory.ContainerCargoLoader;
import micdoodle8.mods.galacticraft.core.inventory.ContainerFuelLoader;
import micdoodle8.mods.galacticraft.core.items.IShiftDescription;
import micdoodle8.mods.galacticraft.core.items.ISortable;
import micdoodle8.mods.galacticraft.core.tile.IMachineSides;
import micdoodle8.mods.galacticraft.core.tile.IMachineSidesProperties;
import micdoodle8.mods.galacticraft.core.tile.TileEntityCargoUnloader;
import micdoodle8.mods.galacticraft.core.tile.TileEntityFuelLoader;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategory;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockFuelLoader extends BlockAdvancedTile implements IShiftDescription, ISortable
{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static IMachineSidesProperties MACHINESIDES_RENDERTYPE = IMachineSidesProperties.TWOFACES_HORIZ;
    public static final EnumProperty SIDES = MACHINESIDES_RENDERTYPE.asProperty;

    public BlockFuelLoader(Properties builder)
    {
        super(builder);
    }

//    @Override
//    public ItemGroup getCreativeTabToDisplayOn()
//    {
//        return GalacticraftCore.galacticraftBlocksTab;
//    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TileEntityFuelLoader();
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Override
    public ActionResultType onMachineActivated(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn, Hand hand, ItemStack heldItem, BlockRayTraceResult hit)
    {
        INamedContainerProvider container = new SimpleNamedContainerProvider((w, p, pl) -> new ContainerFuelLoader(w, p, (TileEntityFuelLoader) worldIn.getTileEntity(pos)), new TranslationTextComponent("container.fuel_loader.name"));
        NetworkHooks.openGui((ServerPlayerEntity) playerIn, container);
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        final int angle = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        worldIn.setBlockState(pos, state.with(FACING, Direction.byHorizontalIndex(angle).getOpposite()), 3);
        WorldUtil.markAdjacentPadForUpdate(worldIn, pos);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state)
    {
        super.onPlayerDestroy(worldIn, pos, state);
        if (worldIn instanceof World)
        {
            WorldUtil.markAdjacentPadForUpdate((World) worldIn, pos);
        }
    }

    @Override
    public String getShiftDescription(ItemStack stack)
    {
        return GCCoreUtil.translate(this.getTranslationKey() + ".description");
    }

    @Override
    public boolean showDescription(ItemStack stack)
    {
        return true;
    }

//    @Override
//    public BlockState getStateFromMeta(int meta)
//    {
//        Direction enumfacing = Direction.byHorizontalIndex(meta);
//        return this.getDefaultState().with(FACING, enumfacing);
//    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, SIDES);
    }

//    @Override
//    public BlockState getActualState(BlockState state, IBlockReader worldIn, BlockPos pos)
//    {
//        TileEntity tile = worldIn.getTileEntity(pos);
//
//        return IMachineSides.addPropertyForTile(state, tile, MACHINESIDES_RENDERTYPE, SIDES);
//    }

    @Override
    public EnumSortCategory getCategory()
    {
        return EnumSortCategory.MACHINE;
    }

    @Override
    public ActionResultType onSneakUseWrench(World world, BlockPos pos, PlayerEntity entityPlayer, Hand hand, ItemStack heldItem, BlockRayTraceResult hit)
    {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof IMachineSides)
        {
            ((IMachineSides) tile).nextSideConfiguration(tile);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
