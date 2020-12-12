package team.galacticraft.galacticraft.common.core.blocks;

import net.minecraft.client.resources.language.I18n;
import team.galacticraft.galacticraft.common.api.transmission.NetworkType;
import team.galacticraft.galacticraft.common.api.transmission.tile.ITransmitter;
import team.galacticraft.galacticraft.common.api.vector.BlockVec3;
import team.galacticraft.galacticraft.common.core.GCBlocks;
import team.galacticraft.galacticraft.common.core.energy.EnergyUtil;
import team.galacticraft.galacticraft.common.core.items.IShiftDescription;
import team.galacticraft.galacticraft.common.core.items.ISortable;
import team.galacticraft.galacticraft.common.core.tile.TileEntityAluminumWire;
import team.galacticraft.galacticraft.common.core.tile.TileEntityAluminumWireSwitch;
import team.galacticraft.galacticraft.common.core.util.EnumSortCategory;
import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
import team.galacticraft.galacticraft.common.core.util.OxygenUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockAluminumWire extends BlockTransmitter implements IShiftDescription, ISortable
{
    public static final BooleanProperty MIDDLE = BooleanProperty.create("middle");
//    public static final EnumProperty<EnumWireType> WIRE_TYPE = EnumProperty.create("wiretype", EnumWireType.class);
    private static final float MIN = 0.38F;
    private static final float MINH = 0.3F;
    private static final float MAX = 0.62F;
    private static final float MAXH = 0.7F;
    protected static final VoxelShape[] BOUNDING_BOXES = new VoxelShape[]{

            Shapes.box(MIN, MIN, MIN, MAX, MAX, MAX),  // No connection                                  0000000
            Shapes.box(MIN, MIN, MIN, MAX, MAX, 1.0D), // South                                          0000001
            Shapes.box(0.0D, MIN, MIN, MAX, MAX, MAX), // West                                           0000010
            Shapes.box(0.0D, MIN, MIN, MAX, MAX, 1.0D), // South West                                    0000011
            Shapes.box(MIN, MIN, 0.0D, MAX, MAX, MAX), // North                                          0000100
            Shapes.box(MIN, MIN, 0.0D, MAX, MAX, 1.0D), // North South                                   0000101
            Shapes.box(0.0D, MIN, 0.0D, MAX, MAX, MAX), // North West                                    0000110
            Shapes.box(0.0D, MIN, 0.0D, MAX, MAX, 1.0D), // North South West                             0000111
            Shapes.box(MIN, MIN, MIN, 1.0D, MAX, MAX), // East                                           0001000
            Shapes.box(MIN, MIN, MIN, 1.0D, MAX, 1.0D), // East South                                    0001001
            Shapes.box(0.0D, MIN, MIN, 1.0D, MAX, MAX), // West East                                     0001010
            Shapes.box(0.0D, MIN, MIN, 1.0D, MAX, 1.0D), // South West East                              0001011
            Shapes.box(MIN, MIN, 0.0D, 1.0D, MAX, MAX), // North East                                    0001100
            Shapes.box(MIN, MIN, 0.0D, 1.0D, MAX, 1.0D), // North South East                             0001101
            Shapes.box(0.0D, MIN, 0.0D, 1.0D, MAX, MAX), // North East West                              0001110
            Shapes.box(0.0D, MIN, 0.0D, 1.0D, MAX, 1.0D), // North South East West                       0001111

            Shapes.box(MIN, 0.0D, MIN, MAX, MAX, MAX),  // Down                                          0010000
            Shapes.box(MIN, 0.0D, MIN, MAX, MAX, 1.0D), // Down South                                    0010001
            Shapes.box(0.0D, 0.0D, MIN, MAX, MAX, MAX), // Down West                                     0010010
            Shapes.box(0.0D, 0.0D, MIN, MAX, MAX, 1.0D), // Down South West                              0010011
            Shapes.box(MIN, 0.0D, 0.0D, MAX, MAX, MAX), // Down North                                    0010100
            Shapes.box(MIN, 0.0D, 0.0D, MAX, MAX, 1.0D), // Down North South                             0010101
            Shapes.box(0.0D, 0.0D, 0.0D, MAX, MAX, MAX), // Down North West                              0010110
            Shapes.box(0.0D, 0.0D, 0.0D, MAX, MAX, 1.0D), // Down North South West                       0010111
            Shapes.box(MIN, 0.0D, MIN, 1.0D, MAX, MAX), // Down East                                     0011000
            Shapes.box(MIN, 0.0D, MIN, 1.0D, MAX, 1.0D), // Down East South                              0011001
            Shapes.box(0.0D, 0.0D, MIN, 1.0D, MAX, MAX), // Down West East                               0011010
            Shapes.box(0.0D, 0.0D, MIN, 1.0D, MAX, 1.0D), // Down South West East                        0011011
            Shapes.box(MIN, 0.0D, 0.0D, 1.0D, MAX, MAX), // Down North East                              0011100
            Shapes.box(MIN, 0.0D, 0.0D, 1.0D, MAX, 1.0D), // Down North South East                       0011101
            Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, MAX, MAX), // Down North East West                        0011110
            Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, MAX, 1.0D), // Down North South East West                 0011111

            Shapes.box(MIN, MIN, MIN, MAX, 1.0D, MAX),  // Up                                            0100000
            Shapes.box(MIN, MIN, MIN, MAX, 1.0D, 1.0D), // Up South                                      0100001
            Shapes.box(0.0D, MIN, MIN, MAX, 1.0D, MAX), // Up West                                       0100010
            Shapes.box(0.0D, MIN, MIN, MAX, 1.0D, 1.0D), // Up South West                                0100011
            Shapes.box(MIN, MIN, 0.0D, MAX, 1.0D, MAX), // Up North                                      0100100
            Shapes.box(MIN, MIN, 0.0D, MAX, 1.0D, 1.0D), // Up North South                               0100101
            Shapes.box(0.0D, MIN, 0.0D, MAX, 1.0D, MAX), // Up North West                                0100110
            Shapes.box(0.0D, MIN, 0.0D, MAX, 1.0D, 1.0D), // Up North South West                         0100111
            Shapes.box(MIN, MIN, MIN, 1.0D, 1.0D, MAX), // Up East                                       0101000
            Shapes.box(MIN, MIN, MIN, 1.0D, 1.0D, 1.0D), // Up East South                                0101001
            Shapes.box(0.0D, MIN, MIN, 1.0D, 1.0D, MAX), // Up West East                                 0101010
            Shapes.box(0.0D, MIN, MIN, 1.0D, 1.0D, 1.0D), // Up South West East                          0101011
            Shapes.box(MIN, MIN, 0.0D, 1.0D, 1.0D, MAX), // Up North East                                0101100
            Shapes.box(MIN, MIN, 0.0D, 1.0D, 1.0D, 1.0D), // Up North South East                         0101101
            Shapes.box(0.0D, MIN, 0.0D, 1.0D, 1.0D, MAX), // Up North East West                          0101110
            Shapes.box(0.0D, MIN, 0.0D, 1.0D, 1.0D, 1.0D), // Up North South East West                   0101111

            Shapes.box(MIN, 0.0D, MIN, MAX, 1.0D, MAX),  // Up Down                                      0110000
            Shapes.box(MIN, 0.0D, MIN, MAX, 1.0D, 1.0D), // Up Down South                                0110001
            Shapes.box(0.0D, 0.0D, MIN, MAX, 1.0D, MAX), // Up Down West                                 0110010
            Shapes.box(0.0D, 0.0D, MIN, MAX, 1.0D, 1.0D), // Up Down South West                          0110011
            Shapes.box(MIN, 0.0D, 0.0D, MAX, 1.0D, MAX), // Up Down North                                0110100
            Shapes.box(MIN, 0.0D, 0.0D, MAX, 1.0D, 1.0D), // Up Down North South                         0110101
            Shapes.box(0.0D, 0.0D, 0.0D, MAX, 1.0D, MAX), // Up Down North West                          0110110
            Shapes.box(0.0D, 0.0D, 0.0D, MAX, 1.0D, 1.0D), // Up Down North South West                   0110111
            Shapes.box(MIN, 0.0D, MIN, 1.0D, 1.0D, MAX), // Up Down East                                 0111000
            Shapes.box(MIN, 0.0D, MIN, 1.0D, 1.0D, 1.0D), // Up Down East South                          0111001
            Shapes.box(0.0D, 0.0D, MIN, 1.0D, 1.0D, MAX), // Up Down West East                           0111010
            Shapes.box(0.0D, 0.0D, MIN, 1.0D, 1.0D, 1.0D), // Up Down South West East                    0111011
            Shapes.box(MIN, 0.0D, 0.0D, 1.0D, 1.0D, MAX), // Up Down North East                          0111100
            Shapes.box(MIN, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), // Up Down North South East                   0111101
            Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, MAX), // Up Down North East West                    0111110
            Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), // Up Down North South East West             0111111

            Shapes.box(MINH, MINH, MINH, MAXH, MAXH, MAXH),  // No connection                            1000000
            Shapes.box(MINH, MINH, MINH, MAXH, MAXH, 1.0D), // South                                     1000001
            Shapes.box(0.0D, MINH, MINH, MAXH, MAXH, MAXH), // West                                      1000010
            Shapes.box(0.0D, MINH, MINH, MAXH, MAXH, 1.0D), // South West                                1000011
            Shapes.box(MINH, MINH, 0.0D, MAXH, MAXH, MAXH), // North                                     1000100
            Shapes.box(MINH, MINH, 0.0D, MAXH, MAXH, 1.0D), // North South                               1000101
            Shapes.box(0.0D, MINH, 0.0D, MAXH, MAXH, MAXH), // North West                                1000110
            Shapes.box(0.0D, MINH, 0.0D, MAXH, MAXH, 1.0D), // North South West                          1000111
            Shapes.box(MINH, MINH, MINH, 1.0D, MAXH, MAXH), // East                                      1001000
            Shapes.box(MINH, MINH, MINH, 1.0D, MAXH, 1.0D), // East South                                1001001
            Shapes.box(0.0D, MINH, MINH, 1.0D, MAXH, MAXH), // West East                                 1001010
            Shapes.box(0.0D, MINH, MINH, 1.0D, MAXH, 1.0D), // South West East                           1001011
            Shapes.box(MINH, MINH, 0.0D, 1.0D, MAXH, MAXH), // North East                                1001100
            Shapes.box(MINH, MINH, 0.0D, 1.0D, MAXH, 1.0D), // North South East                          1001101
            Shapes.box(0.0D, MINH, 0.0D, 1.0D, MAXH, MAXH), // North East West                           1001110
            Shapes.box(0.0D, MINH, 0.0D, 1.0D, MAXH, 1.0D), // North South East West                     1001111

            Shapes.box(MINH, 0.0D, MINH, MAXH, MAXH, MAXH),  // Down                                     1010000
            Shapes.box(MINH, 0.0D, MINH, MAXH, MAXH, 1.0D), // Down South                                1010001
            Shapes.box(0.0D, 0.0D, MINH, MAXH, MAXH, MAXH), // Down West                                 1010010
            Shapes.box(0.0D, 0.0D, MINH, MAXH, MAXH, 1.0D), // Down South West                           1010011
            Shapes.box(MINH, 0.0D, 0.0D, MAXH, MAXH, MAXH), // Down North                                1010100
            Shapes.box(MINH, 0.0D, 0.0D, MAXH, MAXH, 1.0D), // Down North South                          1010101
            Shapes.box(0.0D, 0.0D, 0.0D, MAXH, MAXH, MAXH), // Down North West                           1010110
            Shapes.box(0.0D, 0.0D, 0.0D, MAXH, MAXH, 1.0D), // Down North South West                     1010111
            Shapes.box(MINH, 0.0D, MINH, 1.0D, MAXH, MAXH), // Down East                                 1011000
            Shapes.box(MINH, 0.0D, MINH, 1.0D, MAXH, 1.0D), // Down East South                           1011001
            Shapes.box(0.0D, 0.0D, MINH, 1.0D, MAXH, MAXH), // Down West East                            1011010
            Shapes.box(0.0D, 0.0D, MINH, 1.0D, MAXH, 1.0D), // Down South West East                      1011011
            Shapes.box(MINH, 0.0D, 0.0D, 1.0D, MAXH, MAXH), // Down North East                           1011100
            Shapes.box(MINH, 0.0D, 0.0D, 1.0D, MAXH, 1.0D), // Down North South East                     1011101
            Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, MAXH, MAXH), // Down North East West                      1011110
            Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, MAXH, 1.0D), // Down North South East West                1011111

            Shapes.box(MINH, MINH, MINH, MAXH, 1.0D, MAXH),  // Up                                       1100000
            Shapes.box(MINH, MINH, MINH, MAXH, 1.0D, 1.0D), // Up South                                  1100001
            Shapes.box(0.0D, MINH, MINH, MAXH, 1.0D, MAXH), // Up West                                   1100010
            Shapes.box(0.0D, MINH, MINH, MAXH, 1.0D, 1.0D), // Up South West                             1100011
            Shapes.box(MINH, MINH, 0.0D, MAXH, 1.0D, MAXH), // Up North                                  1100100
            Shapes.box(MINH, MINH, 0.0D, MAXH, 1.0D, 1.0D), // Up North South                            1100101
            Shapes.box(0.0D, MINH, 0.0D, MAXH, 1.0D, MAXH), // Up North West                             1100110
            Shapes.box(0.0D, MINH, 0.0D, MAXH, 1.0D, 1.0D), // Up North South West                       1100111
            Shapes.box(MINH, MINH, MINH, 1.0D, 1.0D, MAXH), // Up East                                   1101000
            Shapes.box(MINH, MINH, MINH, 1.0D, 1.0D, 1.0D), // Up East South                             1101001
            Shapes.box(0.0D, MINH, MINH, 1.0D, 1.0D, MAXH), // Up West East                              1101010
            Shapes.box(0.0D, MINH, MINH, 1.0D, 1.0D, 1.0D), // Up South West East                        1101011
            Shapes.box(MINH, MINH, 0.0D, 1.0D, 1.0D, MAXH), // Up North East                             1101100
            Shapes.box(MINH, MINH, 0.0D, 1.0D, 1.0D, 1.0D), // Up North South East                       1101101
            Shapes.box(0.0D, MINH, 0.0D, 1.0D, 1.0D, MAXH), // Up North East West                        1101110
            Shapes.box(0.0D, MINH, 0.0D, 1.0D, 1.0D, 1.0D), // Up North South East West                  1101111

            Shapes.box(MINH, 0.0D, MINH, MAXH, 1.0D, MAXH),  // Up Down                                  1110000
            Shapes.box(MINH, 0.0D, MINH, MAXH, 1.0D, 1.0D), // Up Down South                             1110001
            Shapes.box(0.0D, 0.0D, MINH, MAXH, 1.0D, MAXH), // Up Down West                              1110010
            Shapes.box(0.0D, 0.0D, MINH, MAXH, 1.0D, 1.0D), // Up Down South West                        1110011
            Shapes.box(MINH, 0.0D, 0.0D, MAXH, 1.0D, MAXH), // Up Down North                             1110100
            Shapes.box(MINH, 0.0D, 0.0D, MAXH, 1.0D, 1.0D), // Up Down North South                       1110101
            Shapes.box(0.0D, 0.0D, 0.0D, MAXH, 1.0D, MAXH), // Up Down North West                        1110110
            Shapes.box(0.0D, 0.0D, 0.0D, MAXH, 1.0D, 1.0D), // Up Down North South West                  1110111
            Shapes.box(MINH, 0.0D, MINH, 1.0D, 1.0D, MAXH), // Up Down East                              1111000
            Shapes.box(MINH, 0.0D, MINH, 1.0D, 1.0D, 1.0D), // Up Down East South                        1111001
            Shapes.box(0.0D, 0.0D, MINH, 1.0D, 1.0D, MAXH), // Up Down West East                         1111010
            Shapes.box(0.0D, 0.0D, MINH, 1.0D, 1.0D, 1.0D), // Up Down South West East                   1111011
            Shapes.box(MINH, 0.0D, 0.0D, 1.0D, 1.0D, MAXH), // Up Down North East                        1111100
            Shapes.box(MINH, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), // Up Down North South East                  1111101
            Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, MAXH), // Up Down North East West                   1111110
            Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)}; // Up Down North South East West            1111111

//    public enum EnumWireType implements IStringSerializable
//    {
//        ALUMINUM_WIRE(0, "alu_wire"),
//        ALUMINUM_WIRE_HEAVY(1, "alu_wire_heavy"),
//        ALUMINUM_WIRE_SWITCHED(2, "alu_wire_switch"),
//        ALUMINUM_WIRE_SWITCHED_HEAVY(3, "alu_wire_switch_heavy");
//
//        private final int meta;
//        private final String name;
//
//        EnumWireType(int meta, String name)
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
//        private final static EnumWireType[] values = values();
//
//        public static EnumWireType byMetadata(int meta)
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

    public BlockAluminumWire(Properties builder)
    {
        super(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
    {
//        state = this.getActualState(state, source, pos);
        return BOUNDING_BOXES[getBoundingBoxIdx(state)];
    }

    private static int getBoundingBoxIdx(BlockState state)
    {
        int i = 0;

        if (state.getValue(NORTH))
        {
            i |= 1 << Direction.NORTH.get2DDataValue();
        }

        if (state.getValue(EAST))
        {
            i |= 1 << Direction.EAST.get2DDataValue();
        }

        if (state.getValue(SOUTH))
        {
            i |= 1 << Direction.SOUTH.get2DDataValue();
        }

        if (state.getValue(WEST))
        {
            i |= 1 << Direction.WEST.get2DDataValue();
        }

        if (state.getValue(DOWN))
        {
            i |= 1 << 4;
        }

        if (state.getValue(UP))
        {
            i |= 1 << 5;
        }

        // Is heavy:
        if (state.getBlock() == GCBlocks.aluminumWireHeavy || state.getBlock() == GCBlocks.aluminumWireSwitchableHeavy)
        {
            i |= 1 << 6;
        }

        return i;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockEntity[] connectable = EnergyUtil.getAdjacentPowerConnections(new BlockVec3(context.getClickedPos()), context.getLevel(), (dir) -> true);

        return defaultBlockState().setValue(DOWN, connectable[Direction.DOWN.ordinal()] != null)
                .setValue(UP, connectable[Direction.UP.ordinal()] != null)
                .setValue(NORTH, connectable[Direction.NORTH.ordinal()] != null)
                .setValue(EAST, connectable[Direction.EAST.ordinal()] != null)
                .setValue(SOUTH, connectable[Direction.SOUTH.ordinal()] != null)
                .setValue(WEST, connectable[Direction.WEST.ordinal()] != null);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        BlockEntity tileEntity = worldIn.getBlockEntity(currentPos);

        if (tileEntity instanceof ITransmitter)
        {
            BlockEntity[] connectable = EnergyUtil.getAdjacentPowerConnections(tileEntity);

            return stateIn.setValue(DOWN, connectable[Direction.DOWN.ordinal()] != null)
                    .setValue(UP, connectable[Direction.UP.ordinal()] != null)
                    .setValue(NORTH, connectable[Direction.NORTH.ordinal()] != null)
                    .setValue(EAST, connectable[Direction.EAST.ordinal()] != null)
                    .setValue(SOUTH, connectable[Direction.SOUTH.ordinal()] != null)
                    .setValue(WEST, connectable[Direction.WEST.ordinal()] != null);
        }

        return stateIn;
    }

//    @Override
//    public ItemGroup getCreativeTabToDisplayOn()
//    {
//        return GalacticraftCore.galacticraftBlocksTab;
//    }
//
//    @Override
//    public boolean isOpaqueCube(BlockState state)
//    {
//        return false;
//    }
//
//    @Override
//    public boolean isFullCube(BlockState state)
//    {
//        return false;
//    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockGetter world)
    {
        if (this == GCBlocks.aluminumWire)
        {
            return new TileEntityAluminumWire.TileEntityAluminumWireT1();
        }
        else if (this == GCBlocks.aluminumWireHeavy)
        {
            return new TileEntityAluminumWire.TileEntityAluminumWireT2();
        }
        else if (this == GCBlocks.aluminumWireSwitchable)
        {
            return new TileEntityAluminumWireSwitch.TileEntityAluminumWireSwitchableT1();
        }
        else if (this == GCBlocks.aluminumWireSwitchableHeavy)
        {
            return new TileEntityAluminumWireSwitch.TileEntityAluminumWireSwitchableT2();
        }
        return null;
    }

    @Override
    public boolean isEntityBlock()
    {
        return true;
    }

    //    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void getSubBlocks(ItemGroup tab, NonNullList<ItemStack> list)
//    {
//        list.add(new ItemStack(this, 1, 0));
//        list.add(new ItemStack(this, 1, 1));
//        list.add(new ItemStack(this, 1, 2));
//        list.add(new ItemStack(this, 1, 3));
//    }

    @Override
    public NetworkType getNetworkType(BlockState state)
    {
        return NetworkType.POWER;
    }

    @Override
    public String getShiftDescription(ItemStack item)
    {
//        switch (item.getDamage())
//        {
//        case 0:
//            return I18n.get("tile.aluminum_wire.alu_wire.description");
//        case 1:
//            return I18n.get("tile.aluminum_wire.alu_wire_heavy.description");
//        case 2:
//            return I18n.get("tile.aluminum_wire.alu_wire_switch.description");
//        case 3:
//            return I18n.get("tile.aluminum_wire.alu_wire_switch_heavy.description");
//        }
        return I18n.get(this.getDescriptionId() + ".description");
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, MIDDLE);
    }

    @Override
    public boolean showDescription(ItemStack item)
    {
        return true;
    }

    @Override
    public EnumSortCategory getCategory()
    {
        return EnumSortCategory.TRANSMITTER;
    }
}