package team.galacticraft.galacticraft.common.core.client.model.block;
//
//import com.google.common.collect.ImmutableList;
//
//import team.galacticraft.galacticraft.common.Constants;
//import team.galacticraft.galacticraft.common.core.TransformerHooks;
//import net.minecraft.block.BlockState;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BufferBuilder;
//import net.minecraft.client.renderer.model.*;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.util.BlockRenderLayer;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.BlockPos;
//import net.minecraftforge.client.MinecraftForgeClient;
//import net.minecraftforge.client.model.data.IModelData;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import java.util.List;
//import java.util.Random;
//
//public class ModelGrating implements IBakedModel
//{
//    private final IBakedModel gratingMetal;
//
//    public ModelGrating (ModelResourceLocation blockLoc, ModelManager modelManager)
//    {
//        this.gratingMetal = modelManager.getModel(blockLoc);
//    }
//
//    @NotNull
//    @Override
//    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull Random rand, @NotNull IModelData extraData)
//    {
//        if (EnvType == Direction.DOWN && state.getBlock() instanceof BlockGrating)
//        {
//            BlockState baseState = ((IExtendedBlockState) state).getValue(BlockGrating.BASE_STATE);
//            if (baseState != null)
//            {
//                IBlockReader blockAccess = BlockGrating.savedBlockAccess;
//                BlockPos pos = BlockGrating.savedPos;
//                BufferBuilder buffer = TransformerHooks.renderBuilder.get();
//                if (buffer != null)
//                {
//                    if (baseState.getBlock().canRenderInLayer(baseState, MinecraftForgeClient.getRenderLayer()))
//                    try {
//                        Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(baseState, pos, blockAccess, buffer);
//                    } catch (Exception ignore) { ignore.printStackTrace(); }
//                }
//            }
//        }
//        if (MinecraftForgeClient.getRenderLayer() == BlockRenderLayer.CUTOUT)
//        {
//            return this.gratingMetal.getQuads(state, EnvType, rand);
//        }
//
//        return ImmutableList.of();
//    }
//
//    @Override
//    public boolean isAmbientOcclusion()
//    {
//        return true;
//    }
//
//    @Override
//    public boolean isGui3d()
//    {
//        return true;
//    }
//
//    @Override
//    public boolean isBuiltInRenderer()
//    {
//        return false;
//    }
//
//    @Override
//    public TextureAtlasSprite getParticleTexture()
//    {
//        return Minecraft.getInstance().getTextureMap().getAtlasSprite(Constants.MOD_ID_CORE + ":blocks/screen_side");
//    }
//
//    @Override
//    public ItemCameraTransforms getItemCameraTransforms()
//    {
//        return ItemCameraTransforms.DEFAULT;
//    }
//
//    @Override
//    public ItemOverrideList getOverrides()
//    {
//        return ItemOverrideList.EMPTY;
//    }
//}