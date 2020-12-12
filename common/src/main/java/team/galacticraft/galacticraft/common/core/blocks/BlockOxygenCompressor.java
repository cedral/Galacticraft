package team.galacticraft.galacticraft.common.core.blocks;

import net.minecraft.client.resources.language.I18n;
import team.galacticraft.galacticraft.common.core.GCBlocks;
import team.galacticraft.galacticraft.common.core.inventory.ContainerOxygenCollector;
import team.galacticraft.galacticraft.common.core.inventory.ContainerOxygenCompressor;
import team.galacticraft.galacticraft.common.core.items.IShiftDescription;
import team.galacticraft.galacticraft.common.core.items.ISortable;
import team.galacticraft.galacticraft.common.core.tile.TileEntityOxygenCollector;
import team.galacticraft.galacticraft.common.core.tile.TileEntityOxygenCompressor;
import team.galacticraft.galacticraft.common.core.tile.TileEntityOxygenDecompressor;
import team.galacticraft.galacticraft.common.core.util.EnumSortCategory;
import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import team.galacticraft.galacticraft.common.compat.PlatformSpecific;

import org.jetbrains.annotations.Nullable;

public class BlockOxygenCompressor extends BlockAdvancedTile implements IShiftDescription, ISortable
{
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
//    public static final EnumProperty<EnumCompressorType> TYPE = EnumProperty.create("type", EnumCompressorType.class);

//    public enum EnumCompressorType implements IStringSerializable
//    {
//        COMPRESSOR(0, "compressor"),
//        DECOMPRESSOR(1, "decompressor");
//
//        private final int meta;
//        private final String name;
//
//        EnumCompressorType(int meta, String name)
//        {
//            this.meta = meta;
//            this.name = name;
//        }
//
//        public int getMeta()
//        {
//            return this.meta;
//        }
//
//        private final static EnumCompressorType[] values = values();
//        public static EnumCompressorType byMetadata(int meta)
//        {
//            return values[meta % values.length];
//        }
//
//        @Override
//        public String getName()
//        {
//            return this.name;
//        }
//    }

    public BlockOxygenCompressor(Properties builder)
    {
        super(builder);
    }

//    @Override
//    public ItemGroup getCreativeTabToDisplayOn()
//    {
//        return GalacticraftCore.galacticraftBlocksTab;
//    }

    @Override
    public InteractionResult onMachineActivated(Level worldIn, BlockPos pos, BlockState state, Player playerIn, InteractionHand hand, ItemStack heldItem, BlockHitResult hit)
    {
        if (!worldIn.isClientSide)
        {
            PlatformSpecific.openContainer((ServerPlayer) playerIn, getMenuProvider(state, worldIn, pos), buf -> buf.writeBlockPos(pos));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos)
    {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        return tileentity instanceof MenuProvider ? (MenuProvider)tileentity : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter world)
    {
        return this == GCBlocks.oxygenCompressor ? new TileEntityOxygenCompressor() : new TileEntityOxygenDecompressor();
//        int metadata = getMetaFromState(state);
//        if (metadata >= BlockOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA)
//        {
//            return new TileEntityOxygenDecompressor();
//        }
//        else if (metadata >= BlockOxygenCompressor.OXYGEN_COMPRESSOR_METADATA)
//        {
//            return new TileEntityOxygenCompressor();
//        }
//        else
//        {
//            return null;
//        }
    }

    @Override
    public boolean isEntityBlock()
    {
        return true;
    }

//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
//    {
//        final int angle = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
//        int change = Direction.byHorizontalIndex(angle).getOpposite().getHorizontalIndex();
//
//        if (stack.getDamage() >= BlockOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA)
//        {
//            change += BlockOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA;
//        }
//        else if (stack.getDamage() >= BlockOxygenCompressor.OXYGEN_COMPRESSOR_METADATA)
//        {
//            change += BlockOxygenCompressor.OXYGEN_COMPRESSOR_METADATA;
//        }
//
//        worldIn.setBlockState(pos, getStateFromMeta(change), 3);
//    }

//    @Override
//    public void getSubBlocks(ItemGroup tab, NonNullList<ItemStack> list)
//    {
//        list.add(new ItemStack(this, 1, BlockOxygenCompressor.OXYGEN_COMPRESSOR_METADATA));
//        list.add(new ItemStack(this, 1, BlockOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA));
//    }
//
//    @Override
//    public int damageDropped(BlockState state)
//    {
//        int metadata = getMetaFromState(state);
//        if (metadata >= BlockOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA)
//        {
//            return BlockOxygenCompressor.OXYGEN_DECOMPRESSOR_METADATA;
//        }
//        else if (metadata >= BlockOxygenCompressor.OXYGEN_COMPRESSOR_METADATA)
//        {
//            return BlockOxygenCompressor.OXYGEN_COMPRESSOR_METADATA;
//        }
//        else
//        {
//            return 0;
//        }
//    }

    @Override
    public String getShiftDescription(ItemStack stack)
    {
        return I18n.get(this.getDescriptionId() + ".description");
    }

    @Override
    public boolean showDescription(ItemStack stack)
    {
        return true;
    }

//    @Override
//    public BlockState getStateFromMeta(int meta)
//    {
//        Direction enumfacing = Direction.byHorizontalIndex(meta % 4);
//        EnumCompressorType type = EnumCompressorType.byMetadata((int) Math.floor(meta / 4.0));
//        return this.getDefaultState().with(FACING, enumfacing).with(TYPE, type);
//    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Override
    public EnumSortCategory getCategory()
    {
        return EnumSortCategory.MACHINE;
    }
}