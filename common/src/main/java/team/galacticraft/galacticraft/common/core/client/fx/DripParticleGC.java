package team.galacticraft.galacticraft.common.core.client.fx;

import team.galacticraft.galacticraft.common.core.client.GCParticles;
import team.galacticraft.galacticraft.common.core.client.fx.DripParticleGC.DrippingOil;
import team.galacticraft.galacticraft.common.core.fluid.GCFluids;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

@Environment(EnvType.CLIENT)
public class DripParticleGC extends TextureSheetParticle
{
    private final Fluid fluid;

    private DripParticleGC(Level world, double x, double y, double z, Fluid fluid)
    {
        super(world, x, y, z);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.fluid = fluid;
    }

    @Override
    public ParticleRenderType getRenderType()
    {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick()
    {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.checkRespawn();
        if (!this.removed)
        {
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.updateMotion();
            if (!this.removed)
            {
                this.xd *= 0.98F;
                this.yd *= 0.98F;
                this.zd *= 0.98F;
                BlockPos blockpos = new BlockPos(this.x, this.y, this.z);
                FluidState ifluidstate = this.level.getFluidState(blockpos);
                if (ifluidstate.getType() == this.fluid && this.y < (double) ((float) blockpos.getY() + ifluidstate.getHeight(this.level, blockpos)))
                {
                    this.remove();
                }

            }
        }
    }

    protected void checkRespawn()
    {
        if (this.lifetime-- <= 0)
        {
            this.remove();
        }

    }

    protected void updateMotion()
    {
    }

    @Environment(EnvType.CLIENT)
    static class Dripping extends DripParticleGC
    {
        private final ParticleOptions data;

        private Dripping(Level world, double x, double y, double z, Fluid fluid, ParticleOptions data)
        {
            super(world, x, y, z, fluid);
            this.data = data;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        @Override
        protected void checkRespawn()
        {
            if (this.lifetime-- <= 0)
            {
                this.remove();
                this.level.addParticle(this.data, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }

        }

        @Override
        protected void updateMotion()
        {
            this.xd *= 0.02D;
            this.yd *= 0.02D;
            this.zd *= 0.02D;
        }
    }

    @Environment(EnvType.CLIENT)
    static class DrippingOil extends Dripping
    {
        private DrippingOil(Level world, double x, double y, double z, Fluid fluid, ParticleOptions data)
        {
            super(world, x, y, z, fluid, data);
        }

        @Override
        protected void checkRespawn()
        {
            this.rCol = 1.0F;
            this.gCol = 16.0F / (float) (40 - this.lifetime + 16);
            this.bCol = 4.0F / (float) (40 - this.lifetime + 8);
            super.checkRespawn();
        }
    }

    @Environment(EnvType.CLIENT)
    public static class DrippingOilFactory implements ParticleProvider<SimpleParticleType>
    {
        protected final SpriteSet spriteSet;

        public DrippingOilFactory(SpriteSet sprite)
        {
            this.spriteSet = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, Level worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            DrippingOil oilDrip = new DrippingOil(worldIn, x, y, z, GCFluids.OIL.getFluid(), GCParticles.OIL_DRIP);
            oilDrip.pickSprite(this.spriteSet);
            return oilDrip;
        }
    }
}