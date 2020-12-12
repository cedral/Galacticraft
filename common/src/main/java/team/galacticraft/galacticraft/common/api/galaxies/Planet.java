package team.galacticraft.galacticraft.common.api.galaxies;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome.SpawnerData;

public class Planet extends CelestialBody
{
    protected SolarSystem parentSolarSystem = null;

    public Planet(String planetName)
    {
        super(planetName);
    }

    public SolarSystem getParentSolarSystem()
    {
        return this.parentSolarSystem;
    }

    @Override
    public int getID()
    {
        return GalaxyRegistry.getPlanetID(this.bodyName);
    }

    @Override
    public String getUnlocalizedNamePrefix()
    {
        return "planet";
    }

    public Planet setParentSolarSystem(SolarSystem galaxy)
    {
        this.parentSolarSystem = galaxy;
        return this;
    }

    public static void addMobToSpawn(String planetName, SpawnerData entry, MobCategory classification)
    {
        GalaxyRegistry.getCelestialBodyFromUnlocalizedName("planet." + planetName).addMobInfo(entry, classification);
    }
}