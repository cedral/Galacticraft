package team.galacticraft.galacticraft.common.core.client.gui.container;

import team.galacticraft.galacticraft.common.Constants;
import team.galacticraft.galacticraft.common.core.GalacticraftCore;
import team.galacticraft.galacticraft.common.core.client.gui.element.GuiElementInfoRegion;
import team.galacticraft.galacticraft.common.core.energy.EnergyDisplayHelper;
import team.galacticraft.galacticraft.common.core.inventory.ContainerCargoBase;
import team.galacticraft.galacticraft.common.core.inventory.ContainerCargoBase.ContainerCargoUnloader;
import team.galacticraft.galacticraft.common.core.network.PacketSimple;
import team.galacticraft.galacticraft.common.core.network.PacketSimple.EnumSimplePacket;
import team.galacticraft.galacticraft.common.core.tile.TileEntityCargoUnloader;
import team.galacticraft.galacticraft.common.core.util.EnumColor;
import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiCargoUnloader extends GuiContainerGC<ContainerCargoUnloader>
{
    private static final ResourceLocation UNLOADER_TEXTURE = new ResourceLocation(Constants.MOD_ID_CORE, "textures/gui/cargo_loader.png");

    private final TileEntityCargoUnloader cargoUnloader;

    private Button buttonLoadItems;
    private final GuiElementInfoRegion electricInfoRegion = new GuiElementInfoRegion((this.width - this.imageWidth) / 2 + 107, (this.height - this.imageHeight) / 2 + 101, 56, 9, new ArrayList<String>(), this.width, this.height, this);

    public GuiCargoUnloader(ContainerCargoUnloader container, Inventory playerInv, Component title)
    {
        super(container, playerInv, title);
//        super(new ContainerCargoLoader(playerInv, unloader), playerInv, new TranslationTextComponent("container.cargo_unloader"));
        this.cargoUnloader = (TileEntityCargoUnloader) container.getCargoTile();
        this.imageHeight = 201;
    }

    @Override
    protected void init()
    {
        super.init();
        List<String> electricityDesc = new ArrayList<String>();
        electricityDesc.add(I18n.get("gui.energy_storage.desc.0"));
        electricityDesc.add(EnumColor.YELLOW + I18n.get("gui.energy_storage.desc.1") + ((int) Math.floor(this.cargoUnloader.getEnergyStoredGC()) + " / " + (int) Math.floor(this.cargoUnloader.getMaxEnergyStoredGC())));
        this.electricInfoRegion.tooltipStrings = electricityDesc;
        this.electricInfoRegion.xPosition = (this.width - this.imageWidth) / 2 + 107;
        this.electricInfoRegion.yPosition = (this.height - this.imageHeight) / 2 + 101;
        this.electricInfoRegion.parentWidth = this.width;
        this.electricInfoRegion.parentHeight = this.height;
        this.infoRegions.add(this.electricInfoRegion);
        List<String> batterySlotDesc = new ArrayList<String>();
        batterySlotDesc.add(I18n.get("gui.battery_slot.desc.0"));
        batterySlotDesc.add(I18n.get("gui.battery_slot.desc.1"));
        this.infoRegions.add(new GuiElementInfoRegion((this.width - this.imageWidth) / 2 + 9, (this.height - this.imageHeight) / 2 + 26, 18, 18, batterySlotDesc, this.width, this.height, this));
        this.buttons.add(this.buttonLoadItems = new Button(this.width / 2 - 1, this.height / 2 - 23, 76, 20, I18n.get("gui.button.unloaditems"), (button) ->
        {
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_UPDATE_DISABLEABLE_BUTTON, GCCoreUtil.getDimensionType(this.minecraft.level), new Object[]{this.cargoUnloader.getBlockPos(), 0}));
        }));
    }

    @Override
    protected void renderLabels(int par1, int par2)
    {
        int offsetX = -17;
        int offsetY = 45;
        this.font.draw(this.title.getColoredString(), 60, 12, 4210752);
        this.buttonLoadItems.active = this.cargoUnloader.disableCooldown == 0;
        this.buttonLoadItems.setMessage(!this.cargoUnloader.getDisabled(0) ? I18n.get("gui.button.stopunloading") : I18n.get("gui.button.unloaditems"));
        this.font.draw(I18n.get("gui.message.status") + ": " + this.getStatus(), 28 + offsetX, 45 + 23 - 46 + offsetY, 4210752);
        //this.font.drawString("" + this.cargoUnloader.storage.getMaxExtract(), 28 + offsetX, 56 + 23 - 46 + offsetY, 4210752);
        //		this.font.drawString(ElectricityDisplay.getDisplay(this.cargoUnloader.getVoltage(), ElectricUnit.VOLTAGE), 28 + offsetX, 68 + 23 - 46 + offsetY, 4210752);
        this.font.draw(I18n.get("container.inventory"), 8, this.imageHeight - 90, 4210752);
    }

    private String getStatus()
    {
        if (this.cargoUnloader.noTarget)
        {
            return EnumColor.DARK_RED + I18n.get("gui.status.notargetunload");
        }

        if (this.cargoUnloader.targetEmpty)
        {
            return EnumColor.DARK_RED + I18n.get("gui.status.targetempty");
        }

        if (this.cargoUnloader.targetNoInventory)
        {
            return EnumColor.DARK_RED + I18n.get("gui.status.noinvtarget");
        }

        return this.cargoUnloader.getGUIstatus();
    }

    @Override
    protected void renderBg(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GuiCargoUnloader.UNLOADER_TEXTURE);
        final int var5 = (this.width - this.imageWidth) / 2;
        final int var6 = (this.height - this.imageHeight) / 2;
        this.blit(var5, var6 + 5, 0, 0, this.imageWidth, this.imageHeight);

        List<String> electricityDesc = new ArrayList<String>();
        electricityDesc.add(I18n.get("gui.energy_storage.desc.0"));
        EnergyDisplayHelper.getEnergyDisplayTooltip(this.cargoUnloader.getEnergyStoredGC(), this.cargoUnloader.getMaxEnergyStoredGC(), electricityDesc);
//		electricityDesc.add(EnumColor.YELLOW + I18n.get("gui.energy_storage.desc.1") + ((int) Math.floor(this.cargoUnloader.getEnergyStoredGC()) + " / " + (int) Math.floor(this.cargoUnloader.getMaxEnergyStoredGC())));
        this.electricInfoRegion.tooltipStrings = electricityDesc;

        if (this.cargoUnloader.getEnergyStoredGC() > 0)
        {
            this.blit(var5 + 94, var6 + 101, 176, 0, 11, 10);
        }

        this.blit(var5 + 108, var6 + 102, 187, 0, Math.min(this.cargoUnloader.getScaledElecticalLevel(54), 54), 7);
    }
}