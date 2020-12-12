package team.galacticraft.galacticraft.common.core.entities;

import team.galacticraft.galacticraft.common.api.entity.IEntityBreathable;
import team.galacticraft.galacticraft.common.core.GCItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.Level;

public class EntityEvolvedEnderman extends EnderMan implements IEntityBreathable
{
    public EntityEvolvedEnderman(EntityType<? extends EntityEvolvedEnderman> type, Level worldIn)
    {
        super(type, worldIn);
    }

    @Override
    public boolean canBreath()
    {
        return true;
    }

//    @Override
//    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
//    {
//        super.dropLoot(wasRecentlyHit, lootingModifier, source);
//        if (wasRecentlyHit && this.rand.nextFloat() < 0.025F + (float)lootingModifier * 0.015F)
//        {
//            this.addRandomDrop();
//        }
//    }

//    protected void addRandomDrop()
//    {
//        switch (this.rand.nextInt(10))
//        {
//        case 0:
//        case 1:
//        case 2:
//        case 3:
//        case 4:
//        case 5:
//            this.dropItem(Items.ENDER_PEARL, 1);
//            break;
//        case 6:
//            //Oxygen tank half empty or less
//            this.entityDropItem(new ItemStack(GCItems.oxTankMedium, 1, 901 + this.rand.nextInt(900)), 0.0F);
//            break;
//        case 7:
//        case 8:
//            this.dropItem(Items.ENDER_EYE, 1);
//            break;
//        case 9:
//            this.dropItem(GCItems.oxygenConcentrator, 1);
//            break;
//        case 10:
//            this.dropItem(GCItems.oxMask, 1);
//            break;
//        }
//    }
}