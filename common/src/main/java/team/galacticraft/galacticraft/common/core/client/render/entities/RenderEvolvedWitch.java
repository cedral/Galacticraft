package team.galacticraft.galacticraft.common.core.client.render.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import team.galacticraft.galacticraft.common.Constants;
import team.galacticraft.galacticraft.common.core.client.gui.overlay.OverlaySensorGlasses;
import team.galacticraft.galacticraft.common.core.client.model.ModelEvolvedWitch;
import team.galacticraft.galacticraft.common.core.client.render.entities.layer.LayerHeldItemEvolvedWitch;
import team.galacticraft.galacticraft.common.core.entities.EntityEvolvedWitch;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class RenderEvolvedWitch extends MobRenderer<EntityEvolvedWitch, ModelEvolvedWitch>
{
    private static final ResourceLocation witchTexture = new ResourceLocation(Constants.MOD_ID_CORE, "textures/model/evolved_witch.png");
    private boolean texSwitch;

    public RenderEvolvedWitch(EntityRenderDispatcher manager)
    {
        super(manager, new ModelEvolvedWitch(), 0.5F);
        this.addLayer(new LayerHeldItemEvolvedWitch(this));
    }

    @Override
    public void render(EntityEvolvedWitch entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn)
    {
        this.getModel().setHoldingItem(entityIn.getMainHandItem().isEmpty());
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        if (OverlaySensorGlasses.overrideMobTexture())
        {
            texSwitch = true;
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
            texSwitch = false;
            OverlaySensorGlasses.postRenderMobs();
        }
    }

    @Override
    protected void preRenderCallback(EntityEvolvedWitch entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
    {
        float f1 = 0.9375F;
        matrixStackIn.scale(f1, f1, f1);
        if (texSwitch)
        {
            OverlaySensorGlasses.preRenderMobs();
        }
    }

    @Override
    public ResourceLocation getEntityTexture(EntityEvolvedWitch entity)
    {
        return texSwitch ? OverlaySensorGlasses.altTexture : RenderEvolvedWitch.witchTexture;
    }
}