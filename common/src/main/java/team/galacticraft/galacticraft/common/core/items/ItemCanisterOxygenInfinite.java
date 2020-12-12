package team.galacticraft.galacticraft.common.core.items;

import team.galacticraft.galacticraft.common.api.item.IItemOxygenSupply;
import team.galacticraft.galacticraft.common.api.entity.GCPlayerStats;
import team.galacticraft.galacticraft.common.core.proxy.ClientProxyCore;
import team.galacticraft.galacticraft.common.core.util.EnumColor;
import team.galacticraft.galacticraft.common.core.util.EnumSortCategory;
import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

import org.jetbrains.annotations.Nullable;

public class ItemCanisterOxygenInfinite extends Item implements IItemOxygenSupply, ISortable
{
    public ItemCanisterOxygenInfinite(Properties properties)
    {
        super(properties);
//        this.setMaxDamage(ItemCanisterGeneric.EMPTY);
//        this.setMaxStackSize(1);
//        this.setNoRepair();
//        this.setUnlocalizedName(assetName);
//        this.setContainerItem(GCItems.oilCanister);
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack par1ItemStack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        tooltip.add(new TextComponent(EnumColor.DARK_GREEN + I18n.get("gui.infinite_item.desc")));
        tooltip.add(new TextComponent(EnumColor.RED + I18n.get("gui.creative_only.desc")));
    }

    /*@Override
    @OnlyIn(Dist.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(Constants.TEXTURE_PREFIX + "oxygenCanisterInfinite");
    }*/

//    @Override
//    public ItemGroup getCreativeTab()
//    {
//        return GalacticraftCore.galacticraftItemsTab;
//    }

    @Override
    public ItemStack getContainerItem(ItemStack itemstack)
    {
        if (super.getContainerItem(itemstack).isEmpty())
        {
            return ItemStack.EMPTY;
        }
        return itemstack;
    }

    @Override
    public int discharge(ItemStack itemStack, int amount)
    {
        return amount;
    }

    @Override
    public int getOxygenStored(ItemStack par1ItemStack)
    {
        return par1ItemStack.getMaxDamage();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Rarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public EnumSortCategory getCategory()
    {
        return EnumSortCategory.TANKS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player instanceof ServerPlayer)
        {
            GCPlayerStats stats = GCPlayerStats.get(player);
            ItemStack gear = stats.getExtendedInventory().getItem(2);
            ItemStack gear1 = stats.getExtendedInventory().getItem(3);

            if (gear.isEmpty())
            {
                stats.getExtendedInventory().setItem(2, itemStack.copy());
                itemStack = ItemStack.EMPTY;
            }
            else if (gear1.isEmpty())
            {
                stats.getExtendedInventory().setItem(3, itemStack.copy());
                itemStack = ItemStack.EMPTY;
            }
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
    }
}