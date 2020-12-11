package team.galacticraft.galacticraft.common.core.client.obj;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.geometry.IModelGeometryPart;

public class VisibleModelConfiguration extends WrapperModelConfiguration {

    private final List<String> visibleGroups;

    public VisibleModelConfiguration(IModelConfiguration internal, List<String> visibleGroups) {
        super(internal);
        this.visibleGroups = visibleGroups;
    }

    @Override
    public boolean getPartVisibility(@NotNull IModelGeometryPart part, boolean fallback) {
        //Ignore fallback as we always have a true or false answer
        return getPartVisibility(part);
    }

    @Override
    public boolean getPartVisibility(@NotNull IModelGeometryPart part) {
        if (visibleGroups.isEmpty()) return true;
        return visibleGroups.contains(part.name());
    }
}