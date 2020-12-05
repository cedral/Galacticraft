package micdoodle8.mods.galacticraft.planets.venus.client.render.entity;
//
//import com.mojang.blaze3d.platform.GlStateManager;
//import micdoodle8.mods.galacticraft.core.client.gui.overlay.OverlaySensorGlasses;
//import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
//import micdoodle8.mods.galacticraft.planets.venus.client.model.ModelJuicer;
//import micdoodle8.mods.galacticraft.planets.venus.entities.EntityJuicer;
//import net.minecraft.client.renderer.entity.EntityRendererManager;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//@OnlyIn(Dist.CLIENT)
//public class RenderJuicer extends MobRenderer<EntityJuicer, ModelJuicer>
//{
//    private static final ResourceLocation juicerTexture = new ResourceLocation(GalacticraftPlanets.ASSET_PREFIX, "textures/model/juicer.png");
//    private boolean texSwitch;
//
//    public RenderJuicer(EntityRendererManager renderManager)
//    {
//        super(renderManager, new ModelJuicer(), 0.5F);
//    }
//
//    @Override
//    protected void preRenderCallback(EntityJuicer entity, float partialTickTime)
//    {
//        GlStateManager.rotatef(entity.isHanging() ? 180.0F : 0.0F, 1.0F, 0.0F, 0.0F);
//        GlStateManager.translatef(0.0F, entity.isHanging() ? 1.8F : 1.3F, 0.0F);
//        super.preRenderCallback(entity, partialTickTime);
//        if (texSwitch)
//        {
//            OverlaySensorGlasses.preRenderMobs();
//        }
//    }
//
//    @Override
//    public void doRender(EntityJuicer entity, double x, double y, double z, float entityYaw, float partialTicks)
//    {
//        super.doRender(entity, x, y, z, entityYaw, partialTicks);
//        if (OverlaySensorGlasses.overrideMobTexture())
//        {
//            texSwitch = true;
//            super.doRender(entity, x, y, z, entityYaw, partialTicks);
//            texSwitch = false;
//            OverlaySensorGlasses.postRenderMobs();
//        }
//    }
//
//    @Override
//    protected ResourceLocation getEntityTexture(EntityJuicer juicer)
//    {
//        return texSwitch ? OverlaySensorGlasses.altTexture : juicerTexture;
//    }
//}
