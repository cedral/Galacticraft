package team.galacticraft.galacticraft.common.core.items;
//
//import team.galacticraft.galacticraft.common.core.GalacticraftCore;
//import team.galacticraft.galacticraft.common.core.proxy.ClientProxyCore;
//import net.minecraft.block.Block;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.Rarity;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fml.EnvType;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class ItemBlockCheese extends ItemBlockDesc
//{
//    public ItemBlockCheese(Block par2Block)
//    {
//        super(par2Block);
//        this.setMaxStackSize(1);
//    }
//
//    @Override
//    public boolean isEnchantable(ItemStack stack)
//    {
//        return false;
//    }
//
//    @Override
//    public ItemGroup getCreativeTab()
//    {
//        return GalacticraftCore.galacticraftBlocksTab;
//    }
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public Rarity getRarity(ItemStack par1ItemStack)
//    {
//        return ClientProxyCore.galacticraftItem;
//    }
//}