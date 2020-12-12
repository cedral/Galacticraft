package team.galacticraft.galacticraft.common.core.schematic;

import team.galacticraft.galacticraft.common.api.recipe.SchematicPage;
import team.galacticraft.galacticraft.common.core.GCItems;
import team.galacticraft.galacticraft.common.core.client.gui.container.GuiSchematicBuggy;
import team.galacticraft.galacticraft.common.core.inventory.ContainerSchematicBuggy;
import team.galacticraft.galacticraft.common.core.util.ConfigManagerCore;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SchematicMoonBuggy extends SchematicPage
{
    @Override
    public int getPageID()
    {
        return ConfigManagerCore.idSchematicMoonBuggy.get();
    }

//    @Override
//    public int getGuiID()
//    {
//        return GuiIdsCore.NASA_WORKBENCH_BUGGY;
//    }

    @Override
    public ItemStack getRequiredItem()
    {
        return new ItemStack(GCItems.schematicBuggy, 1);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public MenuScreens.ScreenConstructor<ContainerSchematicBuggy, GuiSchematicBuggy> getResultScreen(Player player, BlockPos pos)
    {
        return GuiSchematicBuggy::new;
    }

    @Override
    public SimpleMenuProvider getContainerProvider(Player player)
    {
        return new SimpleMenuProvider((w, p, pl) -> new ContainerSchematicBuggy(w, p), new TranslatableComponent("container.buggy"));
    }
}