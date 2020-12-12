package team.galacticraft.galacticraft.common.core.client.gui.container;

import team.galacticraft.galacticraft.common.core.client.gui.element.GuiElementInfoRegion;
import team.galacticraft.galacticraft.common.core.entities.EntityBuggy;
import team.galacticraft.galacticraft.common.core.inventory.ContainerBuggy;
import team.galacticraft.galacticraft.common.core.util.EnumColor;
import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiBuggy extends GuiContainerGC<ContainerBuggy>
{
    private final ResourceLocation textureLoc;

    public GuiBuggy(ContainerBuggy containerBuggy, Inventory playerInv, Component title)
    {
        super(containerBuggy, playerInv, title);
        this.passEvents = false;
        this.textureLoc = containerBuggy.buggyType.getTextureLoc();
        this.imageHeight = 145 + containerBuggy.buggyType.ordinal() * 36;
    }

    @Override
    protected void init()
    {
        super.init();
        List<String> oxygenDesc = new ArrayList<String>();
        oxygenDesc.add(I18n.get("gui.fuel_tank.desc.0"));
        oxygenDesc.add(I18n.get("gui.fuel_tank.desc.1"));
        this.infoRegions.add(new GuiElementInfoRegion((this.width - this.imageWidth) / 2 + 71, (this.height - this.imageHeight) / 2 + 6, 36, 40, oxygenDesc, this.width, this.height, this));
    }

    @Override
    protected void renderLabels(int par1, int par2)
    {
        this.font.draw(I18n.get("gui.message.fuel"), 8, 2 + 3, 4210752);

        this.font.draw(this.title.getColoredString(), 8, this.menu.buggyType == EntityBuggy.BuggyType.NO_INVENTORY ? 50 : 39, 4210752);

        if (this.minecraft.player != null && this.minecraft.player.getVehicle() != null && this.minecraft.player.getVehicle() instanceof EntityBuggy)
        {
            this.font.draw(I18n.get("gui.message.fuel") + ":", 125, 15 + 3, 4210752);
            final double percentage = ((EntityBuggy) this.minecraft.player.getVehicle()).getScaledFuelLevel(100);
            final String color = percentage > 80.0D ? EnumColor.BRIGHT_GREEN.getCode() : percentage > 40.0D ? EnumColor.ORANGE.getCode() : EnumColor.RED.getCode();
            final String str = percentage + "% " + I18n.get("gui.message.full");
            this.font.draw(color + str, 117 - str.length() / 2, 20 + 8, 4210752);
        }
    }

    @Override
    protected void renderBg(float par1, int par2, int par3)
    {
        this.minecraft.getTextureManager().bind(textureLoc);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        final int var5 = (this.width - this.imageWidth) / 2;
        final int var6 = (this.height - this.imageHeight) / 2;
        this.blit(var5, var6, 0, 0, 176, this.imageHeight);

        if (this.minecraft.player != null && this.minecraft.player.getVehicle() != null && this.minecraft.player.getVehicle() instanceof EntityBuggy)
        {
            final int fuelLevel = ((EntityBuggy) this.minecraft.player.getVehicle()).getScaledFuelLevel(38);

            this.blit((this.width - this.imageWidth) / 2 + 72, (this.height - this.imageHeight) / 2 + 45 - fuelLevel, 176, 38 - fuelLevel, 42, fuelLevel);
        }
    }
}