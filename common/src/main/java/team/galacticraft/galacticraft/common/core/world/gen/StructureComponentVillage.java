package team.galacticraft.galacticraft.common.core.world.gen;
//
//import team.galacticraft.galacticraft.common.core.entities.EntityAlienVillager;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.world.World;
//import net.minecraft.world.gen.feature.StructurePiece;
//import net.minecraft.world.gen.feature.template.TemplateManager;
//
//import java.util.List;
//import java.util.Random;
//
//public abstract class StructureComponentVillage extends StructurePiece
//{
//    static
//    {
//        try
//        {
//            MapGenVillageMoon.initiateStructures();
//        }
//        catch (Throwable e)
//        {
//
//        }
//    }
//
//    private int villagersSpawned;
//    protected StructureComponentVillageStartPiece startPiece;
//
//    public StructureComponentVillage()
//    {
//    }
//
//    protected StructureComponentVillage(StructureComponentVillageStartPiece par1ComponentVillageStartPiece, int par2)
//    {
//        super(par2);
//        this.startPiece = par1ComponentVillageStartPiece;
//    }
//
//    @Override
//    protected void writeStructureToNBT(CompoundNBT nbttagcompound)
//    {
//        nbttagCompound.putInt("VCount", this.villagersSpawned);
//    }
//
//    @Override
//    protected void readStructureFromNBT(CompoundNBT nbt, TemplateManager manager)
//    {
//        this.villagersSpawned = nbt.getInt("VCount");
//    }
//
//    protected StructurePiece getNextComponentNN(StructureComponentVillageStartPiece par1ComponentVillageStartPiece, List<StructurePiece> par2List, Random par3Random, int par4, int par5)
//    {
//        switch (this.getCoordBaseMode().getHorizontalIndex())
//        {
//        case 0:
//            return StructureVillagePiecesMoon.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, Direction.byHorizontalIndex(1), this.getComponentType());
//        case 1:
//            return StructureVillagePiecesMoon.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, Direction.byHorizontalIndex(2), this.getComponentType());
//        case 2:
//            return StructureVillagePiecesMoon.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX - 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, Direction.byHorizontalIndex(1), this.getComponentType());
//        case 3:
//            return StructureVillagePiecesMoon.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.minZ - 1, Direction.byHorizontalIndex(2), this.getComponentType());
//        default:
//            return null;
//        }
//    }
//
//    protected StructurePiece getNextComponentPP(StructureComponentVillageStartPiece par1ComponentVillageStartPiece, List<StructurePiece> par2List, Random par3Random, int par4, int par5)
//    {
//        switch (this.getCoordBaseMode().getHorizontalIndex())
//        {
//        case 0:
//            return StructureVillagePiecesMoon.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, Direction.byHorizontalIndex(3), this.getComponentType());
//        case 1:
//            return StructureVillagePiecesMoon.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, Direction.byHorizontalIndex(0), this.getComponentType());
//        case 2:
//            return StructureVillagePiecesMoon.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.maxX + 1, this.boundingBox.minY + par4, this.boundingBox.minZ + par5, Direction.byHorizontalIndex(3), this.getComponentType());
//        case 3:
//            return StructureVillagePiecesMoon.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, this.boundingBox.minX + par5, this.boundingBox.minY + par4, this.boundingBox.maxZ + 1, Direction.byHorizontalIndex(0), this.getComponentType());
//        default:
//            return null;
//        }
//    }
//
//    protected int getAverageGroundLevel(World world, MutableBoundingBox boundingBox)
//    {
//        int i = 0;
//        int j = 0;
//        BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();
//
//        for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k)
//        {
//            for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l)
//            {
//                mutableBlockPos.setPos(l, 64, k);
//
//                if (boundingBox.isVecInside(mutableBlockPos))
//                {
//                    i += world.getTopSolidOrLiquidBlock(mutableBlockPos).getY();
//                    ++j;
//                }
//            }
//        }
//
//        if (j == 0)
//        {
//            return -1;
//        }
//        else
//        {
//            return i / j;
//        }
//    }
//
//    protected static boolean canVillageGoDeeper(MutableBoundingBox par0StructureBoundingBox)
//    {
//        return par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10;
//    }
//
//    protected void spawnVillagers(World par1World, MutableBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6)
//    {
//        if (this.villagersSpawned < par6)
//        {
//            for (int var7 = this.villagersSpawned; var7 < par6; ++var7)
//            {
//                int var8 = this.getXWithOffset(par3 + var7, par5);
//                final int var9 = this.getYWithOffset(par4);
//                int var10 = this.getZWithOffset(par3 + var7, par5);
//
//                var8 += par1World.rand.nextInt(3) - 1;
//                var10 += par1World.rand.nextInt(3) - 1;
//
//                if (!par2StructureBoundingBox.isVecInside(new BlockPos(var8, var9, var10)))
//                {
//                    break;
//                }
//
//                ++this.villagersSpawned;
//                final EntityAlienVillager var11 = new EntityAlienVillager(par1World);
//                var11.setLocationAndAngles(var8 + 0.5D, var9, var10 + 0.5D, 0.0F, 0.0F);
//                par1World.addEntity(var11);
//            }
//        }
//    }
//}