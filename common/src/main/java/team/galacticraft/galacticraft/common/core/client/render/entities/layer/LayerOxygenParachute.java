package team.galacticraft.galacticraft.common.core.client.render.entities.layer;
//
//import com.mojang.blaze3d.platform.GlStateManager;
//import team.galacticraft.galacticraft.common.Constants;
//import team.galacticraft.galacticraft.common.core.GalacticraftCore;
//import team.galacticraft.galacticraft.common.core.client.render.entities.RenderPlayerGC;
//import team.galacticraft.galacticraft.common.core.wrappers.PlayerGearData;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
//import net.minecraft.client.renderer.entity.PlayerRenderer;
//import net.minecraft.client.renderer.entity.layers.LayerRenderer;
//import net.minecraft.client.renderer.entity.model.PlayerModel;
//import net.minecraft.client.renderer.model.ModelRenderer;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//@OnlyIn(Dist.CLIENT)
//public class LayerOxygenParachute extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>
//{
//    private final PlayerRenderer playerRenderer;
//    public ModelRenderer[] parachute = new ModelRenderer[3];
//    public ModelRenderer[] parachuteStrings = new ModelRenderer[4];
//
//    public LayerOxygenParachute(PlayerRenderer playerRendererIn)
//    {
//        super(playerRendererIn);
//        this.playerRenderer = playerRendererIn;
//        float scaleFactor = 0.0F;
//        PlayerModel<AbstractClientPlayerEntity> modelPlayer = playerRendererIn.getEntityModel();
//
//        this.parachute[0] = new ModelRenderer(modelPlayer, 0, 0).setTextureSize(256, 256);
//        this.parachute[0].addBox(-20.0F, -45.0F, -20.0F, 10, 2, 40, scaleFactor);
//        this.parachute[0].setRotationPoint(15.0F, 4.0F, 0.0F);
//        this.parachute[1] = new ModelRenderer(modelPlayer, 0, 42).setTextureSize(256, 256);
//        this.parachute[1].addBox(-20.0F, -45.0F, -20.0F, 40, 2, 40, scaleFactor);
//        this.parachute[1].setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.parachute[2] = new ModelRenderer(modelPlayer, 0, 0).setTextureSize(256, 256);
//        this.parachute[2].addBox(-20.0F, -45.0F, -20.0F, 10, 2, 40, scaleFactor);
//        this.parachute[2].setRotationPoint(11F, -11, 0.0F);
//
//        this.parachuteStrings[0] = new ModelRenderer(modelPlayer, 100, 0).setTextureSize(256, 256);
//        this.parachuteStrings[0].addBox(-0.5F, 0.0F, -0.5F, 1, 40, 1, scaleFactor);
//        this.parachuteStrings[0].setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.parachuteStrings[1] = new ModelRenderer(modelPlayer, 100, 0).setTextureSize(256, 256);
//        this.parachuteStrings[1].addBox(-0.5F, 0.0F, -0.5F, 1, 40, 1, scaleFactor);
//        this.parachuteStrings[1].setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.parachuteStrings[2] = new ModelRenderer(modelPlayer, 100, 0).setTextureSize(256, 256);
//        this.parachuteStrings[2].addBox(-0.5F, 0.0F, -0.5F, 1, 40, 1, scaleFactor);
//        this.parachuteStrings[2].setRotationPoint(0.0F, 0.0F, 0.0F);
//        this.parachuteStrings[3] = new ModelRenderer(modelPlayer, 100, 0).setTextureSize(256, 256);
//        this.parachuteStrings[3].addBox(-0.5F, 0.0F, -0.5F, 1, 40, 1, scaleFactor);
//        this.parachuteStrings[3].setRotationPoint(0.0F, 0.0F, 0.0F);
//    }
//
//    @Override
//    public void render(AbstractClientPlayerEntity player, float f5, float f6, float partialTicks, float f8, float f2, float f7, float scale)
//    {
//        if (!player.isInvisible())
//        {
//            PlayerGearData gearData = GalacticraftCore.proxy.getGearData(player);
//
//            if (gearData != null)
//            {
//                boolean usingParachute = gearData.getParachute() != null;
//
//                if (usingParachute)
//                {
//                    Minecraft.getInstance().textureManager.bindTexture(RenderPlayerGC.PLAYER_TEXTURE);
//
//                    this.parachute[0].rotateAngleZ = 30F / Constants.RADIANS_TO_DEGREES;
//                    this.parachute[2].rotateAngleZ = -(30F / Constants.RADIANS_TO_DEGREES);
//                    this.parachuteStrings[0].rotateAngleZ = 155F / Constants.RADIANS_TO_DEGREES;
//                    this.parachuteStrings[0].rotateAngleX = 23F / Constants.RADIANS_TO_DEGREES;
//                    this.parachuteStrings[0].setRotationPoint(-9.0F, -7.0F, 2.0F);
//                    this.parachuteStrings[1].rotateAngleZ = 155F / Constants.RADIANS_TO_DEGREES;
//                    this.parachuteStrings[1].rotateAngleX = -(23F / Constants.RADIANS_TO_DEGREES);
//                    this.parachuteStrings[1].setRotationPoint(-9.0F, -7.0F, 2.0F);
//                    this.parachuteStrings[2].rotateAngleZ = -(155F / Constants.RADIANS_TO_DEGREES);
//                    this.parachuteStrings[2].rotateAngleX = 23F / Constants.RADIANS_TO_DEGREES;
//                    this.parachuteStrings[2].setRotationPoint(9.0F, -7.0F, 2.0F);
//                    this.parachuteStrings[3].rotateAngleZ = -(155F / Constants.RADIANS_TO_DEGREES);
//                    this.parachuteStrings[3].rotateAngleX = -(23F / Constants.RADIANS_TO_DEGREES);
//                    this.parachuteStrings[3].setRotationPoint(9.0F, -7.0F, 2.0F);
//
//                    GlStateManager.pushMatrix();
//
//                    Minecraft.getInstance().textureManager.bindTexture(gearData.getParachute());
//
//                    this.parachute[0].render(scale);
//                    this.parachute[1].render(scale);
//                    this.parachute[2].render(scale);
//
//                    this.parachuteStrings[0].render(scale);
//                    this.parachuteStrings[1].render(scale);
//                    this.parachuteStrings[2].render(scale);
//                    this.parachuteStrings[3].render(scale);
//
//                    GlStateManager.popMatrix();
//                }
//            }
//        }
//    }
//
//    @Override
//    public boolean shouldCombineTextures()
//    {
//        return true;
//    }
//}