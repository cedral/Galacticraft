package team.galacticraft.galacticraft.common.api.entity;

import me.shedaniel.architectury.fluid.FluidStack;
import team.galacticraft.galacticraft.common.compat.fluid.ActionType;

/**
 * Implement into entities that can be fueled when placed on fuel docks
 */
public interface IFuelable
{
    /**
     * Add fuel to the entity
     *
     * @param fluid  The fluid to add. Be sure to check if the correct fluid is
     *               being loaded before adding it.
     * @param action Whether or not fluid should actually be added, or is just a
     *               test.
     * @return the amount of fluid that was added to the entity.
     */
    int addFuel(FluidStack fluid, ActionType action);

    /**
     * Remove fuel from an entity
     *
     * @param amount The amount of fluid to drain
     * @return The fluidstack that was drained
     */
    FluidStack removeFuel(int amount);
}