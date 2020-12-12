package team.galacticraft.galacticraft.common.core.items;

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

import org.jetbrains.annotations.Nullable;
import java.text.DecimalFormat;
import java.util.List;

public class ItemOxygenTank extends Item implements ISortable, IClickableItem
{
    public ItemOxygenTank(Properties builder)
    {
        super(builder);
//        this.setMaxStackSize(1);
//        this.setMaxDamage(tier * 900);
//        this.setUnlocalizedName(assetName);
//        this.setTextureName(Constants.TEXTURE_PREFIX + assetName);
//        this.setNoRepair();
    }

    @Override
    public boolean isEnchantable(ItemStack stack)
    {
        return false;
    }

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void getSubItems(ItemGroup tab, NonNullList<ItemStack> list)
//    {
//        if (tab == GalacticraftCore.galacticraftItemsTab || tab == ItemGroup.SEARCH)
//        {
//            list.add(new ItemStack(this, 1, 0));
//            list.add(new ItemStack(this, 1, this.getMaxDamage()));
//        }
//    }

//    @Override
//    public ItemGroup getCreativeTab()
//    {
//        return GalacticraftCore.galacticraftItemsTab;
//    }

    @Override
    @Environment(EnvType.CLIENT)
    public Rarity getRarity(ItemStack par1ItemStack)
    {
        return ClientProxyCore.galacticraftItem;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        float damagePercent = (1.0F - stack.getDamageValue() / (float)stack.getMaxDamage()) * 100.0F;
        DecimalFormat df = new DecimalFormat("#.##");
        String percentageStr = df.format(damagePercent) + "%" + EnumColor.WHITE;
        if (damagePercent < 20.0F)
        {
            percentageStr = EnumColor.RED + percentageStr;
        }
        else if (damagePercent < 80.0F)
        {
            percentageStr = EnumColor.YELLOW + percentageStr;
        }
        else
        {
            percentageStr = EnumColor.DARK_GREEN + percentageStr;
        }
        tooltip.add(new TranslatableComponent(("gui.tank.oxygen_remaining") + ": " + (stack.getMaxDamage() - stack.getDamageValue()) + " (" + percentageStr + ")"));
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
            if (itemStack.getItem() instanceof IClickableItem)
            {
                itemStack = ((IClickableItem) itemStack.getItem()).onItemRightClick(itemStack, worldIn, player);
            }

            if (itemStack.isEmpty())
            {
                return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
            }
        }
        return new InteractionResultHolder<>(InteractionResult.PASS, itemStack);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, Level worldIn, Player player)
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

        return itemStack;
    }
}