package team.galacticraft.galacticraft.common.core.tile;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import team.galacticraft.galacticraft.common.core.tile.IMachineSides.Face;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

/**
 * Used to create the blockState property corresponding to the machine sides,
 * and to correspondingly constrain the allowable faces.
 * <p>
 * It's not compulsory to use this, a block could create its blockState model
 * from the machine sides in some different way.
 */
public class IMachineSidesProperties
{
    public static IMachineSidesProperties NOT_CONFIGURABLE = new IMachineSidesProperties(MachineSidesModel.noneConfigurable(), Face.Horizontals);
    public static IMachineSidesProperties ONEFACE_HORIZ = new IMachineSidesProperties(MachineSidesModel.oneFacedHoriz(), Face.Horizontals);
    public static IMachineSidesProperties ONEFACE = new IMachineSidesProperties(MachineSidesModel.oneFacedAll(), Face.AllAvailable);
    public static IMachineSidesProperties TWOFACES_HORIZ = new IMachineSidesProperties(MachineSidesModel.twoFacedHoriz(), Face.Horizontals);
    public static IMachineSidesProperties TWOFACES_ALL = new IMachineSidesProperties(MachineSidesModel.twoFacedAll(), Face.AllAvailable);

    public EnumProperty<MachineSidesModel> asProperty;
    private final Predicate<MachineSidesModel> filter;
    private final Face[] toFaces;

    public IMachineSidesProperties(Predicate<MachineSidesModel> theFilter, Face[] faces)
    {
        this.asProperty = EnumProperty.create("msm", MachineSidesModel.class, theFilter);
        this.filter = theFilter;
        this.toFaces = faces;
    }

    /**
     * Default blockState model to use if the tile can't be read
     */
    public MachineSidesModel getDefault()
    {
        return MachineSidesModel.LEFT1;
    }

    /**
     * Allowable faces for the configurable sides, consistent with the blockState variants
     */
    public Face[] allowableFaces()
    {
        return this.toFaces;
    }

    public boolean isValidFor(MachineSidesModel machineSidesModel)
    {
        return filter.apply(machineSidesModel);
    }

    public static MachineSidesModel getModelForTwoFaces(Face faceA, Face faceB)
    {
        String result = faceA.getName() + faceB.getName();
        for (MachineSidesModel test : MachineSidesModel.values())
        {
            if (result.equals(test.name))
            {
                return test;
            }
        }
        return MachineSidesModel.RIGHT1;
    }

    /**
     * The return results will all be allowed by the oneFacedAll predicate
     */
    public static MachineSidesModel getModelForOneFace(Face allowedSide)
    {
        switch (allowedSide)
        {
        case RIGHT:
            return MachineSidesModel.RIGHT1;
        case REAR:
            return MachineSidesModel.REAR1;
        case TOP:
            return MachineSidesModel.TOP1;
        case BOTTOM:
            return MachineSidesModel.BOTTOM1;
        case LEFT:
        default:
            return MachineSidesModel.LEFT1;
        }
    }

    /**
     * The strings match the blockState model implementation for rendering
     */
    public enum MachineSidesModel implements StringRepresentable
    {
        //Don't change the order, the ordinal is important for the predicate definitions
        LEFT1("lr"),
        LEFT2("lb"),
        LEFT3("lu"),
        LEFT4("ld"),
        RIGHT1("rl"),
        RIGHT2("rb"),
        RIGHT3("ru"),
        RIGHT4("rd"),
        REAR1("bl"),
        REAR2("br"),
        REAR3("bu"),
        REAR4("bd"),
        TOP1("ul"),
        TOP2("ur"),
        TOP3("ub"),
        TOP4("ud"),
        BOTTOM1("dl"),
        BOTTOM2("dr"),
        BOTTOM3("db"),
        BOTTOM4("du");

        private final String name;

        MachineSidesModel(String newname)
        {
            this.name = newname;
        }

        @Override
        public String getSerializedName()
        {
            return this.name;
        }

        public MachineSidesModel validFor(IMachineSidesProperties configurationType)
        {
            if (configurationType.isValidFor(this))
            {
                return this;
            }
            return configurationType.getDefault();
        }

        private static Predicate<MachineSidesModel> oneFacedAll()
        {
            return new Predicate<MachineSidesModel>()
            {
                @Override
                public boolean apply(MachineSidesModel msm)
                {
                    return (msm.ordinal() % 4) == 0;
                }
            };
        }

        private static Predicate<MachineSidesModel> twoFacedAll()
        {
            return Predicates.alwaysTrue();
        }

        private static Predicate<MachineSidesModel> oneFacedHoriz()
        {
            return new Predicate<MachineSidesModel>()
            {
                @Override
                public boolean apply(MachineSidesModel msm)
                {
                    return msm.ordinal() < 12 && (msm.ordinal() % 4) == 0;
                }
            };
        }

        private static Predicate<MachineSidesModel> twoFacedHoriz()
        {
            return new Predicate<MachineSidesModel>()
            {
                @Override
                public boolean apply(MachineSidesModel msm)
                {
                    return msm.ordinal() < 12 && (msm.ordinal() % 4) < 2;
                }
            };
        }

        private static Predicate<MachineSidesModel> noneConfigurable()
        {
            return new Predicate<MachineSidesModel>()
            {
                @Override
                public boolean apply(MachineSidesModel msm)
                {
                    return msm == LEFT1;
                }
            };
        }
    }
}
