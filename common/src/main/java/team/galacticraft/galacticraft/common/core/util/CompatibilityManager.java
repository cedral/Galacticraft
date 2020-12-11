package team.galacticraft.galacticraft.common.core.util;

import team.galacticraft.galacticraft.common.core.GCBlocks;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelType;
import net.minecraftforge.fml.ModList;


public class CompatibilityManager
{
    public final static String modidIC2 = "ic2";
    public final static String modidMekanism = "mekanism";
    public final static String modidBuildcraft = "buildcraftcore";
    public final static String modBCraftTransport = "buildcrafttransport";
    public final static String modBCraftEnergy = "buildcraftenergy";

    public static boolean PlayerAPILoaded = ModList.get().isLoaded("PlayerAPI");
    public static boolean RenderPlayerAPILoaded = ModList.get().isLoaded("RenderPlayerAPI");

    public static boolean modJEILoaded = ModList.get().isLoaded("jei");
    private static final boolean modIc2Loaded = ModList.get().isLoaded(modidIC2);
    private static boolean modICClassicLoaded;
    public static boolean modBCraftLoaded = ModList.get().isLoaded(modidBuildcraft);
    private static final boolean modBCraftEnergyLoaded = ModList.get().isLoaded(modBCraftEnergy);
    private static boolean modBCraftTransportLoaded;
    private static boolean modGTLoaded;
    private static final boolean modTELoaded = ModList.get().isLoaded("thermalexpansion");
    private static final boolean modMekLoaded = ModList.get().isLoaded(modidMekanism);
    private static boolean modAetherIILoaded;
    private static boolean modBasicComponentsLoaded;
    private static boolean modAppEngLoaded;
    private static boolean modPneumaticCraftLoaded;
    private static final boolean modBOPLoaded = ModList.get().isLoaded("biomesoplenty");
    private static final boolean modEIOLoaded = ModList.get().isLoaded("enderio");
    public static boolean modAALoaded = ModList.get().isLoaded("actuallyadditions");
    private static boolean spongeLoaded;
    private static boolean modMatterOverdriveLoaded;
    private static boolean wailaLoaded;
    public static boolean isMFRLoaded = ModList.get().isLoaded("minefactoryreloaded");
    public static boolean isSmartMovingLoaded = ModList.get().isLoaded("smartmoving");
    public static boolean isTConstructLoaded = ModList.get().isLoaded("tconstruct");
    public static boolean isWitcheryLoaded = ModList.get().isLoaded("witchery");
    public static boolean isCubicChunksLoaded;
//    public static int isBG2Loaded = 0;

    public static Class<?> classGTOre = null;
    private static Method spongeOverrideSet = null;
    private static Method spongeOverrideGet = null;
    public static Class classBCTransport;
    public static Class classBCTransportPipeTile;
    public static Class classBOPWorldType = null;
    public static Class classBOPws = null;
    public static Class classBOPwcm = null;
    public static Class classIC2wrench = null;
    public static Class classIC2wrenchElectric = null;
    public static Class classIC2tileEventLoad;
    public static Class classIC2tileEventUnload;
    public static Field fieldIC2tickhandler;
    public static Field fieldIC2networkManager;
    public static Class classIc2ClassicNetworkManager;
    public static Class classIC2cableType = null;
    public static Constructor constructorIC2cableTE = null;
    private static Method androidPlayerGet;
    private static Method androidPlayerIsAndroid;
    public static Class classIc2ClassicTileCable;

    public static void checkForCompatibleMods()
    {
        if (ModList.get().isLoaded("gregtech") || ModList.get().isLoaded("gregtech_addon"))
        {
            CompatibilityManager.modGTLoaded = true;
        }

        if (CompatibilityManager.modMekLoaded)
        {
            GCLog.info("Galacticraft: activating Mekanism compatibility.");
        }

        if (CompatibilityManager.isMFRLoaded)
        {
            GCLog.info("Galacticraft: activating MFR compatibility feature.");
        }

        if (CompatibilityManager.modTELoaded)
        {
            GCLog.info("Galacticraft: activating ThermalExpansion compatibility features.");
        }

        if (CompatibilityManager.isTConstructLoaded)
        {
            GCLog.info("Galacticraft: activating Tinker's Construct compatibility features.");
        }

        if (CompatibilityManager.modIc2Loaded)
        {
            try
            {
                classIc2ClassicTileCable = Class.forName("ic2.core.block.wiring.tile.TileEntityCable");
                classIc2ClassicNetworkManager = Class.forName("ic2.core.network.NetworkManager");
                modICClassicLoaded = true;
            }
            catch (ClassNotFoundException e)
            {
                //Silent error - there will always be an Exception here if IC2 (not IC2 Classic) is present.
            }

            if (!modICClassicLoaded)
            {
                // Ic2 loaded
                try
                {
                    classIC2cableType = Class.forName("ic2.core.block.wiring.CableType");
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }

            try
            {
                try
                {
                    classIC2wrench = Class.forName("ic2.core.item.tool.ItemToolWrench");
                }
                catch (ClassNotFoundException e)
                {
                }

                try
                {
                    classIC2wrenchElectric = Class.forName("ic2.core.item.tool.ItemToolWrenchElectric");
                }
                catch (ClassNotFoundException e)
                {
                }

                try
                {
                    classIC2tileEventLoad = Class.forName("ic2.api.energy.event.EnergyTileLoadEvent");
                    classIC2tileEventUnload = Class.forName("ic2.api.energy.event.EnergyTileUnloadEvent");
                }
                catch (ClassNotFoundException e)
                {
                }

                try
                {
                    Class clazzIC2 = Class.forName("ic2.core.IC2");
                    fieldIC2networkManager = clazzIC2.getDeclaredField("network");
                    fieldIC2tickhandler = clazzIC2.getDeclaredField("tickHandler");
                }
                catch (Exception e)
                {
                }

                Class classIC2cable;
                try
                {
                    classIC2cable = Class.forName("ic2.core.block.wiring.TileEntityCable");
                }
                catch (ClassNotFoundException e)
                {
                    try
                    {
                        classIC2cable = Class.forName("ic2.core.block.wiring.tile.TileEntityCable");
                    }
                    catch (Exception e1)
                    {
                        classIC2cable = null;
                    }
                }
                if (classIC2cable != null)
                {
//                    try
//                    {
//                        BlockEnclosed.onBlockNeighbourChangeIC2a = classIC2cable.getMethod("onNeighborChange", Block.class);
//                    }
//                    catch (Exception e)
//                    {
//                        try
//                        {
//                            BlockEnclosed.onBlockNeighbourChangeIC2b = classIC2cable.getMethod("onNeighborChange", Block.class, BlockPos.class);
//                        }
//                        catch (Exception e1)
//                        {
//                            BlockEnclosed.onBlockNeighbourChangeIC2a = classIC2cable.getMethod("onBlockUpdate", Block.class);
//                        }
//                    } TODO IC2 support

                    Constructor<?>[] constructors = classIC2cable.getDeclaredConstructors();
                    for (Constructor<?> constructor2 : constructors)
                    {
                        if (constructor2.getGenericParameterTypes().length == 2)
                        {
                            constructorIC2cableTE = constructor2;
                            break;
                        }
                    }
                }
                GCLog.info("Galacticraft: activating IndustrialCraft2 compatibility features.");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        if (ModList.get().isLoaded(modBCraftTransport))
        {
            CompatibilityManager.modBCraftTransportLoaded = true;

            try
            {
                classBCTransport = Class.forName("buildcraft.transport.BCTransportItems");
                classBCTransportPipeTile = Class.forName("buildcraft.transport.tile.TilePipeHolder");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

//            BlockEnclosed.initialiseBC(); TODO BC Support

            if (CompatibilityManager.classBCTransportPipeTile == null)
            {
                CompatibilityManager.modBCraftTransportLoaded = false;
            }
            else
            {
                GCLog.info("Galacticraft: activating BuildCraft Transport (Pipes) compatibility features.");
            }
        }

        if (CompatibilityManager.modBOPLoaded)
        {
            try
            {
                classBOPWorldType = Class.forName("biomesoplenty.common.world.WorldTypeBOP");
                classBOPws = Class.forName("biomesoplenty.common.world.BOPWorldSettings");
                classBOPwcm = Class.forName("biomesoplenty.common.world.BiomeProviderBOP");
                GCLog.info("Galacticraft: activating Biomes O'Plenty compatibility feature.");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if (ModList.get().isLoaded("aetherii"))
        {
            CompatibilityManager.modAetherIILoaded = true;
            GCLog.info("Galacticraft: activating AetherII compatibility feature.");
        }

        if (ModList.get().isLoaded("basiccomponents"))
        {
            CompatibilityManager.modBasicComponentsLoaded = true;
        }

        if (ModList.get().isLoaded("appliedenergistics2"))
        {
            CompatibilityManager.modAppEngLoaded = true;
            GCLog.info("Galacticraft: activating AppliedEnergistics2 compatibility features.");
        }

        if (ModList.get().isLoaded("pneumaticcraft"))
        {
            CompatibilityManager.modPneumaticCraftLoaded = true;
            GCLog.info("Galacticraft: activating PneumaticCraft compatibility features.");
        }

        if (ModList.get().isLoaded("waila"))
        {
            CompatibilityManager.wailaLoaded = true;
            GCLog.info("Galacticraft: activating WAILA compatibility features.");
        }

        if (ModList.get().isLoaded("sponge"))
        {
            try
            {
                Class clazz = Class.forName("org.spongepowered.common.interfaces.world.gen.IMixinChunkProviderServer");
                spongeOverrideSet = clazz.getMethod("setForceChunkRequests", boolean.class);
                spongeOverrideGet = clazz.getMethod("getForceChunkRequests");
                spongeLoaded = true;
            }
            catch (Exception e)
            {
                try
                {
                    Class clazz = Class.forName("org.spongepowered.common.bridge.world.chunk.ChunkProviderServerBridge");
                    spongeOverrideSet = clazz.getMethod("bridge$setForceChunkRequests", boolean.class);
                    spongeOverrideGet = clazz.getMethod("bridge$getForceChunkRequests");
                    spongeLoaded = true;
                }
                catch (Exception enew)
                {
                    enew.printStackTrace();
                }
            }
        }

        if (ModList.get().isLoaded("cubicchunks"))
        {
            CompatibilityManager.isCubicChunksLoaded = true;
        }

//TODO      
//        //Compatibility with BattleGear2 - was used by RenderEvolvedSkeleton in 1.7.10
//        try
//        {
//            Class<?> clazz = Class.forName("mods.battlegear2.MobHookContainerClass");
//
//            //accessing this: public static final int Skell_Arrow_Datawatcher = 25;
//            CompatibilityManager.isBG2Loaded = clazz.getField("Skell_Arrow_Datawatcher").getInt(null);
//        }
//        catch (Exception e)
//        {
//        }

        if (ModList.get().isLoaded("matteroverdrive"))
        {
            try
            {
                Class<?> androidPlayer = Class.forName("matteroverdrive.entity.android_player.AndroidPlayer");
                Class<?> androidCapability = Class.forName("matteroverdrive.entity.player.MOPlayerCapabilityProvider");
                CompatibilityManager.androidPlayerGet = androidCapability.getMethod("GetAndroidCapability", Entity.class);
                CompatibilityManager.androidPlayerIsAndroid = androidPlayer.getMethod("isAndroid");
                CompatibilityManager.modMatterOverdriveLoaded = true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static boolean isIc2Loaded()
    {
        return CompatibilityManager.modIc2Loaded;
    }

    public static boolean isIc2ClassicLoaded()
    {
        return CompatibilityManager.modICClassicLoaded;
    }

    public static boolean isBCraftTransportLoaded()
    {
        return CompatibilityManager.modBCraftTransportLoaded;
    }

    public static boolean isBCraftEnergyLoaded()
    {
        return CompatibilityManager.modBCraftEnergyLoaded;
    }

    public static boolean isTELoaded()
    {
        return CompatibilityManager.modTELoaded;
    }

    public static boolean isMekanismLoaded()
    {
        return CompatibilityManager.modMekLoaded;
    }

    public static boolean isGTLoaded()
    {
        return CompatibilityManager.modGTLoaded;
    }

    public static boolean isAIILoaded()
    {
        return CompatibilityManager.modAetherIILoaded;
    }

    public static boolean isBCLoaded()
    {
        return CompatibilityManager.modBasicComponentsLoaded;
    }

    public static boolean isAppEngLoaded()
    {
        return CompatibilityManager.modAppEngLoaded;
    }

    public static boolean isBOPLoaded()
    {
        return CompatibilityManager.modBOPLoaded;
    }

    public static boolean isBOPWorld(LevelType worldType)
    {
        if (modBOPLoaded && classBOPWorldType != null && classBOPws != null && classBOPwcm != null)
        {
            return classBOPWorldType.isInstance(worldType);
        }
        return false;
    }

    public static boolean isPneumaticCraftLoaded()
    {
        return CompatibilityManager.modPneumaticCraftLoaded;
    }

    public static boolean isWailaLoaded()
    {
        return CompatibilityManager.wailaLoaded;
    }

    public static void spongeOverrideStart(ServerLevel w)
    {
    }

    public static boolean forceLoadChunks(ServerLevel w)
    {
        Boolean spongeForceChunksPrevious = null;
        if (spongeLoaded)
        {
            ServerChunkCache cps = w.getChunkSource();
            try
            {
                spongeForceChunksPrevious = (Boolean) spongeOverrideGet.invoke(cps);
                spongeOverrideSet.invoke(cps, true);
            }
            catch (Exception ignore)
            {
            }
        }
        return Boolean.TRUE.equals(spongeForceChunksPrevious);
    }

    public static void forceLoadChunksEnd(ServerLevel w, boolean previous)
    {
        if (spongeLoaded)
        {
            try
            {
                spongeOverrideSet.invoke(w.getChunkSource(), previous);
            }
            catch (Exception ignore)
            {
            }
        }
    }

//    public static void registerMicroBlocks()
//    {
//        try
//        {
//            Class clazz = Class.forName("codechicken.microblock.MicroMaterialRegistry");
//            if (clazz != null)
//            {
//                Method registerMethod = null;
//                Method[] methodz = clazz.getMethods();
//                for (Method m : methodz)
//                {
//                    if (m.getName().equals("registerMaterial"))
//                    {
//                        registerMethod = m;
//                        break;
//                    }
//                }
//                Class<?> clazzbm = Class.forName("codechicken.microblock.BlockMicroMaterial");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.basicBlock, 3), "tile.gcBlockCore.decoblock1");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.basicBlock, 4), "tile.gcBlockCore.decoblock2");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.basicBlock, 9), "tile.gcBlockCore.copperBlock");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.basicBlock, 10), "tile.gcBlockCore.tinBlock");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.basicBlock, 11), "tile.gcBlockCore.aluminumBlock");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.basicBlock, 12), "tile.gcBlockCore.meteorironBlock");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.moonDirt, 3), "tile.moonBlock.moondirt");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.moonStone, 4), "tile.moonBlock.moonstone");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.moonTurf, 5), "tile.moonBlock.moongrass");
//                registerMethod.invoke(null, clazzbm.getConstructor(Block.class, int.class).newInstance(GCBlocks.moonDungeonBrick, 14), "tile.moonBlock.bricks");
//                GCLog.info("Galacticraft: activating CodeChicken Microblocks compatibility.");
//            }
//        }
//        catch (Exception e)
//        {
//        }
//    }

    public static boolean isAndroid(Player player)
    {
        if (CompatibilityManager.modMatterOverdriveLoaded)
        {
//          Equivalent to:
//            AndroidPlayer androidPlayer = AndroidPlayer.get(player);
//            return (androidPlayer != null && androidPlayer.isAndroid());
            try
            {
                Object androidPlayer = CompatibilityManager.androidPlayerGet.invoke(null, player);
                if (androidPlayer != null)
                {
                    return (Boolean) CompatibilityManager.androidPlayerIsAndroid.invoke(androidPlayer);
                }
            }
            catch (Exception ignore)
            {
            }
        }
        return false;
    }

    public static boolean useAluDust()
    {
        return modIc2Loaded || modAppEngLoaded || modTELoaded || modEIOLoaded || modAALoaded;
    }
}
