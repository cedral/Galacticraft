package micdoodle8.mods.galacticraft.core.client.render.entities.layer;

import micdoodle8.mods.galacticraft.core.client.model.ModelEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Environment(EnvType.CLIENT)
public class LayerEvolvedCreeperCharge extends EnergySwirlLayer<EntityEvolvedCreeper, ModelEvolvedCreeper>
{
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final ModelEvolvedCreeper creeperModel = new ModelEvolvedCreeper(2.0F);

    public LayerEvolvedCreeperCharge(RenderLayerParent<EntityEvolvedCreeper, ModelEvolvedCreeper> renderer) {
        super(renderer);
    }

    @Override
    protected float xOffset(float p_225634_1_) {
        return p_225634_1_ * 0.01F;
    }

    @Override
    protected ResourceLocation getTextureLocation() {
        return LIGHTNING_TEXTURE;
    }

    @Override
    protected ModelEvolvedCreeper model() {
        return this.creeperModel;
    }
}
