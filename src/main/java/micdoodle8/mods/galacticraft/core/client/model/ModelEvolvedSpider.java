package micdoodle8.mods.galacticraft.core.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

public class ModelEvolvedSpider extends EntityModel<EntityEvolvedSpider>
{
    ModelPart body;
    ModelPart rearEnd;
    ModelPart leg8;
    ModelPart leg6;
    ModelPart leg4;
    ModelPart leg2;
    ModelPart leg7;
    ModelPart leg5;
    ModelPart leg3;
    ModelPart leg1;
    ModelPart head;

    ModelPart oxygenMask;
    ModelPart tank1;
    ModelPart tank2;
    ModelPart tube1;
    ModelPart tube2;
    ModelPart tube3;
    ModelPart tube4;
    ModelPart tube5;
    ModelPart tube6;
    ModelPart tube7;
    ModelPart tube8;
    ModelPart tube9;
    ModelPart tube10;
    ModelPart tube11;
    ModelPart tube12;
    ModelPart tube13;
    ModelPart tube15;
    ModelPart tube14;
    ModelPart tube16;
    ModelPart tube17;
    ModelPart tube18;

    public ModelEvolvedSpider()
    {
        this(0.0F);
    }

    public ModelEvolvedSpider(float par1)
    {
        this.texWidth = 128;
        this.texHeight = 64;

        this.body = new ModelPart(this, 0, 0);
        this.body.addBox(-3F, -3F, -3F, 6, 6, 6, par1);
        this.body.setPos(0F, 15F, 0F);
        this.body.setTexSize(128, 64);
        this.body.mirror = true;
        this.setRotation(this.body, 0F, 0F, 0F);
        this.rearEnd = new ModelPart(this, 0, 12);
        this.rearEnd.addBox(-5F, -4F, -6F, 10, 8, 12, par1);
        this.rearEnd.setPos(0F, 15F, 9F);
        this.rearEnd.setTexSize(128, 64);
        this.rearEnd.mirror = true;
        this.setRotation(this.rearEnd, 0F, 0F, 0F);
        this.leg8 = new ModelPart(this, 18, 0);
        this.leg8.addBox(-1F, -1F, -1F, 16, 2, 2, par1);
        this.leg8.setPos(4F, 15F, -1F);
        this.leg8.setTexSize(128, 64);
        this.leg8.mirror = true;
        this.setRotation(this.leg8, 0F, 0.5759587F, 0.1919862F);
        this.leg6 = new ModelPart(this, 18, 0);
        this.leg6.addBox(-1F, -1F, -1F, 16, 2, 2, par1);
        this.leg6.setPos(4F, 15F, 0F);
        this.leg6.setTexSize(128, 64);
        this.leg6.mirror = true;
        this.setRotation(this.leg6, 0F, 0.2792527F, 0.1919862F);
        this.leg4 = new ModelPart(this, 18, 0);
        this.leg4.addBox(-1F, -1F, -1F, 16, 2, 2, par1);
        this.leg4.setPos(4F, 15F, 1F);
        this.leg4.setTexSize(128, 64);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0F, -0.2792527F, 0.1919862F);
        this.leg2 = new ModelPart(this, 18, 0);
        this.leg2.addBox(-1F, -1F, -1F, 16, 2, 2, par1);
        this.leg2.setPos(4F, 15F, 2F);
        this.leg2.setTexSize(128, 64);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0F, -0.5759587F, 0.1919862F);
        this.leg7 = new ModelPart(this, 18, 0);
        this.leg7.addBox(-15F, -1F, -1F, 16, 2, 2, par1);
        this.leg7.setPos(-4F, 15F, -1F);
        this.leg7.setTexSize(128, 64);
        this.leg7.mirror = true;
        this.setRotation(this.leg7, 0F, -0.5759587F, -0.1919862F);
        this.leg5 = new ModelPart(this, 18, 0);
        this.leg5.addBox(-15F, -1F, -1F, 16, 2, 2, par1);
        this.leg5.setPos(-4F, 15F, 0F);
        this.leg5.setTexSize(128, 64);
        this.leg5.mirror = true;
        this.setRotation(this.leg5, 0F, -0.2792527F, -0.1919862F);
        this.leg3 = new ModelPart(this, 18, 0);
        this.leg3.addBox(-15F, -1F, -1F, 16, 2, 2, par1);
        this.leg3.setPos(-4F, 15F, 1F);
        this.leg3.setTexSize(128, 64);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0F, 0.2792527F, -0.1919862F);
        this.leg1 = new ModelPart(this, 18, 0);
        this.leg1.addBox(-15F, -1F, -1F, 16, 2, 2, par1);
        this.leg1.setPos(-4F, 15F, 2F);
        this.leg1.setTexSize(128, 64);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0F, 0.5759587F, -0.1919862F);
        this.head = new ModelPart(this, 32, 4);
        this.head.addBox(-4F, -4F, -8F, 8, 8, 8, par1);
        this.head.setPos(0F, 15F, -3F);
        this.head.setTexSize(128, 64);
        this.head.mirror = true;
        this.setRotation(this.head, 0F, 0F, 0F);

        this.oxygenMask = new ModelPart(this, 0, 32);
        this.oxygenMask.addBox(-5F, -5F, -9F, 10, 10, 10, par1);
        this.oxygenMask.setPos(0F, 15F, -3F);
        this.oxygenMask.setTexSize(128, 64);
        this.oxygenMask.mirror = true;
        this.setRotation(this.oxygenMask, 0F, 0F, 0F);
        this.tank1 = new ModelPart(this, 40, 34);
        this.tank1.addBox(1F, -3F, 0F, 3, 3, 7, par1);
        this.tank1.setPos(0F, 11F, 4F);
        this.tank1.setTexSize(128, 64);
        this.tank1.mirror = true;
        this.setRotation(this.tank1, 0F, 0F, 0F);
        this.tank2 = new ModelPart(this, 40, 34);
        this.tank2.addBox(-4F, -3F, 0F, 3, 3, 7, par1);
        this.tank2.setPos(0F, 11F, 4F);
        this.tank2.setTexSize(128, 64);
        this.tank2.mirror = true;
        this.setRotation(this.tank2, 0F, 0F, 0F);
        this.tube1 = new ModelPart(this, 40, 32);
        this.tube1.addBox(2F, 0F, -6.5F, 1, 1, 1, par1);
        this.tube1.setPos(0F, 11F, 4F);
        this.tube1.setTexSize(128, 64);
        this.tube1.mirror = true;
        this.setRotation(this.tube1, 0F, 0F, 0F);
        this.tube2 = new ModelPart(this, 40, 32);
        this.tube2.addBox(2F, -1F, -5.5F, 1, 1, 1, par1);
        this.tube2.setPos(0F, 11F, 4F);
        this.tube2.setTexSize(128, 64);
        this.tube2.mirror = true;
        this.setRotation(this.tube2, 0F, 0F, 0F);
        this.tube3 = new ModelPart(this, 40, 32);
        this.tube3.addBox(2F, -1F, -4.5F, 1, 1, 1, par1);
        this.tube3.setPos(0F, 11F, 4F);
        this.tube3.setTexSize(128, 64);
        this.tube3.mirror = true;
        this.setRotation(this.tube3, 0F, 0F, 0F);
        this.tube4 = new ModelPart(this, 40, 32);
        this.tube4.addBox(2F, -2F, -3.5F, 1, 1, 1, par1);
        this.tube4.setPos(0F, 11F, 4F);
        this.tube4.setTexSize(128, 64);
        this.tube4.mirror = true;
        this.setRotation(this.tube4, 0F, 0F, 0F);
        this.tube5 = new ModelPart(this, 40, 32);
        this.tube5.addBox(2F, -3F, -2.5F, 1, 1, 1, par1);
        this.tube5.setPos(0F, 11F, 4F);
        this.tube5.setTexSize(128, 64);
        this.tube5.mirror = true;
        this.setRotation(this.tube5, 0F, 0F, 0F);
        this.tube6 = new ModelPart(this, 40, 32);
        this.tube6.addBox(2F, -4F, -2.5F, 1, 1, 1, par1);
        this.tube6.setPos(0F, 11F, 4F);
        this.tube6.setTexSize(128, 64);
        this.tube6.mirror = true;
        this.setRotation(this.tube6, 0F, 0F, 0F);
        this.tube7 = new ModelPart(this, 40, 32);
        this.tube7.addBox(2F, -5F, -1.5F, 1, 1, 1, par1);
        this.tube7.setPos(0F, 11F, 4F);
        this.tube7.setTexSize(128, 64);
        this.tube7.mirror = true;
        this.setRotation(this.tube7, 0F, 0F, 0F);
        this.tube8 = new ModelPart(this, 40, 32);
        this.tube8.addBox(2F, -5F, -0.5F, 1, 1, 1, par1);
        this.tube8.setPos(0F, 11F, 4F);
        this.tube8.setTexSize(128, 64);
        this.tube8.mirror = true;
        this.setRotation(this.tube8, 0F, 0F, 0F);
        this.tube9 = new ModelPart(this, 40, 32);
        this.tube9.addBox(2F, -4F, 0.5F, 1, 1, 1, par1);
        this.tube9.setPos(0F, 11F, 4F);
        this.tube9.setTexSize(128, 64);
        this.tube9.mirror = true;
        this.setRotation(this.tube9, 0F, 0F, 0F);
        this.tube10 = new ModelPart(this, 40, 32);
        this.tube10.addBox(-3F, 0F, -6.5F, 1, 1, 1, par1);
        this.tube10.setPos(0F, 11F, 4F);
        this.tube10.setTexSize(128, 64);
        this.tube10.mirror = true;
        this.setRotation(this.tube10, 0F, 0F, 0F);
        this.tube11 = new ModelPart(this, 40, 32);
        this.tube11.addBox(-3F, -1F, -5.5F, 1, 1, 1, par1);
        this.tube11.setPos(0F, 11F, 4F);
        this.tube11.setTexSize(128, 64);
        this.tube11.mirror = true;
        this.setRotation(this.tube11, 0F, 0F, 0F);
        this.tube12 = new ModelPart(this, 40, 32);
        this.tube12.addBox(-3F, -1F, -4.5F, 1, 1, 1, par1);
        this.tube12.setPos(0F, 11F, 4F);
        this.tube12.setTexSize(128, 64);
        this.tube12.mirror = true;
        this.setRotation(this.tube12, 0F, 0F, 0F);
        this.tube13 = new ModelPart(this, 40, 32);
        this.tube13.addBox(-3F, -2F, -3.5F, 1, 1, 1, par1);
        this.tube13.setPos(0F, 11F, 4F);
        this.tube13.setTexSize(128, 64);
        this.tube13.mirror = true;
        this.setRotation(this.tube13, 0F, 0F, 0F);
        this.tube15 = new ModelPart(this, 40, 32);
        this.tube15.addBox(-3F, -4F, -2.5F, 1, 1, 1, par1);
        this.tube15.setPos(0F, 11F, 4F);
        this.tube15.setTexSize(128, 64);
        this.tube15.mirror = true;
        this.setRotation(this.tube15, 0F, 0F, 0F);
        this.tube14 = new ModelPart(this, 40, 32);
        this.tube14.addBox(-3F, -3F, -2.5F, 1, 1, 1, par1);
        this.tube14.setPos(0F, 11F, 4F);
        this.tube14.setTexSize(128, 64);
        this.tube14.mirror = true;
        this.setRotation(this.tube14, 0F, 0F, 0F);
        this.tube16 = new ModelPart(this, 40, 32);
        this.tube16.addBox(-3F, -5F, -1.5F, 1, 1, 1, par1);
        this.tube16.setPos(0F, 11F, 4F);
        this.tube16.setTexSize(128, 64);
        this.tube16.mirror = true;
        this.setRotation(this.tube16, 0F, 0F, 0F);
        this.tube17 = new ModelPart(this, 40, 32);
        this.tube17.addBox(-3F, -5F, -0.5F, 1, 1, 1, par1);
        this.tube17.setPos(0F, 11F, 4F);
        this.tube17.setTexSize(128, 64);
        this.tube17.mirror = true;
        this.setRotation(this.tube17, 0F, 0F, 0F);
        this.tube18 = new ModelPart(this, 40, 32);
        this.tube18.addBox(-3F, -4F, 0.5F, 1, 1, 1, par1);
        this.tube18.setPos(0F, 11F, 4F);
        this.tube18.setTexSize(128, 64);
        this.tube18.mirror = true;
        this.setRotation(this.tube18, 0F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(EntityEvolvedSpider entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
    {
        this.head.yRot = netHeadYaw / Constants.RADIANS_TO_DEGREES;
        this.head.xRot = headPitch / Constants.RADIANS_TO_DEGREES;
        this.oxygenMask.yRot = netHeadYaw / Constants.RADIANS_TO_DEGREES;
        this.oxygenMask.xRot = headPitch / Constants.RADIANS_TO_DEGREES;
        final float var7 = (float) Math.PI / 4F;
        this.leg1.zRot = -var7;
        this.leg2.zRot = var7;
        this.leg3.zRot = -var7 * 0.74F;
        this.leg4.zRot = var7 * 0.74F;
        this.leg5.zRot = -var7 * 0.74F;
        this.leg6.zRot = var7 * 0.74F;
        this.leg7.zRot = -var7;
        this.leg8.zRot = var7;
        final float var8 = -0.0F;
        final float var9 = 0.3926991F;
        this.leg1.yRot = var9 * 2.0F + var8;
        this.leg2.yRot = -var9 * 2.0F - var8;
        this.leg3.yRot = var9 * 1.0F + var8;
        this.leg4.yRot = -var9 * 1.0F - var8;
        this.leg5.yRot = -var9 * 1.0F + var8;
        this.leg6.yRot = var9 * 1.0F - var8;
        this.leg7.yRot = -var9 * 2.0F + var8;
        this.leg8.yRot = var9 * 2.0F - var8;
        final float var10 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
        final float var11 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        final float var12 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + Constants.halfPI) * 0.4F) * limbSwingAmount;
        final float var13 = -(Mth.cos(limbSwing * 0.6662F * 2.0F + 3F * Constants.halfPI) * 0.4F) * limbSwingAmount;
        final float var14 = Math.abs(Mth.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;
        final float var15 = Math.abs(Mth.sin(limbSwing * 0.6662F + (float) Math.PI) * 0.4F) * limbSwingAmount;
        final float var16 = Math.abs(Mth.sin(limbSwing * 0.6662F + Constants.halfPI) * 0.4F) * limbSwingAmount;
        final float var17 = Math.abs(Mth.sin(limbSwing * 0.6662F + 3F * Constants.halfPI) * 0.4F) * limbSwingAmount;
        this.leg1.yRot += var10;
        this.leg2.yRot += -var10;
        this.leg3.yRot += var11;
        this.leg4.yRot += -var11;
        this.leg5.yRot += var12;
        this.leg6.yRot += -var12;
        this.leg7.yRot += var13;
        this.leg8.yRot += -var13;
        this.leg1.zRot += var14;
        this.leg2.zRot += -var14;
        this.leg3.zRot += var15;
        this.leg4.zRot += -var15;
        this.leg5.zRot += var16;
        this.leg6.zRot += -var16;
        this.leg7.zRot += var17;
        this.leg8.zRot += -var17;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
    {
        this.body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.rearEnd.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg8.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.leg1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.oxygenMask.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tank1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tank2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube3.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube4.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube5.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube6.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube7.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube8.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube9.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube10.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube11.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube12.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube13.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube15.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube14.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube16.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube17.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        this.tube18.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    private void setRotation(ModelPart model, float x, float y, float z)
    {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }
}
