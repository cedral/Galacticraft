package team.galacticraft.galacticraft.common.core.blocks;
//
//import com.google.common.base.Predicates;
//import com.google.common.collect.ImmutableMap;
//import com.google.common.collect.ImmutableTable;
//import com.google.common.collect.Iterables;
//import team.galacticraft.galacticraft.common.core.util.JavaUtil;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.properties.IProperty;
//import net.minecraftforge.common.property.ExtendedBlockState;
//import net.minecraftforge.common.property.IExtendedBlockState;
//import net.minecraftforge.common.property.IUnlistedProperty;
//import net.minecraftforge.fluids.BlockFluidBase;
//import net.minecraftforge.fluids.BlockFluidClassic;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import java.util.*;
//
//public class BlockGratingExtendedState extends ExtendedBlockState
//{
//    public BlockGratingExtendedState(Block blockIn, IProperty<?>[] properties, IUnlistedProperty<?>[] unlistedProperties)
//    {
//        super(blockIn, properties, unlistedProperties);
//    }
//
//    @Override
//    @NotNull
//    protected StateImplementation createState(@NotNull Block block, @NotNull  ImmutableMap<IProperty<?>, Comparable<?>> properties, @Nullable ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties)
//    {
//        if (unlistedProperties == null || unlistedProperties.isEmpty()) return super.createState(block, properties, unlistedProperties);
//        return new ExtendedStateImplementation(block, properties, unlistedProperties, null, null);
//    }
//
//    protected static class ExtendedStateImplementation extends StateImplementation implements IExtendedBlockState
//    {
//        private final ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties;
//        private BlockState cleanState;
//        private final Block blockBase;
//
//        protected ExtendedStateImplementation(Block block, ImmutableMap<IProperty<?>, Comparable<?>> properties, ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties, @Nullable ImmutableTable<IProperty<?>, Comparable<?>, BlockState> table, BlockState clean)
//        {
//            super(block, properties, table);
//            this.unlistedProperties = unlistedProperties;
//            this.cleanState = clean == null ? this : clean;
//            this.blockBase = block;
//        }
//
//        /**
//         * Get a version of this BlockState with the given Property now set to the given value
//         */
//        @Override
//        @NotNull
//        public <T extends Comparable<T>, V extends T> BlockState with(@NotNull IProperty<T> property, @NotNull V value)
//        {
//            BlockState clean = super.with(property, value);
//            if (clean == this.cleanState) {
//                return this;
//            }
//
//            if (Iterables.all(unlistedProperties.values(), Predicates.<Optional<?>>equalTo(Optional.empty())))
//            { // no dynamic properties present, looking up in the normal table
//                return clean;
//            }
//
//            return new ExtendedStateImplementation(getBlock(), clean.getProperties(), unlistedProperties, ((StateImplementation)clean).getPropertyValueTable(), this.cleanState);
//        }
//
//        @Override
//        public <V> IExtendedBlockState with(IUnlistedProperty<V> property, V value)
//        {
//            if(!this.unlistedProperties.containsKey(property))
//            {
//                throw new IllegalArgumentException("Cannot set unlisted property " + property + " as it does not exist in " + getBlock().getBlockState());
//            }
//            if(!property.isValid(value))
//            {
//                throw new IllegalArgumentException("Cannot set unlisted property " + property + " to " + value + " on block " + Block.REGISTRY.getNameForObject(getBlock()) + ", it is not an allowed value");
//            }
//            Map<IUnlistedProperty<?>, Optional<?>> newMap = new HashMap<IUnlistedProperty<?>, Optional<?>>(unlistedProperties);
//            newMap.put(property, Optional.ofNullable(value));
//            if(Iterables.all(newMap.values(), Predicates.<Optional<?>>equalTo(Optional.empty())))
//            { // no dynamic properties, lookup normal state
//                return (IExtendedBlockState)cleanState;
//            }
//            return new ExtendedStateImplementation(getBlock(), getProperties(), ImmutableMap.copyOf(newMap), propertyValueTable, this.cleanState);
//        }
//
//        @Override
//        public Collection<IUnlistedProperty<?>> getUnlistedNames()
//        {
//            return Collections.unmodifiableCollection(unlistedProperties.keySet());
//        }
//
//        @Override
//        public <V>V getValue(IUnlistedProperty<V> property)
//        {
//            if(!this.unlistedProperties.containsKey(property))
//            {
//                throw new IllegalArgumentException("Cannot get unlisted property " + property + " as it does not exist in " + getBlock().getBlockState());
//            }
//            return property.getType().cast(this.unlistedProperties.get(property).orElse(null));
//        }
//
//        @Override
//        public ImmutableMap<IUnlistedProperty<?>, Optional<?>> getUnlistedProperties()
//        {
//            return unlistedProperties;
//        }
//
//        @Override
//        public BlockState getClean()
//        {
//            return cleanState;
//        }
//
//        @Override
//        public Block getBlock()
//        {
//            if (JavaUtil.instance.isCalledBy(BlockFluidClassic.class, BlockFluidBase.class, BlockFluidGC.class))
//            {
//                BlockState bs = ((BlockGrating)blockBase).getLiquidBlock(this);
//                if (bs != null) return bs.getBlock();
//            }
//            return this.blockBase;
//        }
//    }
//}