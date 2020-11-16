package micdoodle8.mods.galacticraft.core.client.render.entities.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import micdoodle8.mods.galacticraft.core.client.model.ModelEvolvedWitch;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedWitch;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.model.WitchModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerHeldItemEvolvedWitch extends CrossedArmsItemLayer<EntityEvolvedWitch, ModelEvolvedWitch>
{
    public LayerHeldItemEvolvedWitch(IEntityRenderer<EntityEvolvedWitch, ModelEvolvedWitch> p_i50916_1_) {
        super(p_i50916_1_);
    }

    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, EntityEvolvedWitch entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entitylivingbaseIn.getHeldItemMainhand();
        matrixStackIn.push();
        if (itemstack.getItem() == Items.POTION) {
            this.getEntityModel().getModelHead().translateRotate(matrixStackIn);
            this.getEntityModel().func_205073_b().translateRotate(matrixStackIn);
            matrixStackIn.translate(0.0625D, 0.25D, 0.0D);
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(180.0F));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(140.0F));
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(10.0F));
            matrixStackIn.translate(0.0D, (double)-0.4F, (double)0.4F);
        }

        super.render(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
        matrixStackIn.pop();
    }
}
