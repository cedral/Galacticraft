package team.galacticraft.galacticraft.common.core.items;
//
//import team.galacticraft.galacticraft.common.core.GalacticraftCore;
//import team.galacticraft.galacticraft.common.core.proxy.ClientProxyCore;
//import team.galacticraft.galacticraft.common.core.util.EnumSortCategoryItem;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.Rarity;
//import net.minecraft.item.ItemFood;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fml.EnvType;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class ItemCheese extends ItemFood implements ISortableItem
//{
//    public ItemCheese(int par1, float par2, boolean par3)
//    {
//        super(par1, par2, par3);
//        this.setUnlocalizedName("cheese_curd");
//    }
//
//    public ItemCheese(int par1, boolean par2)
//    {
//        this(par1, 0.6F, par2);
//    }
//
//    /*@Override
//    @OnlyIn(Dist.CLIENT)
//    public void registerIcons(IIconRegister iconRegister)
//    {
//        this.itemIcon = iconRegister.registerIcon("galacticraftmoon:cheese_curd");
//    }*/
//
////    @Override
////    public ItemGroup getCreativeTab()
////    {
////        return GalacticraftCore.galacticraftItemsTab;
////    }
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public Rarity getRarity(ItemStack par1ItemStack)
//    {
//        return ClientProxyCore.galacticraftItem;
//    }
//
//    @Override
//    public EnumSortCategoryItem getCategory()
//    {
//        return EnumSortCategoryItem.GENERAL;
//    }
//}