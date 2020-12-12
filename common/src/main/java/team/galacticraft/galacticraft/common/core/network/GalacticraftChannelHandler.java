package team.galacticraft.galacticraft.common.core.network;

import team.galacticraft.galacticraft.common.Constants;
import team.galacticraft.galacticraft.common.core.GalacticraftCore;
import team.galacticraft.galacticraft.planets.asteroids.network.PacketSimpleAsteroids;
import team.galacticraft.galacticraft.planets.mars.network.PacketSimpleMars;
import team.galacticraft.galacticraft.planets.venus.network.PacketSimpleVenus;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class GalacticraftChannelHandler
{
    private static int messageID = 0;

    private static int getUniqueId()
    {
        return messageID++;
    }

    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Constants.MOD_ID_CORE, "main"))
            .clientAcceptedVersions(getProtocolVersion()::equals)
            .serverAcceptedVersions(getProtocolVersion()::equals)
            .networkProtocolVersion(GalacticraftChannelHandler::getProtocolVersion).simpleChannel();

    private static String getProtocolVersion()
    {
        return GalacticraftCore.instance == null ? "999.999.999" : GalacticraftCore.instance.versionNumber.toString();
    }

    public GalacticraftChannelHandler()
    {
        INSTANCE.registerMessage(getUniqueId(), PacketSimple.class, PacketSimple::encode, PacketSimple::decode, PacketSimple::handle);
//        INSTANCE.registerMessage(getUniqueId(), PacketRotateRocket.class, PacketRotateRocket::encode, PacketRotateRocket::decode, PacketRotateRocket::handle);
        INSTANCE.registerMessage(getUniqueId(), PacketDynamic.class, PacketDynamic::encode, PacketDynamic::decode, PacketDynamic::handle);
        INSTANCE.registerMessage(getUniqueId(), PacketFluidNetworkUpdate.class, PacketFluidNetworkUpdate::encode, PacketFluidNetworkUpdate::decode, PacketFluidNetworkUpdate::handle);
        INSTANCE.registerMessage(getUniqueId(), PacketEntityUpdate.class, PacketEntityUpdate::encode, PacketEntityUpdate::decode, PacketEntityUpdate::handle);
        INSTANCE.registerMessage(getUniqueId(), PacketDynamicInventory.class, PacketDynamicInventory::encode, PacketDynamicInventory::decode, PacketDynamicInventory::handle);
    }

    public static void registerPlanetsPackets()
    {
        if (GalacticraftCore.isPlanetsLoaded)
        {
            INSTANCE.registerMessage(getUniqueId(), PacketSimpleMars.class, PacketSimpleMars::encode, PacketSimpleMars::decode, PacketSimpleMars::handle);
            INSTANCE.registerMessage(getUniqueId(), PacketSimpleAsteroids.class, PacketSimpleAsteroids::encode, PacketSimpleAsteroids::decode, PacketSimpleAsteroids::handle);
            INSTANCE.registerMessage(getUniqueId(), PacketSimpleVenus.class, PacketSimpleVenus::encode, PacketSimpleVenus::decode, PacketSimpleVenus::handle);
        }
    }

    /**
     * Send this message to the specified player.
     *
     * @param message - the message to send
     * @param player  - the player to send it to
     */
    public <MSG> void sendTo(MSG message, ServerPlayer player)
    {
        INSTANCE.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    /**
     * Send this message to everyone connected to the server.
     *
     * @param message - message to send
     */
    public <MSG> void sendToAll(MSG message)
    {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    /**
     * Send this message to everyone within the supplied dimension.
     *
     * @param message   - the message to send
     * @param dimension - the dimension to target
     */
    public <MSG> void sendToDimension(MSG message, DimensionType dimension)
    {
        INSTANCE.send(PacketDistributor.DIMENSION.with(() -> dimension), message);
    }

    /**
     * Send this message to the server.
     *
     * @param message - the message to send
     */
    public <MSG> void sendToServer(MSG message)
    {
        INSTANCE.sendToServer(message);
    }

    public <MSG> void sendToAllTracking(MSG message, Entity entity)
    {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
    }

    public <MSG> void sendToAllAround(MSG message, PacketDistributor.TargetPoint point)
    {
        INSTANCE.send(PacketDistributor.NEAR.with(() -> point), message);
    }

    public <MSG> void sendToAllTracking(MSG message, BlockEntity tile)
    {
        sendToAllTracking(message, tile.getLevel(), tile.getBlockPos());
    }

    public <MSG> void sendToAllTracking(MSG message, Level world, BlockPos pos)
    {
        if (world instanceof ServerLevel)
        {
            //If we have a ServerWorld just directly figure out the ChunkPos so as to not require looking up the chunk
            // This provides a decent performance boost over using the packet distributor
            ((ServerLevel) world).getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false).forEach(p -> sendTo(message, p));
        }
        else
        {
            //Otherwise fallback to entities tracking the chunk if some mod did something odd and our world is not a ServerWorld
            INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> world.getChunk(pos.getX() >> 4, pos.getZ() >> 4)), message);
        }
    }

//    /**
//     * Send this message to everyone.
//     * <p/>
//     * Adapted from CPW's code in
//     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
//     *
//     * @param message The message to send
//     */
//    public void sendToAll(IPacket message)
//    {
//        this.channels.get(EnvType.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
//        this.channels.get(EnvType.SERVER).writeOutbound(message);
//    }
//
//    /**
//     * Send this message to the specified player.
//     * <p/>
//     * Adapted from CPW's code in
//     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
//     *
//     * @param message The message to send
//     * @param player  The player to send it to
//     */
//    public void sendTo(IPacket message, ServerPlayerEntity player)
//    {
//        this.channels.get(EnvType.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
//        this.channels.get(EnvType.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
//        this.channels.get(EnvType.SERVER).writeOutbound(message);
//    }
//
//    /**
//     * Send this message to everyone within a certain range of a point.
//     * <p/>
//     * Adapted from CPW's code in
//     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
//     *
//     * @param message The message to send
//     * @param point   The
//     *                {@link net.minecraftforge.fml.common.network.PacketDistributor.TargetPoint}
//     *                around which to send
//     */
//    public void sendToAllAround(IPacket message, PacketDistributor.TargetPoint point)
//    {
//        try
//        {
//            this.channels.get(EnvType.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
//            this.channels.get(EnvType.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
//            this.channels.get(EnvType.SERVER).writeOutbound(message);
//        }
//        catch (Exception e)
//        {
//            GCLog.severe("Forge error when sending network packet to nearby players - this is not a Galacticraft bug, does another mod make fake players?");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Send this message to everyone within the supplied dimension.
//     * <p/>
//     * Adapted from CPW's code in
//     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
//     *
//     * @param message     The message to send
//     * @param dimensionID The dimension id to target
//     */
//    public void sendToDimension(IPacket message, int dimensionID)
//    {
//        try
//        {
//            this.channels.get(EnvType.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
//            this.channels.get(EnvType.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionID);
//            this.channels.get(EnvType.SERVER).writeOutbound(message);
//        }
//        catch (Exception e)
//        {
//            GCLog.severe("Forge error when sending network packet to all players in dimension - this is not a Galacticraft bug, does another mod make fake players?");
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Send this message to the server.
//     * <p/>
//     * Adapted from CPW's code in
//     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
//     *
//     * @param message The message to send
//     */
//    public void sendToServer(IPacket message)
//    {
//        if (FMLCommonHandler.instance().getSide() != EnvType.CLIENT)
//        {
//            return;
//        }
//        this.channels.get(EnvType.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
//        this.channels.get(EnvType.CLIENT).writeOutbound(message);
//    }
}