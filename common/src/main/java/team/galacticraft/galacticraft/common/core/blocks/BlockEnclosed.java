package team.galacticraft.galacticraft.common.core.blocks;
//
//import appeng.api.AEApi;
//import appeng.api.parts.IPartHelper;
//import ic2.api.network.INetworkManager;
//import team.galacticraft.galacticraft.common.api.block.IPartialSealableBlock;
//import team.galacticraft.galacticraft.common.api.transmission.tile.IConductor;
//import team.galacticraft.galacticraft.common.api.transmission.tile.INetworkConnection;
//import team.galacticraft.galacticraft.common.core.GCBlocks;
//import team.galacticraft.galacticraft.common.core.GalacticraftCore;
//import team.galacticraft.galacticraft.common.core.energy.tile.TileCableIC2Sealed;
//import team.galacticraft.galacticraft.common.core.items.IShiftDescription;
//import team.galacticraft.galacticraft.common.core.tile.TileEntityAluminumWire;
//import team.galacticraft.galacticraft.common.core.tile.TileEntityFluidPipe;
//import team.galacticraft.galacticraft.common.core.tile.TileEntityNull;
//import team.galacticraft.galacticraft.common.core.util.CompatibilityManager;
//import team.galacticraft.galacticraft.common.core.util.EnumSortCategory;
//import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.ITileEntityProvider;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.ItemStack;
//import net.minecraft.state.EnumProperty;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.Direction;
//import net.minecraft.util.IStringSerializable;
//import net.minecraft.util.NonNullList;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.RayTraceResult;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.EnvType;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//public class BlockEnclosed extends Block implements IPartialSealableBlock, ITileEntityProvider, IShiftDescription, ISortable
//{
//    public static Item[] pipeItemsBC = new Item[6];
//    public static Block blockPipeBC = null;
//    public static Method onBlockNeighbourChangeIC2a = null;
//    public static Method onBlockNeighbourChangeIC2b = null;
//    private static Class icCableContainerClass;
//    private static Class icWireRegClass;
//    private static Field icWireRegInstance;
//    private static Method icContainerGetMethod;
//    private static Constructor icTileCableConstuctor;
//
//    public static final EnumProperty<EnumEnclosedBlockType> TYPE = EnumProperty.create("type", EnumEnclosedBlockType.class);
//
//    public enum EnumEnclosedBlockType implements IStringSerializable
//    {
////        copper(1, 1, 0.25F, 0.2D, 128),
////        glass(0, 0, 0.25F, 0.025D, 8192),
////        gold(2, 1, 0.1875F, 0.4D, 512),
////        iron(3, 1, 0.375F, 0.8D, 2048),
////        tin(1, 1, 0.25F, 0.2D, 32),
////        detector(0, 2147483647, 0.5F, 0.5D, 8192),
////        splitter(0, 2147483647, 0.5F, 0.5D, 8192);
//        IC2_HV_CABLE(0, "iron", 2, 7, "enclosed_hv_cable"),
//        OXYGEN_PIPE(1, "enclosed_fluid_pipe"),
//        IC2_COPPER_CABLE(2, "copper", 1, 1, "enclosed_copper_cable"),
//        IC2_GOLD_CABLE(3, "gold", 2, 4, "enclosed_gold_cable"),
//        TE_CONDUIT(4, "enclosed_te_conduit"), //CURRENTLY UNUSED
//        IC2_GLASS_FIBRE_CABLE(5, "glass", 0, 9, "enclosed_glass_fibre_cable"),
//        IC2_LV_CABLE(6, "tin", 1, 10, "enclosed_lv_cable"),
//        BC_ITEM_STONEPIPE(7, "pipeItemStone", "enclosed_pipe_items_stone"),
//        BC_ITEM_COBBLESTONEPIPE(8, "pipeItemCobble", "enclosed_pipe_items_cobblestone"),
//        BC_FLUIDS_STONEPIPE(9, "pipeFluidStone", "enclosed_pipe_fluids_stone"),
//        BC_FLUIDS_COBBLESTONEPIPE(10, "pipeFluidCobble", "enclosed_pipe_fluids_cobblestone"),
//        BC_POWER_STONEPIPE(11, "pipePowerStone", "enclosed_pipe_power_stone"),
//        BC_POWER_GOLDPIPE(12, "pipePowerGold", "enclosed_pipe_power_gold"),
//        ME_CABLE(13, "enclosed_me_cable"),
//        ALUMINUM_WIRE(14, "enclosed_aluminum_wire"),
//        ALUMINUM_WIRE_HEAVY(15, "enclosed_heavy_aluminum_wire");
//
//        private final int meta;
//        private final String name;
//        private final String ic2Enum;
//        private final int ic2Insulation;
//        private final int icClassicMeta;
//        private final String bcPipeType;
//
//        EnumEnclosedBlockType(int meta, String bcPipeType, String name)
//        {
//            this(meta, null, -1, -1, bcPipeType, name);
//        }
//
//        EnumEnclosedBlockType(int meta, String name)
//        {
//            this(meta, null, -1, -1, null, name);
//        }
//
//        EnumEnclosedBlockType(int meta, String ic2Enum, int ic2Insulation, int icClassicMeta, String name)
//        {
//            this(meta, ic2Enum, ic2Insulation, icClassicMeta, null, name);
//        }
//
//        EnumEnclosedBlockType(int meta, String ic2Enum, int ic2Insulation, int icClassicMeta, String bcPipeType, String name)
//        {
//            this.meta = meta;
//            this.ic2Enum = ic2Enum;
//            this.ic2Insulation = ic2Insulation;
//            this.icClassicMeta = icClassicMeta;
//            this.bcPipeType = bcPipeType;
//            this.name = name;
//        }
//
//        public int getMeta()
//        {
//            return this.meta;
//        }
//
//        public String getIc2Enum()
//        {
//            return ic2Enum;
//        }
//
//        public int getIc2Insulation()
//        {
//            return ic2Insulation;
//        }
//
//        public String getBCPipeType()
//        {
//            return bcPipeType;
//        }
//
//        private final static EnumEnclosedBlockType[] values = values();
//        public static EnumEnclosedBlockType byMetadata(int meta)
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
//
//    public BlockEnclosed(Properties builder)
//    {
//        super(builder);
//    }
//
////    @OnlyIn(Dist.CLIENT)
////    @Override
////    public void getSubBlocks(ItemGroup tab, NonNullList<ItemStack> list)
////    {
////        list.add(new ItemStack(this, 1, EnumEnclosedBlockType.ALUMINUM_WIRE.getMeta()));
////        list.add(new ItemStack(this, 1, EnumEnclosedBlockType.ALUMINUM_WIRE_HEAVY.getMeta()));
////        list.add(new ItemStack(this, 1, EnumEnclosedBlockType.OXYGEN_PIPE.getMeta()));
////
////        if (CompatibilityManager.isTELoaded() || GCBlocks.registeringSorted)
////        {
////            // par3List.add(new ItemStack(par1, 1, 0));
////        }
////
////        if (CompatibilityManager.isIc2Loaded() || GCBlocks.registeringSorted)
////        {
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.IC2_COPPER_CABLE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.IC2_GOLD_CABLE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.IC2_HV_CABLE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.IC2_GLASS_FIBRE_CABLE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.IC2_LV_CABLE.getMeta()));
////        }
////
////        if (CompatibilityManager.isBCraftTransportLoaded() || GCBlocks.registeringSorted)
////        {
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.BC_ITEM_COBBLESTONEPIPE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.BC_ITEM_STONEPIPE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.BC_FLUIDS_COBBLESTONEPIPE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.BC_FLUIDS_STONEPIPE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.BC_POWER_STONEPIPE.getMeta()));
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.BC_POWER_GOLDPIPE.getMeta()));
////        }
////
////        if (CompatibilityManager.isAppEngLoaded() || GCBlocks.registeringSorted)
////        {
////            list.add(new ItemStack(this, 1, EnumEnclosedBlockType.ME_CABLE.getMeta()));
////        }
////    }
//
//    public static void initialiseBC()
//    {
//        try
//        {
//        	if (CompatibilityManager.classBCTransport != null)
//        	{
//	            for (int i = 0; i < 6; i++)
//	            {
//	                String pipeName = EnumEnclosedBlockType.values()[i + 7].getBCPipeType();
//	                pipeItemsBC[i] = (Item) CompatibilityManager.classBCTransport.getField(pipeName).get(null);
//	            }
//        	}
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
////    @Override
////    public ItemGroup getCreativeTabToDisplayOn()
////    {
////        return GalacticraftCore.galacticraftBlocksTab;
////    }
//
//    @Override
//    public int damageDropped(BlockState state)
//    {
//        return state.getBlock().getMetaFromState(state);
//    }
//
//    @Override
//    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
//    {
////        int metadata = state.getBlock().getMetaFromState(state);
//        EnumEnclosedBlockType type = state.get(TYPE);
//        final TileEntity tileEntity = worldIn.getTileEntity(pos);
//
//        if (type == EnumEnclosedBlockType.TE_CONDUIT)
//        {
//            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//        }
//        else if (type == EnumEnclosedBlockType.OXYGEN_PIPE)
//        {
//            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//
//            if (tileEntity instanceof INetworkConnection)
//            {
//                ((INetworkConnection) tileEntity).refresh();
//            }
//        }
//        else if (type.getMeta() <= 6)
//        {
//            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//            if (CompatibilityManager.isIc2Loaded() && tileEntity != null)
//            {
//                try
//                {
//                    if (onBlockNeighbourChangeIC2a != null)
//                    {
//                        onBlockNeighbourChangeIC2a.invoke(tileEntity, blockIn);
//                    }
//                    else if (onBlockNeighbourChangeIC2b != null)
//                    {
//                        onBlockNeighbourChangeIC2b.invoke(tileEntity, blockIn, pos);
//                    }
//                    return;
//                }
//                catch (Exception ignore)
//                {
//                }
//            }
//        }
//        else if (type.getMeta() <= 12)
//        {
//            if (CompatibilityManager.isBCraftTransportLoaded())
//            {
//                if (blockPipeBC != null)
//                {
//                    try
//                    {
//                        blockPipeBC.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                    return;
//                }
//            }
//
//            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//        }
//        else if (type.getMeta() <= EnumEnclosedBlockType.ME_CABLE.getMeta())
//        {
//            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//            if (CompatibilityManager.isAppEngLoaded())
//            {
////                worldIn.notifyBlockUpdate(pos); TODO
//            }
//        }
//        else if (type.getMeta() <= EnumEnclosedBlockType.ALUMINUM_WIRE.getMeta())
//        {
//            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//            if (tileEntity instanceof IConductor)
//            {
//                ((IConductor) tileEntity).refresh();
//            }
//        }
//        else if (type.getMeta() <= EnumEnclosedBlockType.ALUMINUM_WIRE_HEAVY.getMeta())
//        {
//            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
//            if (tileEntity instanceof IConductor)
//            {
//                ((IConductor) tileEntity).refresh();
//            }
//        }
//    }
//
//    @Override
//    public TileEntity createNewTileEntity(World world, int metadata)
//    {
//        if (metadata == EnumEnclosedBlockType.TE_CONDUIT.getMeta())
//        {
//            //TODO
//        }
//        else if (metadata == EnumEnclosedBlockType.OXYGEN_PIPE.getMeta())
//        {
//            return new TileEntityFluidPipe();
//        }
//        else if (metadata <= 6)
//        {
//            if (CompatibilityManager.isIc2Loaded())
//            {
//                try
//                {
//                    EnumEnclosedBlockType enclosedType = EnumEnclosedBlockType.byMetadata(metadata);
//                    if (CompatibilityManager.isIc2ClassicLoaded())
//                    {
//                        if (icCableContainerClass == null)
//                        {
//                            icCableContainerClass = Class.forName("ic2.core.block.wiring.cables.CableContainer");
//
//                            icWireRegClass = Class.forName("ic2.core.block.wiring.cables.WireRegistry");
//                            icWireRegInstance = icWireRegClass.getDeclaredField("instance");
//                            icContainerGetMethod = icWireRegClass.getMethod("getContainerFromMeta", int.class);
//
//                            icTileCableConstuctor = CompatibilityManager.classIc2ClassicTileCable.getConstructor(icCableContainerClass);
//                        }
//
//                        Object wireRegInstance = icWireRegInstance.get(null);
//                        Object container = icContainerGetMethod.invoke(wireRegInstance, enclosedType.icClassicMeta);
//
//                        return (TileEntity) icTileCableConstuctor.newInstance(container);
//                    }
//                    else
//                    {
//                        // IndustrialCraft 2 uses enums for types
//                        Enum[] enums = (Enum[]) CompatibilityManager.classIC2cableType.getEnumConstants();
//                        Enum foundEnum = null;
//                        for (Enum e : enums)
//                        {
//                            if (e.name().equals(enclosedType.getIc2Enum()))
//                            {
//                                foundEnum = e;
//                                break;
//                            }
//                        }
//
//                        return new TileCableIC2Sealed().setupInsulation(foundEnum, enclosedType.getIc2Insulation());
//                    }
//                }
//                catch (Exception ex)
//                {
//                    ex.printStackTrace();
//                }
//            }
//        }
//        else if (metadata <= 12)
//        {
//            if (CompatibilityManager.isBCraftTransportLoaded())
//            {
//                try
//                {
//                    return (TileEntity) CompatibilityManager.classBCTransportPipeTile.getConstructor().newInstance();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//        else if (metadata <= EnumEnclosedBlockType.ME_CABLE.getMeta())
//        {
//            if (CompatibilityManager.isAppEngLoaded())
//            {
//            	//Emulate Api.INSTANCE.partHelper().getCombinedInstance( TileCableBus.class )
//                try
//                {
//                    IPartHelper apiPart = AEApi.instance().partHelper();
//                    Class classTileCableBus = Class.forName("appeng.tile.networking.TileCableBus");
//                    for (Method m : apiPart.getClass().getMethods())
//                    {
//                        if ("getCombinedInstance".equals(m.getName()))
//                        {
//                            return (TileEntity) ((Class)m.invoke(apiPart, classTileCableBus)).newInstance();
//                        }
//                    }
//                }
//                catch (Exception e) { e.printStackTrace(); }
//            }
//        }
//        else if (metadata <= EnumEnclosedBlockType.ALUMINUM_WIRE.getMeta())
//        {
//            return new TileEntityAluminumWire(1);
//        }
//        else if (metadata <= EnumEnclosedBlockType.ALUMINUM_WIRE_HEAVY.getMeta())
//        {
//            return new TileEntityAluminumWire(2);
//        }
//
//        return new TileEntityNull();
//    }
//
//    @Override
//    public boolean isSealed(World world, BlockPos pos, Direction direction)
//    {
//        return true;
//    }
//
//    @Override
//    public String getShiftDescription(ItemStack stack)
//    {
//        return I18n.get(this.getTranslationKey() + ".description");
//    }
//
//    @Override
//    public boolean showDescription(ItemStack stack)
//    {
//        return true;
//    }
//
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
//    {
//        int metadata = stack.getDamage();
//        if (metadata >= EnumEnclosedBlockType.BC_ITEM_STONEPIPE.getMeta() && metadata <= EnumEnclosedBlockType.BC_POWER_GOLDPIPE.getMeta())
//        {
//        	try
//        	{
//        		TileEntity tile = worldIn.getTileEntity(pos);
//        		if (CompatibilityManager.classBCTransportPipeTile.isInstance(tile))
//        		{
//	        		Method m = CompatibilityManager.classBCTransportPipeTile.getMethod("onPlacedBy", LivingEntity.class, ItemStack.class);
//	        		m.invoke(tile, placer, new ItemStack(pipeItemsBC[metadata - 7]));
//        		}
//        	}
//        	catch (Exception e)
//        	{
//        		e.printStackTrace();
//        	}
//        }
//        else if (!worldIn.isRemote && metadata <= EnumEnclosedBlockType.IC2_LV_CABLE.getMeta() && metadata != EnumEnclosedBlockType.OXYGEN_PIPE.getMeta() && metadata != EnumEnclosedBlockType.TE_CONDUIT.getMeta() && CompatibilityManager.isIc2Loaded())
//        {
//            TileEntity te = worldIn.getTileEntity(pos);
//            if (te != null)
//            {
//                try {
//                    Object network = CompatibilityManager.fieldIC2networkManager.get(null);
//                    if (CompatibilityManager.isIc2ClassicLoaded())
//                    {
//                        Method get = network.getClass().getMethod("get");
//                        Object manager = get.invoke(network);
//                        Method sendMethod = CompatibilityManager.classIc2ClassicNetworkManager.getMethod("sendInitialData", ServerPlayerEntity.class, TileEntity.class);
//                        sendMethod.invoke(manager, (ServerPlayerEntity) placer, te);
//                    }
//                    else
//                    {
//                        INetworkManager manager = null;
//                        Method get = network.getClass().getMethod("get", boolean.class);
//                        manager = (INetworkManager)get.invoke(network, true);
//                        if (manager != null)
//                        {
//                            manager.sendInitialData(te);
//                        }
//                    }
//                }
//                catch (Exception e) { e.printStackTrace(); }
//            }
//        }
//    }
//
//    @Override
//    public BlockState getStateFromMeta(int meta)
//    {
//        EnumEnclosedBlockType type = EnumEnclosedBlockType.byMetadata(meta);
//        return this.getDefaultState().with(TYPE, type);
//    }
//
//    @Override
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
//    {
//        builder.add(TYPE);
//    }
//
//    @Override
//    public EnumSortCategory getCategory()
//    {
//        return EnumSortCategory.TRANSMITTER;
//    }
//
//    @Override
//    public ItemStack getCloneItemStack(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
//    {
//        return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(state));
//    }
//}