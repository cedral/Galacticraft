package team.galacticraft.galacticraft.common.core.items;
//
//import team.galacticraft.galacticraft.common.core.blocks.BlockDoubleSlabGC;
//import team.galacticraft.galacticraft.common.core.blocks.BlockSlabGC;
//import team.galacticraft.galacticraft.common.core.proxy.ClientProxyCore;
//import net.minecraft.block.Block;
//import net.minecraft.item.Rarity;
//import net.minecraft.item.ItemSlab;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fml.EnvType;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class ItemBlockSlabGC extends ItemSlab
//{
//	public ItemBlockSlabGC(Block block, BlockSlabGC singleSlab, BlockDoubleSlabGC doubleSlab)
//	{
//		super(block, singleSlab, doubleSlab);
//	}
//
//	@Override
//	public int getMetadata(int meta)
//	{
//		return meta & 7;
//	}
//
//	@Override
//	@OnlyIn(Dist.CLIENT)
//	public Rarity getRarity(ItemStack par1ItemStack)
//	{
//		return ClientProxyCore.galacticraftItem;
//	}
//
//	@Override
//	public String getUnlocalizedName(ItemStack itemStack)
//	{
//		BlockSlabGC slab = (BlockSlabGC)Block.getBlockFromItem(itemStack.getItem());
//		return super.getUnlocalizedName() + "." + slab.getUnlocalizedName(itemStack.getDamage());
//	}
//}