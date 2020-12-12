package team.galacticraft.galacticraft.common.core.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class InventoryBuggyBench implements Container
{
    private final NonNullList<ItemStack> stackList;
    private final int inventoryWidth;
    private final AbstractContainerMenu eventHandler;

    public InventoryBuggyBench(AbstractContainerMenu par1Container)
    {
        final int size = 32;
        this.stackList = NonNullList.withSize(size, ItemStack.EMPTY);
        this.eventHandler = par1Container;
        this.inventoryWidth = 5;
    }

    @Override
    public int getContainerSize()
    {
        return this.stackList.size();
    }

    @Override
    public ItemStack getItem(int par1)
    {
        return par1 >= this.getContainerSize() ? ItemStack.EMPTY : this.stackList.get(par1);
    }

    public ItemStack getStackInRowAndColumn(int par1, int par2)
    {
        if (par1 >= 0 && par1 < this.inventoryWidth)
        {
            final int var3 = par1 + par2 * this.inventoryWidth;
            return this.getItem(var3);
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }

//    @Override
//    public String getName()
//    {
//        return "container.crafting";
//    }

    @Override
    public ItemStack removeItemNoUpdate(int index)
    {
        ItemStack oldstack = ContainerHelper.takeItem(this.stackList, index);
        if (!oldstack.isEmpty())
        {
            this.setChanged();
            this.eventHandler.slotsChanged(this);
        }
        return oldstack;
    }

    @Override
    public ItemStack removeItem(int index, int count)
    {
        ItemStack itemstack = ContainerHelper.removeItem(this.stackList, index, count);

        if (!itemstack.isEmpty())
        {
            this.setChanged();
            this.eventHandler.slotsChanged(this);
        }

        return itemstack;
    }

    @Override
    public void setItem(int index, ItemStack stack)
    {
        if (stack.getCount() > this.getMaxStackSize())
        {
            stack.setCount(this.getMaxStackSize());
        }

        this.stackList.set(index, stack);
        this.setChanged();
        this.eventHandler.slotsChanged(this);
    }

    @Override
    public int getMaxStackSize()
    {
        return 64;
    }

    @Override
    public void setChanged()
    {
    }

    @Override
    public boolean stillValid(Player par1EntityPlayer)
    {
        return true;
    }

    @Override
    public void startOpen(Player player)
    {
    }

    @Override
    public void stopOpen(Player player)
    {
    }

//    @Override
//    public boolean hasCustomName()
//    {
//        return false;
//    }

    @Override
    public boolean canPlaceItem(int i, ItemStack itemstack)
    {
        return false;
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.stackList)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

//    @Override
//    public int getField(int id)
//    {
//        return 0;
//    }
//
//    @Override
//    public void setField(int id, int value)
//    {
//    }

//    @Override
//    public int getFieldCount()
//    {
//        return 0;
//    }

    @Override
    public void clearContent()
    {

    }

//    @Override
//    public ITextComponent getDisplayName()
//    {
//        return this.hasCustomName() ? new StringTextComponent(this.getName()) : new TranslationTextComponent(this.getName(), new Object[0]);
//    }
}