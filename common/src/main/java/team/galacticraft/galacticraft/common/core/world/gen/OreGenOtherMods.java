package team.galacticraft.galacticraft.common.core.world.gen;
//
//import team.galacticraft.galacticraft.common.api.event.wgen.GCCoreEventPopulate;
//import team.galacticraft.galacticraft.common.api.vector.BlockTuple;
//import team.galacticraft.galacticraft.common.api.world.IGalacticraftWorldProvider;
//import team.galacticraft.galacticraft.common.core.GCBlocks;
//import team.galacticraft.galacticraft.common.core.GalacticraftCore;
//import team.galacticraft.galacticraft.common.core.dimension.DimensionMoon;
//import team.galacticraft.galacticraft.common.core.dimension.DimensionSpaceStation;
//import team.galacticraft.galacticraft.common.core.util.ConfigManagerCore;
//import team.galacticraft.galacticraft.common.core.util.GCLog;
//import team.galacticraft.galacticraft.planets.mars.blocks.MarsBlocks;
//import team.galacticraft.galacticraft.planets.mars.dimension.WorldProviderMars;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.pattern.BlockMatcher;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraft.world.dimension.Dimension;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.OreFeature;
//import net.minecraft.world.gen.feature.OreFeatureConfig;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//public class OreGenOtherMods
//{
//    private World world;
//    private Random randomGenerator;
//
//    private BlockPos pos;
//
//    private Feature oreGen;
//    public static ArrayList<OreGenData> data = new ArrayList<OreGenData>();
//
//    static
//    {
//        for (final String str : ConfigManagerCore.oregenIDs.get())
//        {
//            try
//            {
//                int slash = str.indexOf('/');
//                String s;
//                int rarity = 0;  //0 = common  1 = uncommon  2 = rare
//                int depth = 0;   //0 = even   1 = deep   2 = shallow
//                int size = 1;    //0 = single 1 = standard 2 = large
//                boolean extraRandom = false;
//                int dim = 0;
//
//                if (slash >= 0)
//                {
//                    s = str.substring(0, slash).trim();
//                    String params = str.substring(slash).toUpperCase();
//                    if (params.contains("UNCOMMON"))
//                    {
//                        rarity = 1;
//                    }
//                    else if (params.contains("RARE"))
//                    {
//                        rarity = 2;
//                    }
//
//                    if (params.contains("DEEP"))
//                    {
//                        depth = 1;
//                    }
//                    else if (params.contains("SHALLOW"))
//                    {
//                        depth = 2;
//                    }
//
//                    if (params.contains("SINGLE"))
//                    {
//                        size = 0;
//                    }
//                    else if (params.contains("LARGE"))
//                    {
//                        size = 2;
//                    }
//
//                    if (params.contains("XTRARANDOM"))
//                    {
//                        extraRandom = true;
//                    }
//
//                    if (params.contains("ONLYMOON"))
//                    {
//                        dim = 1;
//                    }
//                    else if (params.contains("ONLYMARS"))
//                    {
//                        dim = 2;
//                    }
//
//                }
//                else
//                {
//                    s = str;
//                }
//
//                BlockTuple bt = ConfigManagerCore.stringToBlock.get()(s, "Other mod ore generate IDs", true);
//                if (bt == null)
//                {
//                    continue;
//                }
//
//                int meta = bt.meta;
//                if (meta == -1)
//                {
//                    meta = 0;
//                }
//
//                OreGenOtherMods.addOre(bt.block, meta, rarity, depth, size, extraRandom, dim);
//            }
//            catch (final Exception e)
//            {
//                GCLog.severe("[config] External Sealable IDs: error parsing '" + str + "'. Must be in the form Blockname or BlockName:metadata followed by / parameters ");
//            }
//        }
//    }
//
//    public static void addOre(Block block, int meta, int rarity, int depth, int clumpSize, boolean extraRandom, int dim)
//    {
//        int clusters = 12;
//        int size = 4;
//        int min = 0;
//        int max = 64;
//
//        switch (depth)
//        {
//        case 0:
//            //Evenly distributed
//            size = 6;
//            clusters = 20;
//            max = 80;
//            if (rarity == 1)
//            {
//                clusters = 9;
//                size = 4;
//            }
//            else if (rarity == 2)
//            {
//                clusters = 6;
//                size = 3;
//                max = 96;
//            }
//            break;
//        case 1:
//            //Deep
//            size = 5;
//            clusters = 12;
//            max = 32;
//            if (rarity == 1)
//            {
//                clusters = 6;
//                size = 4;
//                max = 20;
//            }
//            else if (rarity == 2)
//            {
//                clusters = 2;
//                size = 3;
//                max = 16;
//            }
//            break;
//        case 2:
//            //Shallow
//            size = 6;
//            clusters = 15;
//            min = 32;
//            max = 80;
//            if (rarity == 1)
//            {
//                clusters = 8;
//                size = 4;
//                min = 32;
//                max = 72;
//            }
//            else if (rarity == 2)
//            {
//                clusters = 3;
//                size = 3;
//                min = 40;
//                max = 64;
//            }
//        }
//
//        if (clumpSize == 0)
//        {
//            size = 1;
//            clusters = (3 * clusters) / 2;
//        }
//        else if (clumpSize == 2)
//        {
//            size *= 4;
//            clusters /= 2;
//        }
//
//        if (extraRandom)
//        {
//            if (depth == 1)
//            {
//                min = -max * 3;
//            }
//            else
//            {
//                max *= 4;
//            }
//        }
//
//        OreGenData ore = new OreGenData(block, meta, clusters, size, min, max, dim);
//        OreGenOtherMods.data.add(ore);
//    }
//
//    @SubscribeEvent
//    public void onPlanetDecorated(GCCoreEventPopulate.Post event)
//    {
//        this.world = event.world;
//        this.randomGenerator = event.rand;
//        this.pos = event.pos;
//
//        int dimDetected = 0;
//
//        Dimension prov = world.getDimension();
//        if (!(prov instanceof IGalacticraftWorldProvider) || (prov instanceof DimensionSpaceStation))
//        {
//            return;
//        }
//
//        Block stoneBlock = null;
////        int stoneMeta = 0;
//
//        if (prov instanceof DimensionMoon)
//        {
//            stoneBlock = GCBlocks.moonStone;
////            stoneMeta = 4;
//            dimDetected = 1;
//        }
//        else if (GalacticraftCore.isPlanetsLoaded && prov instanceof WorldProviderMars)
//        {
////            stoneBlock = MarsBlocks.marsBlock;
////            stoneMeta = 9;
//            dimDetected = 2;
//        }
//
//        if (stoneBlock == null)
//        {
//            return;
//        }
//
//        for (OreGenData ore : OreGenOtherMods.data)
//        {
//            if (ore.dimRestrict == 0 || ore.dimRestrict == dimDetected)
//            {
//                String value = ore.oreBlock.getRegistryName().getPath();
//                OreFeatureConfig.FillerBlockType type = OreFeatureConfig.FillerBlockType.create(value.toUpperCase(), value, new BlockMatcher(stoneBlock));
//                OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(type, ore.oreBlock, ore.sizeCluster);
//                this.oreGen = new OreFeature(OreFeatureConfig::deserialize);
////                this.oreGen = new WorldGenMinableMeta(ore.oreBlock, ore.sizeCluster, true, stoneBlock);
//                this.genStandardOre1(ore.numClusters, this.oreGen, ore.minHeight, ore.maxHeight);
//            }
//        }
//    }
//
//    void genStandardOre1(int amountPerChunk, Feature worldGenerator, int minY, int maxY)
//    {
//        for (int var5 = 0; var5 < amountPerChunk; ++var5)
//        {
//            BlockPos blockpos = this.pos.add(this.randomGenerator.nextInt(16), this.randomGenerator.nextInt(maxY - minY) + minY, this.randomGenerator.nextInt(16));
//            worldGenerator.generate(this.world, this.randomGenerator, blockpos);
//        }
//    }
//
//    public static class OreGenData
//    {
//        public BlockState oreBlock;
////        public int oreMeta = 0;
//        public int sizeCluster = 4;
//        public int numClusters = 8;
//        public int minHeight = 0;
//        public int maxHeight = 128;
//        public int dimRestrict = 0;
//
//        public OreGenData(BlockState block, int num, int cluster, int min, int max, int dim)
//        {
//            this.oreBlock = block;
////            this.oreMeta = meta;
//            this.sizeCluster = cluster;
//            this.numClusters = num;
//            this.minHeight = min;
//            this.maxHeight = max;
//            this.dimRestrict = dim;
//        }
//
//        public OreGenData(BlockState block, int num, int cluster)
//        {
//            this.oreBlock = block;
////            this.oreMeta = meta;
//            this.sizeCluster = cluster;
//            this.numClusters = num;
//            this.minHeight = 0;
//            this.maxHeight = 128;
//        }
//
//        public OreGenData(BlockState block, int num)
//        {
//            this.oreBlock = block;
////            this.oreMeta = meta;
//            this.sizeCluster = 4;
//            this.numClusters = num;
//            this.minHeight = 0;
//            this.maxHeight = 128;
//        }
//    }
//}