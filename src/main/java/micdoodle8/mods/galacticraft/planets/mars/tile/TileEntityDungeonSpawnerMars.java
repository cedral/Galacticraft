package micdoodle8.mods.galacticraft.planets.mars.tile;

import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.client.sounds.GCSounds;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.tile.TileEntityDungeonSpawner;
import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlockNames;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntityCreeperBoss;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

public class TileEntityDungeonSpawnerMars extends TileEntityDungeonSpawner<EntityCreeperBoss>
{
    @ObjectHolder(Constants.MOD_ID_PLANETS + ":" + MarsBlockNames.cryoChamber)
    public static BlockEntityType<TileEntityDungeonSpawnerMars> TYPE;

    public TileEntityDungeonSpawnerMars()
    {
        super(TYPE, EntityCreeperBoss.class);
    }

    @Override
    public List<Class<? extends Mob>> getDisabledCreatures()
    {
        List<Class<? extends Mob>> list = new ArrayList<Class<? extends Mob>>();
        list.add(EntityEvolvedSkeleton.class);
        list.add(EntityEvolvedZombie.class);
        list.add(EntityEvolvedSpider.class);
        return list;
    }

    @Override
    public void playSpawnSound(Entity entity)
    {
        this.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), GCSounds.scaryScape, SoundSource.AMBIENT, 9.0F, 1.4F);
    }
}
