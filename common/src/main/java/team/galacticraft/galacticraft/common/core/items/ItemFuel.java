package team.galacticraft.galacticraft.common.core.items;
//
//import team.galacticraft.galacticraft.common.core.proxy.ClientProxyCore;
//import net.minecraft.item.Rarity;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fml.EnvType;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//public class ItemFuel extends Item
//{
//    public ItemFuel(Item.Properties properties)
//    {
//        super(properties);
//        this.setUnlocalizedName(assetName);
//    }
//
//    /*@Override
//    @OnlyIn(Dist.CLIENT)
//    public void registerIcons(IIconRegister par1IconRegister)
//    {
//        this.itemIcon = par1IconRegister.registerIcon(Constants.TEXTURE_PREFIX + "fuel_flow");
//    }*/
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public Rarity getRarity(ItemStack par1ItemStack)
//    {
//        return ClientProxyCore.galacticraftItem;
//    }
//}