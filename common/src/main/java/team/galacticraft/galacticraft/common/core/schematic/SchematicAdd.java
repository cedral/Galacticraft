package team.galacticraft.galacticraft.common.core.schematic;

import team.galacticraft.galacticraft.common.api.recipe.SchematicPage;
import team.galacticraft.galacticraft.common.core.client.gui.container.GuiSchematicInput;
import team.galacticraft.galacticraft.common.core.inventory.ContainerSchematic;
import team.galacticraft.galacticraft.common.core.util.ConfigManagerCore;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SchematicAdd extends SchematicPage
{
    @Override
    public int getPageID()
    {
        return ConfigManagerCore.idSchematicAddSchematic.get();
    }

//    @Override
//    public int getGuiID()
//    {
//        return GuiIdsCore.NASA_WORKBENCH_NEW_SCHEMATIC;
//    }

    @Override
    public ItemStack getRequiredItem()
    {
        return null;  //This null is OK, it's used only as a flag by SchematicRegistry calling code
    }

    @Environment(EnvType.CLIENT)
    @Override
    public MenuScreens.ScreenConstructor<ContainerSchematic, GuiSchematicInput> getResultScreen(Player player, BlockPos pos)
    {
        return GuiSchematicInput::new;
    }

    @Override
    public SimpleMenuProvider getContainerProvider(Player player)
    {
        return new SimpleMenuProvider((w, p, pl) -> new ContainerSchematic(w, p), new TranslatableComponent("container.schematic_add"));
    }
}