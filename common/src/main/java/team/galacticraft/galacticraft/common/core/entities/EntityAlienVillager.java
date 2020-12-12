package team.galacticraft.galacticraft.common.core.entities;
//
//import team.galacticraft.galacticraft.common.api.entity.IEntityBreathable;
//import team.galacticraft.galacticraft.common.core.GCItems;
//import team.galacticraft.galacticraft.common.core.GalacticraftCore;
//import team.galacticraft.galacticraft.common.core.entities.player.GCPlayerHandler;
//import team.galacticraft.galacticraft.common.api.entity.GCPlayerStats;
//import team.galacticraft.galacticraft.common.core.items.ItemBasic;
//import team.galacticraft.galacticraft.common.core.items.ItemCanisterGeneric;
//import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
//import team.galacticraft.galacticraft.common.core.wrappers.PlayerGearData;
//import net.minecraft.block.Blocks;
//import net.minecraft.entity.*;
//import net.minecraft.entity.ai.goal.*;
//import net.minecraft.entity.effect.LightningBoltEntity;
//import net.minecraft.entity.item.ExperienceOrbEntity;
//import net.minecraft.entity.item.ItemEntity;
//import net.minecraft.entity.merchant.IMerchant;
//import net.minecraft.entity.monster.WitchEntity;
//import net.minecraft.entity.monster.ZombieEntity;
//import net.minecraft.entity.monster.IMob;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.inventory.Inventory;
//import net.minecraft.item.Items;
//import net.minecraft.nbt.ListNBT;
//import net.minecraft.pathfinding.GroundPathNavigator;
//import net.minecraft.potion.Effects;
//import net.minecraft.stats.Stats;
//import net.minecraft.util.SoundEvents;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.potion.EffectInstance;
//import net.minecraft.util.*;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.text.StringTextComponent;
//import net.minecraft.village.MerchantRecipe;
//import net.minecraft.village.MerchantRecipeList;
//import net.minecraft.village.Village;
//import net.minecraft.world.DifficultyInstance;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.EnvType;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.Random;
//
//public class EntityAlienVillager extends AgeableEntity implements IMerchant, INPC, IEntityBreathable
//{
//    private int randomTickDivider;
//    private boolean isMating;
//    private boolean isPlaying;
//    private Village villageObj;
//    private PlayerEntity buyingPlayer;
//    private MerchantRecipeList buyingList;
//    private int timeUntilReset;
//    private boolean needsInitilization;
//    private int wealth;
//    private String lastBuyingPlayer;
//    private boolean isLookingForHome;
//    private Inventory villagerInventory;
//    private static final EntityAlienVillager.ITradeList[] DEFAULT_TRADE_LIST_MAP = new EntityAlienVillager.ITradeList[] {
//            new EntityAlienVillager.ListItemForEmeralds(new ItemStack(GCItems.oxMask, 1, 0), new EntityAlienVillager.PriceInfo(1, 2)),
//            new EntityAlienVillager.ListItemForEmeralds(new ItemStack(GCItems.oxTankLight, 1, 235), new EntityAlienVillager.PriceInfo(3, 4)),
//            new EntityAlienVillager.ListItemForEmeralds(new ItemStack(GCItems.oxygenGear, 1, 0), new EntityAlienVillager.PriceInfo(3, 4)),
//            new EntityAlienVillager.ListItemForEmeralds(new ItemStack(GCItems.fuelCanister, 1, 317), new EntityAlienVillager.PriceInfo(3, 4)),
//            new EntityAlienVillager.ListItemForEmeralds(new ItemStack(GCItems.parachute, 1, 0), new EntityAlienVillager.PriceInfo(1, 2)),
//            new EntityAlienVillager.ListItemForEmeralds(new ItemStack(GCItems.battery, 1, 58), new EntityAlienVillager.PriceInfo(2, 4)),
//            new EntityAlienVillager.ItemAndEmeraldToItem(ItemOilCanister.createEmptyCanister(1), new EntityAlienVillager.PriceInfo(1, 1), new ItemStack(GCItems.foodItem, 1, 1)), //carrots = also yields a tin!
//            new EntityAlienVillager.ListItemForEmeralds(new ItemStack(GCItems.basicItem, 1, ItemBasic.WAFER_BASIC), new EntityAlienVillager.PriceInfo(3, 4)),
//            new EntityAlienVillager.ItemAndEmeraldToItem(new ItemStack(GCItems.schematic, 1, 0), new EntityAlienVillager.PriceInfo(3, 5), new ItemStack(GCItems.schematic, 1, 1)), //Exchange buggy and rocket schematics
//            new EntityAlienVillager.ItemAndEmeraldToItem(new ItemStack(GCItems.schematic, 1, 1), new EntityAlienVillager.PriceInfo(3, 5), new ItemStack(GCItems.schematic, 1, 0)), //Exchange buggy and rocket schematics
//            new EntityAlienVillager.ItemAndEmeraldToItem(new ItemStack(GCItems.basicItem, 2, 3), new EntityAlienVillager.PriceInfo(1, 1), new ItemStack(GCItems.basicItem, 1, 6)), //Compressed Tin - needed to craft a Fuel Loader
//            new EntityAlienVillager.ItemAndEmeraldToItem(new ItemStack(GCItems.basicItem, 2, 4), new EntityAlienVillager.PriceInfo(1, 1), new ItemStack(GCItems.basicItem, 1, 7)), //Compressed Copper - needed to craft a Fuel Loader
//            new EntityAlienVillager.EmeraldForItems(new ItemStack(Blocks.SAPLING, 1, 3), new EntityAlienVillager.PriceInfo(11, 39)) //The one thing Alien Villagers don't have and can't get is jungle trees...
//            };
//
//    public EntityAlienVillager(World worldIn)
//    {
//        super(worldIn);
//        this.villagerInventory = new Inventory("Items", false, 8);
//        this.setSize(0.6F, 1.8F);
//        ((GroundPathNavigator) this.getNavigator()).setBreakDoors(true);
//        this.goalSelector.addGoal(0, new SwimGoal(this));
//        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, ZombieEntity.class, 8.0F, 0.6D, 0.6D));
//        this.goalSelector.addGoal(1, new EntityAITradePlayerGC(this));
//        this.goalSelector.addGoal(1, new EntityAILookAtTradePlayerGC(this));
//        this.goalSelector.addGoal(2, new EntityAIMoveIndoors(this));
//        this.goalSelector.addGoal(3, new EntityAIRestrictOpenDoor(this));
//        this.goalSelector.addGoal(4, new OpenDoorGoal(this, true));
//        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 0.6D));
//        this.goalSelector.addGoal(9, new EntityAIWatchClosest2(this, PlayerEntity.class, 3.0F, 1.0F));
//        this.goalSelector.addGoal(9, new RandomWalkingGoal(this, 0.6D));
//        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
//        this.setCanPickUpLoot(true);
//    }
//
//    @Override
//    protected void onGrowingAdult()
//    {
//        super.onGrowingAdult();
//    }
//    @Override
//    protected void registerAttributes()
//    {
//        super.registerAttributes();
//        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
//    }
//
//    @Override
//    protected void updateAITasks()
//    {
//        if (--this.randomTickDivider <= 0)
//        {
//            BlockPos blockpos = new BlockPos(this);
//            this.world.getVillageCollection().addToVillagerPositionList(blockpos);
//            this.randomTickDivider = 70 + this.rand.nextInt(50);
//            this.villageObj = this.world.getVillageCollection().getNearestVillage(blockpos, 32);
//
//            if (this.villageObj == null)
//            {
//                this.detachHome();
//            }
//            else
//            {
//                BlockPos blockpos1 = this.villageObj.getCenter();
//                this.setHomePosAndDistance(blockpos1, (int) ((float) this.villageObj.getVillageRadius() * 1.0F));
//
//                if (this.isLookingForHome)
//                {
//                    this.isLookingForHome = false;
//                    this.villageObj.setDefaultPlayerReputation(5);
//                }
//            }
//        }
//
//        if (!this.isTrading() && this.timeUntilReset > 0)
//        {
//            --this.timeUntilReset;
//
//            if (this.timeUntilReset <= 0)
//            {
//                if (this.needsInitilization)
//                {
//                    for (MerchantRecipe merchantrecipe : this.buyingList)
//                    {
//                        if (merchantrecipe.isRecipeDisabled())
//                        {
//                            merchantrecipe.increaseMaxTradeUses(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
//                        }
//                    }
//
//                    this.populateBuyingList();
//                    this.needsInitilization = false;
//
//                    if (this.villageObj != null && this.lastBuyingPlayer != null)
//                    {
//                        this.world.setEntityState(this, (byte) 14);
//                        this.villageObj.modifyPlayerReputation(this.lastBuyingPlayer, 1);
//                    }
//                }
//
//                this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 0));
//            }
//        }
//
//        super.updateAITasks();
//    }
//
//    @Override
//    public boolean processInteract(PlayerEntity player, Hand hand)
//    {
//        ItemStack itemstack = player.inventory.getCurrentItem();
//        boolean flag = itemstack != null && itemstack.getItem() == Items.SPAWN_EGG;
//
//        if (!flag && this.isEntityAlive() && !this.isTrading() && !this.isChild() && !player.isSneaking())
//        {
//            PlayerGearData gearData = GalacticraftCore.proxy.getGearData(player);
//
//            if (!this.world.isRemote && (this.buyingList == null || this.buyingList.size() > 0))
//            {
//                if (gearData != null && gearData.getFrequencyModule() != GCPlayerHandler.GEAR_NOT_PRESENT)
//                {
//                    this.setCustomer(player);
//                    player.displayVillagerTradeGui(this);
//                }
//                else
//                {
//                    if (player instanceof ServerPlayerEntity)
//                    {
//                        ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
//                        GCPlayerStats stats = GCPlayerStats.get(playerMP);
//                        if (stats.getChatCooldown() == 0)
//                        {
//                            player.sendMessage(new StringTranslatableComponent(("gui.village.warning.no_freq_mod")));
//                            stats.setChatCooldown(20);
//                        }
//                    }
//                }
//            }
//
//            player.addStat(Stats.TALKED_TO_VILLAGER);
//            return true;
//        }
//        else
//        {
//            return super.processInteract(player, hand);
//        }
//    }
//
//    @Override
//    public void writeEntityToNBT(CompoundNBT tagCompound)
//    {
//        super.writeAdditional(tagCompound);
//        tagCompound.putInt("Riches", this.wealth);
//
//        if (this.buyingList != null)
//        {
//            tagCompound.setTag("Offers", this.buyingList.getRecipiesAsTags());
//        }
//
//        ListNBT nbttaglist = new ListNBT();
//
//        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
//        {
//            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);
//
//            if (!itemstack.isEmpty())
//            {
//                nbttaglist.add(itemstack.writeToNBT(new CompoundNBT()));
//            }
//        }
//
//        tagCompound.setTag("Inventory", nbttaglist);
//    }
//
//    @Override
//    public void readEntityFromNBT(CompoundNBT tagCompund)
//    {
//        super.readAdditional(tagCompund);
//        this.wealth = tagCompund.getInteger("Riches");
//
//        if (tagCompund.contains("Offers", 10))
//        {
//            CompoundNBT nbttagcompound = tagCompund.getCompound("Offers");
//            this.buyingList = new MerchantRecipeList(nbttagcompound);
//        }
//
//        ListNBT nbttaglist = tagCompund.getList("Inventory", 10);
//
//        for (int i = 0; i < nbttaglist.size(); ++i)
//        {
//            ItemStack itemstack = new ItemStack(nbttaglist.getCompound(i));
//
//            if (!itemstack.isEmpty())
//            {
//                this.villagerInventory.addItem(itemstack);
//            }
//        }
//
//        this.setCanPickUpLoot(true);
//    }
//
//    @Override
//    protected boolean canDespawn()
//    {
//        return false;
//    }
//
//    @Override
//    protected float getSoundPitch()
//    {
//        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 0.5F;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound()
//    {
//        return this.isTrading() ? SoundEvents.ENTITY_VILLAGER_TRADING : SoundEvents.ENTITY_VILLAGER_AMBIENT;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundEvents.ENTITY_VILLAGER_HURT;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound()
//    {
//        return SoundEvents.ENTITY_VILLAGER_DEATH;
//    }
//
//    public boolean isMating()
//    {
//        return this.isMating;
//    }
//
//    public void setMating(boolean mating)
//    {
//        this.isMating = mating;
//    }
//
//    public void setPlaying(boolean playing)
//    {
//        this.isPlaying = playing;
//    }
//
//    public boolean isPlaying()
//    {
//        return this.isPlaying;
//    }
//
//    @Override
//    public void setRevengeTarget(LivingEntity livingBase)
//    {
//        super.setRevengeTarget(livingBase);
//
//        if (this.villageObj != null && livingBase != null)
//        {
//            this.villageObj.addOrRenewAgressor(livingBase);
//
//            if (livingBase instanceof PlayerEntity)
//            {
//                int i = -1;
//
//                if (this.isChild())
//                {
//                    i = -3;
//                }
//
//                this.villageObj.modifyPlayerReputation(livingBase.getName(), i);
//
//                if (this.isEntityAlive())
//                {
//                    this.world.setEntityState(this, (byte) 13);
//                }
//            }
//        }
//    }
//
//    @Override
//    public void onDeath(DamageSource cause)
//    {
//        if (this.villageObj != null)
//        {
//            Entity entity = cause.getTrueSource();
//
//            if (entity != null)
//            {
//                if (entity instanceof PlayerEntity)
//                {
//                    this.villageObj.modifyPlayerReputation(entity.getName(), -2);
//                }
//                else if (entity instanceof IMob)
//                {
//                    this.villageObj.endMatingSeason();
//                }
//            }
//            else
//            {
//                PlayerEntity entityplayer = this.world.getClosestPlayerToEntity(this, 16.0D);
//
//                if (entityplayer != null)
//                {
//                    this.villageObj.endMatingSeason();
//                }
//            }
//        }
//
//        super.onDeath(cause);
//    }
//
//    @Override
//    public void setCustomer(PlayerEntity p_70932_1_)
//    {
//        this.buyingPlayer = p_70932_1_;
//    }
//
//    @Override
//    public PlayerEntity getCustomer()
//    {
//        return this.buyingPlayer;
//    }
//
//    public boolean isTrading()
//    {
//        return this.buyingPlayer != null;
//    }
//
//    @Override
//    public void useRecipe(MerchantRecipe recipe)
//    {
//        recipe.incrementToolUses();
//        this.livingSoundTime = -this.getTalkInterval();
//        this.playSound(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
//        int i = 3 + this.rand.nextInt(4);
//
//        if (recipe.getToolUses() == 1 || this.rand.nextInt(5) == 0)
//        {
//            this.timeUntilReset = 40;
//            this.needsInitilization = true;
//
//            if (this.buyingPlayer != null)
//            {
//                this.lastBuyingPlayer = this.buyingPlayer.getName();
//            }
//            else
//            {
//                this.lastBuyingPlayer = null;
//            }
//
//            i += 5;
//        }
//
//        if (recipe.getItemToBuy().getItem() == Items.EMERALD)
//        {
//            this.wealth += recipe.getItemToBuy().getCount();
//        }
//
//        if (recipe.getRewardsExp())
//        {
//            this.world.addEntity(new ExperienceOrbEntity(this.world, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), i));
//        }
//    }
//
//    @Override
//    public void verifySellingItem(ItemStack stack)
//    {
//        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20)
//        {
//            this.livingSoundTime = -this.getTalkInterval();
//
//            if (!stack.isEmpty())
//            {
//                this.playSound(SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
//            }
//            else
//            {
//                this.playSound(SoundEvents.ENTITY_VILLAGER_NO, this.getSoundVolume(), this.getSoundPitch());
//            }
//        }
//    }
//
//    @Override
//    public MerchantRecipeList getRecipes(PlayerEntity p_70934_1_)
//    {
//        if (this.buyingList == null)
//        {
//            this.populateBuyingList();
//        }
//
//        return this.buyingList;
//    }
//
//    private void populateBuyingList()
//    {
//        if (this.buyingList == null)
//        {
//            this.buyingList = new MerchantRecipeList();
//        }
//
//        for (EntityAlienVillager.ITradeList tradeList : DEFAULT_TRADE_LIST_MAP)
//        {
//            tradeList.modifyMerchantRecipeList(this.buyingList, this.rand);
//        }
//    }
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void setRecipes(MerchantRecipeList recipeList)
//    {
//    }
//
//    @Override
//    public float getEyeHeight()
//    {
//        float f = 1.62F;
//
//        if (this.isChild())
//        {
//            f = (float) ((double) f - 0.81D);
//        }
//
//        return f;
//    }
//
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public void handleStatusUpdate(byte id)
//    {
//        if (id == 12)
//        {
//            this.spawnParticles(EnumParticleTypes.HEART);
//        }
//        else if (id == 13)
//        {
//            this.spawnParticles(EnumParticleTypes.VILLAGER_ANGRY);
//        }
//        else if (id == 14)
//        {
//            this.spawnParticles(EnumParticleTypes.VILLAGER_HAPPY);
//        }
//        else
//        {
//            super.handleStatusUpdate(id);
//        }
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    private void spawnParticles(EnumParticleTypes particleType)
//    {
//        for (int i = 0; i < 5; ++i)
//        {
//            double d0 = this.rand.nextGaussian() * 0.02D;
//            double d1 = this.rand.nextGaussian() * 0.02D;
//            double d2 = this.rand.nextGaussian() * 0.02D;
//            this.world.addParticle(particleType, this.getPosX() + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.getPosY() + 1.0D + (double) (this.rand.nextFloat() * this.height), this.getPosZ() + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2, new int[0]);
//        }
//    }
//
//    @Override
//    public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, ILivingEntityData livingdata)
//    {
//        livingdata = super.onInitialSpawn(difficulty, livingdata);
//        return livingdata;
//    }
//
//    @Override
//    public EntityAlienVillager createChild(AgeableEntity ageable)
//    {
//        EntityAlienVillager entityvillager = new EntityAlienVillager(this.world);
//        entityvillager.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityvillager)), (ILivingEntityData) null);
//        return entityvillager;
//    }
//
//    @Override
//    public World getWorld()
//    {
//        return this.world;
//    }
//
//    @Override
//    public BlockPos getPos()
//    {
//        return new BlockPos(this);
//    }
//
//    @Override
//    public boolean canBeLeashedTo(PlayerEntity player)
//    {
//        return false;
//    }
//
//    @Override
//    public void onStruckByLightning(LightningBoltEntity lightningBolt)
//    {
//        if (!this.world.isRemote && this.isAlive())
//        {
//            WitchEntity entitywitch = new EntityEvolvedWitch(this.world);
//            entitywitch.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
//            entitywitch.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entitywitch)), (ILivingEntityData) null);
//            entitywitch.setNoAI(this.isAIDisabled());
//
//            if (this.hasCustomName())
//            {
//                entitywitch.setCustomNameTag(this.getCustomNameTag());
//                entitywitch.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
//            }
//
//            this.world.addEntity(entitywitch);
//            this.remove();
//        }
//    }
//
//    public Inventory getVillagerInventory()
//    {
//        return this.villagerInventory;
//    }
//
//    @Override
//    protected void updateEquipmentIfNeeded(ItemEntity itemEntity)
//    {
//        ItemStack itemstack = itemEntity.getItem();
//        Item item = itemstack.getItem();
//
//        if (this.canVillagerPickupItem(item))
//        {
//            ItemStack itemstack1 = this.villagerInventory.addItem(itemstack);
//
//            if (itemstack1.isEmpty())
//            {
//                itemEntity.remove();
//            }
//            else
//            {
//                itemstack.setCount(itemstack1.getCount());
//            }
//        }
//    }
//
//    private boolean canVillagerPickupItem(Item itemIn)
//    {
//        return itemIn == Items.BREAD || itemIn == Items.POTATO || itemIn == Items.CARROT || itemIn == Items.WHEAT || itemIn == Items.WHEAT_SEEDS;
//    }
//
//    public boolean func_175553_cp()
//    {
//        return this.hasEnoughItems(1);
//    }
//
//    public boolean canAbondonItems()
//    {
//        return this.hasEnoughItems(2);
//    }
//
//    public boolean func_175557_cr()
//    {
//        return !this.hasEnoughItems(5);
//    }
//
//    private boolean hasEnoughItems(int multiplier)
//    {
//        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
//        {
//            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);
//
//            if (!itemstack.isEmpty())
//            {
//                if (itemstack.getItem() == Items.BREAD && itemstack.getCount() >= 3 * multiplier || itemstack.getItem() == Items.POTATO && itemstack.getCount() >= 12 * multiplier || itemstack.getItem() == Items.CARROT && itemstack.getCount() >= 12 * multiplier)
//                {
//                    return true;
//                }
//
//                if (itemstack.getItem() == Items.WHEAT && itemstack.getCount() >= 9 * multiplier)
//                {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean canBreath()
//    {
//        return true;
//    }
//
//    @Override
//    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn)
//    {
//        if (super.replaceItemInInventory(inventorySlot, itemStackIn))
//        {
//            return true;
//        }
//        else
//        {
//            int i = inventorySlot - 300;
//
//            if (i >= 0 && i < this.villagerInventory.getSizeInventory())
//            {
//                this.villagerInventory.setInventorySlotContents(i, itemStackIn);
//                return true;
//            }
//            else
//            {
//                return false;
//            }
//        }
//    }
//
//    public static class EmeraldForItems implements EntityAlienVillager.ITradeList
//    {
//        public ItemStack sellItem;
//        public EntityAlienVillager.PriceInfo price;
//
//        public EmeraldForItems(ItemStack itemStack, EntityAlienVillager.PriceInfo priceIn)
//        {
//            this.sellItem = itemStack;
//            this.price = priceIn;
//        }
//
//        @Override
//        public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random)
//        {
//            int i = 1;
//
//            if (this.price != null)
//            {
//                i = this.price.getPrice(random);
//            }
//            
//            ItemStack tradeStack = this.sellItem.copy();
//            tradeStack.setCount(i);
//
//            recipeList.add(new MerchantRecipe(tradeStack, new ItemStack(GCItems.itemBasicMoon, 1, 2)));
//        }
//    }
//
//    public interface ITradeList
//    {
//        void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random);
//    }
//
//    public static class ItemAndEmeraldToItem implements EntityAlienVillager.ITradeList
//    {
//        public ItemStack field_179411_a;
//        public EntityAlienVillager.PriceInfo field_179409_b;
//        public ItemStack field_179410_c;
//
//        public ItemAndEmeraldToItem(Item p_i45813_1_, EntityAlienVillager.PriceInfo p_i45813_2_, Item p_i45813_3_)
//        {
//            this.field_179411_a = new ItemStack(p_i45813_1_);
//            this.field_179409_b = p_i45813_2_;
//            this.field_179410_c = new ItemStack(p_i45813_3_);
//        }
//
//        public ItemAndEmeraldToItem(ItemStack p_i45813_1_, EntityAlienVillager.PriceInfo p_i45813_2_, ItemStack p_i45813_3_)
//        {
//            this.field_179411_a = p_i45813_1_;
//            this.field_179409_b = p_i45813_2_;
//            this.field_179410_c = p_i45813_3_;
//        }
//
//
//        @Override
//        public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random)
//        {
//            int i = 1;
//
//            if (this.field_179409_b != null)
//            {
//                i = this.field_179409_b.getPrice(random);
//            }
//
//            recipeList.add(new MerchantRecipe(this.field_179411_a.copy(), new ItemStack(GCItems.itemBasicMoon, i, 2), this.field_179410_c.copy()));
//        }
//    }
//
//    public static class ListItemForEmeralds implements EntityAlienVillager.ITradeList
//    {
//        public ItemStack itemToBuy;
//        public EntityAlienVillager.PriceInfo priceInfo;
//
//        public ListItemForEmeralds(ItemStack stack, EntityAlienVillager.PriceInfo priceInfo)
//        {
//            this.itemToBuy = stack;
//            this.priceInfo = priceInfo;
//        }
//
//        @Override
//        public void modifyMerchantRecipeList(MerchantRecipeList recipeList, Random random)
//        {
//            int i = 1;
//
//            if (this.priceInfo != null)
//            {
//                i = this.priceInfo.getPrice(random);
//            }
//
//            ItemStack itemstack;
//            ItemStack itemstack1;
//
//            if (i < 0)
//            {
//                itemstack = new ItemStack(GCItems.itemBasicMoon, 1, 2);
//                itemstack1 = new ItemStack(this.itemToBuy.getItem(), -i, this.itemToBuy.getMetadata());
//            }
//            else
//            {
//                itemstack = new ItemStack(GCItems.itemBasicMoon, i, 2);
//                itemstack1 = new ItemStack(this.itemToBuy.getItem(), 1, this.itemToBuy.getMetadata());
//            }
//
//            recipeList.add(new MerchantRecipe(itemstack, itemstack1));
//        }
//    }
//
//    public static class PriceInfo extends Tuple<Integer, Integer>
//    {
//        public PriceInfo(int p_i45810_1_, int p_i45810_2_)
//        {
//            super(Integer.valueOf(p_i45810_1_), Integer.valueOf(p_i45810_2_));
//        }
//
//        public int getPrice(Random rand)
//        {
//            return ((Integer) this.getFirst()).intValue() >= ((Integer) this.getSecond()).intValue() ? ((Integer) this.getFirst()).intValue() : ((Integer) this.getFirst()).intValue() + rand.nextInt(((Integer) this.getSecond()).intValue() - ((Integer) this.getFirst()).intValue() + 1);
//        }
//    }
//}