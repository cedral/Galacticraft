package team.galacticraft.galacticraft.common.core.dimension;

import team.galacticraft.galacticraft.common.api.galaxies.CelestialBody;
import team.galacticraft.galacticraft.common.api.prefab.world.gen.DimensionSpace;
import team.galacticraft.galacticraft.common.api.world.IGalacticraftDimension;
import team.galacticraft.galacticraft.common.api.world.ISolarLevel;
import team.galacticraft.galacticraft.common.core.GalacticraftCore;
import team.galacticraft.galacticraft.common.core.dimension.chunk.MoonChunkGenerator;
import team.galacticraft.galacticraft.common.core.dimension.chunk.MoonGenSettings;
import team.galacticraft.galacticraft.common.core.event.EventHandlerGC;
import team.galacticraft.galacticraft.common.core.world.gen.BiomeMoonHills;
import team.galacticraft.galacticraft.common.core.world.gen.dungeon.RoomTreasure;
import team.galacticraft.galacticraft.planets.venus.dimension.VenusBiomeProvider;
import team.galacticraft.galacticraft.planets.venus.dimension.VenusBiomeProviderSettings;
import team.galacticraft.galacticraft.planets.venus.dimension.VenusBiomeProviderTypes;
import team.galacticraft.galacticraft.planets.venus.world.gen.VenusChunkGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSourceType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class DimensionMoon extends DimensionSpace implements IGalacticraftDimension, ISolarLevel
{
    public DimensionMoon(Level worldIn, DimensionType typeIn)
    {
        super(worldIn, typeIn, 0.0F);
    }

//    @Override
//    public DimensionType getDimensionType()
//    {
//        return GCDimensions.MOON;
//    }

    @Override
    public Vec3 getFogColor(float celestialAngle, float partialTicks)
    {
        return new Vec3(0, 0, 0);
    }


//    @Override
//    public Vector3 getFogColor()
//    {
//        return new Vector3(0, 0, 0);
//    }
//
//    @Override
//    public Vector3 getSkyColor()
//    {
//        return new Vector3(0, 0, 0);
//    }

    @Override
    public boolean hasSunset()
    {
        return false;
    }

    @Override
    public long getDayLength()
    {
        return 192000L;
    }

    @Override
    public ChunkGenerator createRandomLevelGenerator()
    {
        MoonGenSettings settings = new MoonGenSettings();
        BiomeSourceType<MoonBiomeProviderSettings, MoonBiomeProvider> type = MoonBiomeProviderTypes.MOON_TYPE;
        MoonBiomeProviderSettings providerSettings = type.createSettings(level.getLevelData()).setGeneratorSettings(settings);
        return new MoonChunkGenerator(this.level, type.create(providerSettings), settings);
    }

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public float getStarBrightness(float par1)
//    {
//        final float var2 = this.world.getCelestialAngle(par1);
//        float var3 = 1.0F - (MathHelper.cos(var2 * Constants.TWO_PI) * 2.0F + 0.25F);
//
//        if (var3 < 0.0F)
//        {
//            var3 = 0.0F;
//        }
//
//        if (var3 > 1.0F)
//        {
//            var3 = 1.0F;
//        }
//
//        return var3 * var3 * 0.5F + 0.3F;
//    }

    @Override
    public boolean hasGround()
    {
        return false;
    }

//    @Override
//    public double getHorizon()
//    {
//        return 44.0D;
//    }

    @Override
    public int getSeaLevel()
    {
        return 68;
    }

    @Override
    @Nullable
    public BlockPos getSpawnPosInChunk(ChunkPos chunkPosIn, boolean checkValid)
    {
        for (int i = chunkPosIn.getMinBlockX(); i <= chunkPosIn.getMaxBlockX(); ++i)
        {
            for (int j = chunkPosIn.getMinBlockZ(); j <= chunkPosIn.getMaxBlockZ(); ++j)
            {
                BlockPos blockpos = this.getValidSpawnPosition(i, j, checkValid);
                if (blockpos != null)
                {
                    return blockpos;
                }
            }
        }

        return null;
    }

    @Override
    @Nullable
    public BlockPos getValidSpawnPosition(int posX, int posZ, boolean checkValid)
    {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(posX, 0, posZ);
        Biome biome = this.level.getBiome(blockpos$mutableblockpos);
        BlockState blockstate = biome.getSurfaceBuilderConfig().getTopMaterial();
        if (checkValid && !blockstate.getBlock().is(BlockTags.VALID_SPAWN))
        {
            return null;
        }
        else
        {
            LevelChunk chunk = this.level.getChunk(posX >> 4, posZ >> 4);
            int i = chunk.getHeight(Heightmap.Types.MOTION_BLOCKING, posX & 15, posZ & 15);
            if (i < 0)
            {
                return null;
            }
            else if (chunk.getHeight(Heightmap.Types.WORLD_SURFACE, posX & 15, posZ & 15) > chunk.getHeight(Heightmap.Types.OCEAN_FLOOR, posX & 15, posZ & 15))
            {
                return null;
            }
            else
            {
                for (int j = i + 1; j >= 0; --j)
                {
                    blockpos$mutableblockpos.set(posX, j, posZ);
                    BlockState blockstate1 = this.level.getBlockState(blockpos$mutableblockpos);
                    if (!blockstate1.getFluidState().isEmpty())
                    {
                        break;
                    }

                    if (blockstate1.equals(blockstate))
                    {
                        return blockpos$mutableblockpos.above().immutable();
                    }
                }

                return null;
            }
        }
    }

    //Overriding  so that beds do not explode on Moon
    @Override
    public boolean mayRespawn()
    {
        if (EventHandlerGC.bedActivated)
        {
            EventHandlerGC.bedActivated = false;
            return true;
        }
        return false;
    }

    @Override
    public float getGravity()
    {
        return 0.062F;
    }

    @Override
    public double getFuelUsageMultiplier()
    {
        return 0.7D;
    }

    @Override
    public double getSolarEnergyMultiplier()
    {
        return 1.4D;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier)
    {
        return tier > 0;
    }

    @Override
    public float getFallDamageModifier()
    {
        return 0.18F;
    }

    @Override
    public CelestialBody getCelestialBody()
    {
        return GalacticraftCore.moonMoon;
    }

    @Override
    public int getDungeonSpacing()
    {
        return 704;
    }

    @Override
    public ResourceLocation getDungeonChestType()
    {
        return RoomTreasure.MOONCHEST;
    }

    @Override
    public boolean isFoggyAt(int x, int z)
    {
        return false;
    }
}