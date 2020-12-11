package team.galacticraft.galacticraft.common.core.client.gui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import team.galacticraft.galacticraft.common.api.vector.Vector2;
import team.galacticraft.galacticraft.common.api.vector.Vector3;
import team.galacticraft.galacticraft.common.Constants;
import team.galacticraft.galacticraft.common.core.GalacticraftCore;
import team.galacticraft.galacticraft.common.core.client.gui.element.*;
import team.galacticraft.galacticraft.common.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import team.galacticraft.galacticraft.common.core.client.gui.element.GuiElementGradientList.ListElement;
import team.galacticraft.galacticraft.common.core.client.gui.element.GuiElementTextBox.ITextBoxCallback;
import team.galacticraft.galacticraft.common.core.dimension.SpaceRace;
import team.galacticraft.galacticraft.common.core.dimension.SpaceRaceManager;
import team.galacticraft.galacticraft.common.core.entities.EntityFlag;
import team.galacticraft.galacticraft.common.core.entities.GCEntities;
import team.galacticraft.galacticraft.common.core.network.PacketSimple;
import team.galacticraft.galacticraft.common.core.network.PacketSimple.EnumSimplePacket;
import team.galacticraft.galacticraft.common.core.util.ColorUtil;
import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
import team.galacticraft.galacticraft.common.core.util.PlayerUtil;
import team.galacticraft.galacticraft.common.core.wrappers.FlagData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class GuiNewSpaceRace extends Screen implements ICheckBoxCallback, ITextBoxCallback
{
    protected static final ResourceLocation texture = new ResourceLocation(Constants.MOD_ID_CORE, "textures/gui/gui.png");

    public enum EnumSpaceRaceGui
    {
        MAIN,
        ADD_PLAYER,
        REMOVE_PLAYER,
        DESIGN_FLAG,
        CHANGE_TEAM_COLOR
    }

    private int ticksPassed;
    private final Player thePlayer;
    private GuiElementCheckbox checkboxPaintbrush;
    private GuiElementCheckbox checkboxShowGrid;
    private GuiElementCheckbox checkboxEraser;
    private GuiElementCheckbox checkboxSelector;
    private GuiElementCheckbox checkboxColorSelector;
    private GuiElementTextBox textBoxRename;
    private boolean initialized;
    private GuiElementSlider sliderColorR;
    private GuiElementSlider sliderColorG;
    private GuiElementSlider sliderColorB;
    private GuiElementSlider sliderBrushSize;
    private GuiElementSlider sliderEraserSize;
    private GuiElementGradientList gradientListAddPlayers;
    private GuiElementGradientList gradientListRemovePlayers;
    private EnumSpaceRaceGui currentState = EnumSpaceRaceGui.MAIN;

    private int buttonFlag_width;
    private int buttonFlag_height;
    private int buttonFlag_xPosition;
    private int buttonFlag_yPosition;
    private boolean buttonFlag_hover;

    private int buttonTeamColor_width;
    private int buttonTeamColor_height;
    private int buttonTeamColor_xPosition;
    private int buttonTeamColor_yPosition;
    private boolean buttonTeamColor_hover;

    private Vector2 flagDesignerScale = new Vector2();
    private float flagDesignerMinX;
    private float flagDesignerMinY;
    private float flagDesignerWidth;
    private float flagDesignerHeight;

    private int selectionMinX;
    private int selectionMaxX;
    private int selectionMinY;
    private int selectionMaxY;

    private final EntityFlag dummyFlag = new EntityFlag(GCEntities.FLAG, Minecraft.getInstance().level);
//    private final ModelFlag dummyModel = new ModelFlag();

    private SpaceRace spaceRaceData;
    public Map<String, Integer> recentlyInvited = new HashMap<String, Integer>();

    private boolean lastMousePressed = false;
    private boolean isDirty = true;
    private boolean canEdit;

    public GuiNewSpaceRace(Player player)
    {
        super(new TextComponent("New Space Race"));
        this.thePlayer = player;

        SpaceRace race = SpaceRaceManager.getSpaceRaceFromPlayer(PlayerUtil.getName(player));

        if (race != null)
        {
            this.spaceRaceData = race;
        }
        else
        {
            List<String> playerList = new ArrayList<String>();
            playerList.add(PlayerUtil.getName(player));
            this.spaceRaceData = new SpaceRace(playerList, SpaceRace.DEFAULT_NAME, new FlagData(48, 32), new Vector3(1, 1, 1));
        }

        this.minecraft = Minecraft.getInstance();
        this.canEdit = canPlayerEdit();
    }

    private boolean canPlayerEdit()
    {
        return PlayerUtil.getName(this.minecraft.player).equals(this.spaceRaceData.getPlayerNames().get(0));
    }

    @Override
    protected void init()
    {
        float sliderR = 0;
        float sliderG = 0;
        float sliderB = 0;

        if (this.sliderColorR != null && this.sliderColorG != null && this.sliderColorB != null)
        {
            sliderR = this.sliderColorR.getSliderPos() / (float) this.sliderColorR.getButtonHeight();
            sliderG = this.sliderColorG.getSliderPos() / (float) this.sliderColorG.getButtonHeight();
            sliderB = this.sliderColorB.getSliderPos() / (float) this.sliderColorB.getButtonHeight();
        }

        super.init();
        this.buttons.clear();

        if (this.initialized)
        {
            this.buttonFlag_width = 81;
            this.buttonFlag_height = 58;
            this.buttonFlag_xPosition = this.width / 2 - this.buttonFlag_width / 2;
            this.buttonFlag_yPosition = this.height / 2 - this.height / 3 + 10;

            this.buttonTeamColor_width = 45;
            this.buttonTeamColor_height = 45;
            this.buttonTeamColor_xPosition = this.buttonFlag_xPosition + this.buttonFlag_width + 10;
            this.buttonTeamColor_yPosition = this.buttonFlag_yPosition + this.buttonFlag_height / 2 - this.buttonTeamColor_height / 2;

            this.buttons.add(new GuiElementGradientButton(this.width / 2 - this.width / 3 + 15, this.height / 2 - this.height / 4 - 15, 50, 15, this.currentState == EnumSpaceRaceGui.MAIN ? I18n.get("gui.space_race.create.close") : I18n.get("gui.space_race.create.back"), (button) ->
            {
                if (this.currentState == EnumSpaceRaceGui.CHANGE_TEAM_COLOR)
                {
                    this.markDirty();
                }

                this.exitCurrentScreen(true);
            }));

            switch (this.currentState)
            {
            case MAIN:
                this.textBoxRename = new GuiElementTextBox(this, this.width / 2 - 75, this.buttonFlag_yPosition + this.buttonFlag_height + 10, 150, 16, I18n.get("gui.space_race.create.r"), false, 25, true);
                this.buttons.add(this.textBoxRename);
                if (this.canEdit)
                {
                    this.buttons.add(new GuiElementGradientButton(this.width / 2 - 120, this.textBoxRename.y + this.height / 10, 100, this.height / 10, I18n.get("gui.space_race.create.add_players"), (button) ->
                    {
                        if (this.currentState == EnumSpaceRaceGui.MAIN && this.canEdit)
                        {
                            this.currentState = EnumSpaceRaceGui.ADD_PLAYER;
                            this.init();
                        }
                    }));
                    this.buttons.add(new GuiElementGradientButton(this.width / 2 - 120, this.textBoxRename.y + this.height / 10 + this.height / 10 + this.height / 50, 100, this.height / 10, I18n.get("gui.space_race.create.rem_players"), (button) ->
                    {
                        if (this.currentState == EnumSpaceRaceGui.MAIN && this.canEdit)
                        {
                            this.currentState = EnumSpaceRaceGui.REMOVE_PLAYER;
                            this.init();
                        }
                    }));
                }

                GuiElementGradientButton localStats = new GuiElementGradientButton(this.width / 2 + (this.canEdit ? 20 : -50), this.textBoxRename.y + this.height / 10, 100, this.height / 10, I18n.get("gui.space_race.create.server_stats"), (button) ->
                {
                });
                GuiElementGradientButton serverStats = new GuiElementGradientButton(this.width / 2 + (this.canEdit ? 20 : -50), this.textBoxRename.y + this.height / 10 + this.height / 10 + this.height / 50, 100, this.height / 10, I18n.get("gui.space_race.create.global_stats"), (button) ->
                {
                });
                localStats.active = false;
                serverStats.active = false;
                this.buttons.add(localStats);
                this.buttons.add(serverStats);
                break;
            case ADD_PLAYER:
                this.buttons.add(new GuiElementGradientButton(this.width / 2 - this.width / 3 + 7, this.height / 2 + this.height / 4 - 15, 64, 15, I18n.get("gui.space_race.create.send_invite"), (button) ->
                {
                    if (this.currentState == EnumSpaceRaceGui.ADD_PLAYER)
                    {
                        ListElement playerToInvite = this.gradientListAddPlayers.getSelectedElement();
                        if (playerToInvite != null && !this.recentlyInvited.containsKey(playerToInvite.value))
                        {
                            SpaceRace race = SpaceRaceManager.getSpaceRaceFromPlayer(PlayerUtil.getName(this.thePlayer));
                            if (race != null)
                            {
                                GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_INVITE_RACE_PLAYER, GCCoreUtil.getDimensionType(minecraft.level), new Object[]{playerToInvite.value, race.getSpaceRaceID()}));
                                this.recentlyInvited.put(playerToInvite.value, 20 * 60);
                            }
                        }
                    }
                }));
                int xPos0 = ((GuiElementGradientButton) this.buttons.get(0)).x + this.buttons.get(0).getWidth() + 10;
                int xPos1 = this.width / 2 + this.width / 3 - 10;
                int yPos0 = this.height / 2 - this.height / 3 + 10;
                int yPos1 = this.height / 2 + this.height / 3 - 10;
                this.gradientListAddPlayers = new GuiElementGradientList(xPos0, yPos0, xPos1 - xPos0, yPos1 - yPos0);
                break;
            case REMOVE_PLAYER:
                this.buttons.add(new GuiElementGradientButton(this.width / 2 - this.width / 3 + 7, this.height / 2 + this.height / 4 - 15, 64, 15, I18n.get("gui.space_race.create.remove"), (button) ->
                {
                    if (this.currentState == EnumSpaceRaceGui.REMOVE_PLAYER)
                    {
                        ListElement playerToRemove = this.gradientListRemovePlayers.getSelectedElement();
                        if (playerToRemove != null)
                        {
                            SpaceRace race = SpaceRaceManager.getSpaceRaceFromPlayer(PlayerUtil.getName(this.thePlayer));
                            if (race != null)
                            {
                                GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_REMOVE_RACE_PLAYER, GCCoreUtil.getDimensionType(minecraft.level), new Object[]{playerToRemove.value, race.getSpaceRaceID()}));
                            }
                        }
                    }
                }));
                int xPos0b = ((GuiElementGradientButton) this.buttons.get(0)).x + this.buttons.get(0).getWidth() + 10;
                int xPos1b = this.width / 2 + this.width / 3 - 10;
                int yPos0b = this.height / 2 - this.height / 3 + 10;
                int yPos1b = this.height / 2 + this.height / 3 - 10;
                this.gradientListRemovePlayers = new GuiElementGradientList(xPos0b, yPos0b, xPos1b - xPos0b, yPos1b - yPos0b);
                break;
            case DESIGN_FLAG:
                int guiBottom = this.height / 2 + this.height / 4;
                int guiTop = this.height / 2 - this.height / 4;
                int guiRight = this.width / 2 + this.width / 3;
                int guiLeft;
                this.flagDesignerScale = new Vector2(this.width / 130.0F, this.height / 70.0F);
                this.flagDesignerMinX = this.width / 2 - this.spaceRaceData.getFlagData().getWidth() * this.flagDesignerScale.x / 2;
                this.flagDesignerMinY = this.height / 2 - this.spaceRaceData.getFlagData().getHeight() * this.flagDesignerScale.y / 2;
                this.flagDesignerWidth = this.spaceRaceData.getFlagData().getWidth() * this.flagDesignerScale.x;
                this.flagDesignerHeight = this.spaceRaceData.getFlagData().getHeight() * this.flagDesignerScale.y;
                int flagDesignerRight = (int) (this.flagDesignerMinX + this.flagDesignerWidth);
                int availWidth = (int) ((guiRight - 10 - (this.flagDesignerMinX + this.flagDesignerWidth + 10)) / 3);
                float x1 = flagDesignerRight + 10;
                float x2 = guiRight - 10;
                float y1 = guiBottom - 10 - (x2 - x1);
                int height = (int) (y1 - 10 - (guiTop + 10));
                this.sliderColorR = new GuiElementSlider(flagDesignerRight + 10, guiTop + 10, availWidth, height, true, new Vector3(0, 0, 0), new Vector3(1, 0, 0));
                this.sliderColorG = new GuiElementSlider(flagDesignerRight + 11 + availWidth, guiTop + 10, availWidth, height, true, new Vector3(0, 0, 0), new Vector3(0, 1, 0));
                this.sliderColorB = new GuiElementSlider(flagDesignerRight + 12 + availWidth * 2, guiTop + 10, availWidth, height, true, new Vector3(0, 0, 0), new Vector3(0, 0, 1));
                this.sliderColorR.setSliderPos(sliderR);
                this.sliderColorG.setSliderPos(sliderG);
                this.sliderColorB.setSliderPos(sliderB);
                this.buttons.add(this.sliderColorR);
                this.buttons.add(this.sliderColorG);
                this.buttons.add(this.sliderColorB);
                this.checkboxPaintbrush = new GuiElementCheckbox(this, (int) (this.flagDesignerMinX - 15), this.height / 2 - this.height / 4 + 10, 13, 13, 26, 26, 133, 0, "", 4210752, false);
                this.checkboxEraser = new GuiElementCheckbox(this, (int) (this.flagDesignerMinX - 15), this.height / 2 - this.height / 4 + 25, 13, 13, 26, 26, 133, 52, "", 4210752, false);
                this.checkboxSelector = new GuiElementCheckbox(this, (int) (this.flagDesignerMinX - 15), this.height / 2 - this.height / 4 + 40, 13, 13, 26, 26, 133, 78, "", 4210752, false);
                this.checkboxColorSelector = new GuiElementCheckbox(this, (int) (this.flagDesignerMinX - 15), this.height / 2 - this.height / 4 + 55, 13, 13, 26, 26, 133, 104, "", 4210752, false);
                this.checkboxShowGrid = new GuiElementCheckbox(this, (int) (this.flagDesignerMinX - 15), this.height / 2 - this.height / 4 + 90, 13, 13, 26, 26, 133, 26, "", 4210752, false);
                this.sliderBrushSize = new GuiElementSlider(this.checkboxPaintbrush.x - 40, this.checkboxPaintbrush.y, 35, 13, false, new Vector3(0.34F, 0.34F, 0.34F), new Vector3(0.34F, 0.34F, 0.34F), I18n.get("gui.space_race.create.brush_size"));
                this.sliderEraserSize = new GuiElementSlider(this.checkboxEraser.x - 40, this.checkboxEraser.y, 35, 13, false, new Vector3(0.34F, 0.34F, 0.34F), new Vector3(0.34F, 0.34F, 0.34F), I18n.get("gui.space_race.create.eraser_size"));
                this.sliderEraserSize.visible = false;
                this.buttons.add(this.checkboxPaintbrush);
                this.buttons.add(this.checkboxShowGrid);
                this.buttons.add(this.checkboxEraser);
                this.buttons.add(this.checkboxSelector);
                this.buttons.add(this.checkboxColorSelector);
                this.buttons.add(this.sliderBrushSize);
                this.buttons.add(this.sliderEraserSize);
                break;
            case CHANGE_TEAM_COLOR:
                guiBottom = this.height / 2 + this.height / 4;
                guiTop = this.height / 2 - this.height / 4;
                guiLeft = this.width / 6;
                guiRight = this.width / 2 + this.width / 3;
                flagDesignerRight = guiLeft;
                availWidth = (guiRight - guiLeft - 100) / 3;
                x1 = flagDesignerRight + 10;
                x2 = guiLeft - 10;
                y1 = guiBottom - 10 - (x2 - x1);
                height = (int) (y1 - 10 - (guiTop + 30));
                this.sliderColorR = new GuiElementSlider(flagDesignerRight + 25, guiTop + 30, availWidth, height, true, new Vector3(0, 0, 0), new Vector3(1, 0, 0));
                this.sliderColorG = new GuiElementSlider(flagDesignerRight + availWidth + 50, guiTop + 30, availWidth, height, true, new Vector3(0, 0, 0), new Vector3(0, 1, 0));
                this.sliderColorB = new GuiElementSlider(flagDesignerRight + availWidth * 2 + 75, guiTop + 30, availWidth, height, true, new Vector3(0, 0, 0), new Vector3(0, 0, 1));
                this.sliderColorR.setSliderPos(this.spaceRaceData.getTeamColor().floatX());
                this.sliderColorG.setSliderPos(this.spaceRaceData.getTeamColor().floatY());
                this.sliderColorB.setSliderPos(this.spaceRaceData.getTeamColor().floatZ());
                this.buttons.add(this.sliderColorR);
                this.buttons.add(this.sliderColorG);
                this.buttons.add(this.sliderColorB);
                break;
            default:
                break;
            }
        }
    }

    private boolean leftMouseClicked = false;

    @Override
    public boolean mouseClicked(double x, double y, int button)
    {
        if (button == 0)
        {
            leftMouseClicked = true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double x, double y, int button)
    {
        if (button == 0)
        {
            leftMouseClicked = false;
        }
        return false;
    }

    @Override
    public void onClose()
    {
        this.exitCurrentScreen(false);
    }

    @Override
    public boolean keyPressed(int key, int scanCode, int modifiers)
    {
        if (this.textBoxRename != null && this.textBoxRename.keyPressed(key, scanCode, modifiers))
        {
            return true;
        }

        return super.keyPressed(key, scanCode, modifiers);
    }

    private void exitCurrentScreen(boolean close)
    {
        if (this.currentState == EnumSpaceRaceGui.MAIN)
        {
            if (this.isDirty)
            {
                this.sendSpaceRaceData();
            }

            if (close)
            {
                if (this.canEdit)
                {
                    this.writeFlagToFile();
                }

                this.thePlayer.closeContainer();
            }
        }
        else
        {
            this.currentState = EnumSpaceRaceGui.MAIN;
            this.init();
        }
    }

    @Override
    public void tick()
    {
        super.tick();

        if (this.ticksPassed % 100 == 0)
        {
            if (this.isDirty || this.ticksPassed == 0)
            {
                this.sendSpaceRaceData();
                this.isDirty = false;
            }
            else
            {
                this.updateSpaceRaceData();
            }
        }

        ++this.ticksPassed;

        for (Entry<String, Integer> e : new HashSet<Entry<String, Integer>>(this.recentlyInvited.entrySet()))
        {
            int timeLeft = e.getValue();
            if (--timeLeft < 0)
            {
                this.recentlyInvited.remove(e.getKey());
            }
            else
            {
                this.recentlyInvited.put(e.getKey(), timeLeft);
            }
        }

        if (this.currentState == EnumSpaceRaceGui.ADD_PLAYER && this.gradientListAddPlayers != null && this.ticksPassed % 20 == 0)
        {
            List<ListElement> playerNames = new ArrayList<ListElement>();
            for (int i = 0; i < this.thePlayer.level.players().size(); i++)
            {
                Player player = this.thePlayer.level.players().get(i);

                if (player.distanceToSqr(this.thePlayer) <= 25 * 25)
                {
                    String username = PlayerUtil.getName(player);

                    if (!this.spaceRaceData.getPlayerNames().contains(username))
                    {
                        playerNames.add(new ListElement(username, this.recentlyInvited.containsKey(username) ? ColorUtil.to32BitColor(255, 250, 120, 0) : ColorUtil.to32BitColor(255, 190, 190, 190)));
                    }
                }
            }

            this.gradientListAddPlayers.updateListContents(playerNames);

            if (this.buttons.size() >= 2)
            {
                ((GuiElementGradientButton) this.buttons.get(1)).active = this.gradientListAddPlayers.getSelectedElement() != null;
            }
        }

        if (this.currentState == EnumSpaceRaceGui.REMOVE_PLAYER && this.gradientListRemovePlayers != null && this.ticksPassed % 20 == 0)
        {
            List<ListElement> playerNames = new ArrayList<ListElement>();
            for (int i = 1; i < this.spaceRaceData.getPlayerNames().size(); i++)
            {
                String playerName = this.spaceRaceData.getPlayerNames().get(i);
                playerNames.add(new ListElement(playerName, ColorUtil.to32BitColor(255, 190, 190, 190)));
            }

            this.gradientListRemovePlayers.updateListContents(playerNames);

            if (this.buttons.size() >= 2)
            {
                ((GuiElementGradientButton) this.buttons.get(1)).active = this.gradientListRemovePlayers.getSelectedElement() != null;
            }
        }

        if (this.currentState == EnumSpaceRaceGui.ADD_PLAYER && this.gradientListAddPlayers != null)
        {
            this.gradientListAddPlayers.update();
        }

        if (this.currentState == EnumSpaceRaceGui.REMOVE_PLAYER && this.gradientListRemovePlayers != null)
        {
            this.gradientListRemovePlayers.update();
        }

        if (!this.initialized)
        {
            return;
        }

        if (this.currentState == EnumSpaceRaceGui.DESIGN_FLAG)
        {
//            int x = Mouse.getEventX() * this.width / this.minecraft.displayWidth;
//            int y = this.height - Mouse.getEventY() * this.height / this.minecraft.displayHeight - 1;
            int x = (int) (minecraft.mouseHandler.xpos() * (double) minecraft.getWindow().getGuiScaledWidth() / (double) minecraft.getWindow().getScreenWidth());
            int y = (int) (minecraft.mouseHandler.ypos() * (double) minecraft.getWindow().getGuiScaledHeight() / (double) minecraft.getWindow().getScreenHeight());

            if (this.canEdit && x >= this.flagDesignerMinX && y >= this.flagDesignerMinY && x <= this.flagDesignerMinX + this.flagDesignerWidth && y <= this.flagDesignerMinY + this.flagDesignerHeight)
            {
                int unScaledX = (int) Math.floor((x - this.flagDesignerMinX) / this.flagDesignerScale.x);
                int unScaledY = (int) Math.floor((y - this.flagDesignerMinY) / this.flagDesignerScale.y);

                if (leftMouseClicked)
                {
                    if (this.checkboxEraser.isSelected != null && this.checkboxEraser.isSelected)
                    {
                        this.setColorWithBrushSize(unScaledX, unScaledY, new Vector3(255, 255, 255), (int) Math.floor(this.sliderEraserSize.getNormalizedValue() * 10) + 1);
                    }
                    else if (this.checkboxColorSelector.isSelected != null && this.checkboxColorSelector.isSelected)
                    {
                        Vector3 colorAt = this.spaceRaceData.getFlagData().getColorAt(unScaledX, unScaledY);
                        this.sliderColorR.setSliderPos(colorAt.floatX());
                        this.sliderColorG.setSliderPos(colorAt.floatY());
                        this.sliderColorB.setSliderPos(colorAt.floatZ());
                    }
                    else if (this.checkboxPaintbrush.isSelected != null && this.checkboxPaintbrush.isSelected)
                    {
                        this.setColorWithBrushSize(unScaledX, unScaledY, new Vector3(this.sliderColorR.getColorValueF(), this.sliderColorG.getColorValueF(), this.sliderColorB.getColorValueF()), (int) Math.floor(this.sliderBrushSize.getNormalizedValue() * 10) + 1);
                    }
                }
            }

            if (this.checkboxSelector != null)
            {
                if (!this.lastMousePressed && leftMouseClicked && this.checkboxSelector.isSelected != null && this.checkboxSelector.isSelected)
                {
                    if (x >= this.flagDesignerMinX && y >= this.flagDesignerMinY && x <= this.flagDesignerMinX + this.flagDesignerWidth && y <= this.flagDesignerMinY + this.flagDesignerHeight)
                    {
                        int unScaledX = (int) Math.floor((x - this.flagDesignerMinX) / this.flagDesignerScale.x);
                        int unScaledY = (int) Math.floor((y - this.flagDesignerMinY) / this.flagDesignerScale.y);
                        this.selectionMinX = unScaledX;
                        this.selectionMinY = unScaledY;
                    }
                    else
                    {
                        this.selectionMinX = this.selectionMinY = -1;
                    }
                }
                else if (this.lastMousePressed && !leftMouseClicked && this.checkboxSelector.isSelected != null && this.checkboxSelector.isSelected)
                {
                    if (this.selectionMinX != -1 && this.selectionMinY != -1 && x >= this.flagDesignerMinX && y >= this.flagDesignerMinY && x <= this.flagDesignerMinX + this.flagDesignerWidth && y <= this.flagDesignerMinY + this.flagDesignerHeight)
                    {
                        int unScaledX = (int) Math.floor((x - this.flagDesignerMinX) / this.flagDesignerScale.x);
                        int unScaledY = (int) Math.floor((y - this.flagDesignerMinY) / this.flagDesignerScale.y);
                        this.selectionMaxX = Math.min(unScaledX + 1, this.spaceRaceData.getFlagData().getWidth());
                        this.selectionMaxY = Math.min(unScaledY + 1, this.spaceRaceData.getFlagData().getHeight());

                        if (this.selectionMinX > this.selectionMaxX)
                        {
                            int temp = this.selectionMaxX - 1;
                            this.selectionMaxX = this.selectionMinX + 1;
                            this.selectionMinX = temp;
                        }

                        if (this.selectionMinY > this.selectionMaxY)
                        {
                            int temp = this.selectionMaxY - 1;
                            this.selectionMaxY = this.selectionMinY + 1;
                            this.selectionMinY = temp;
                        }
                    }
                    else
                    {
                        this.selectionMaxX = this.selectionMaxY = -1;
                    }
                }
            }

            if (this.sliderBrushSize != null && this.sliderBrushSize.visible)
            {
                this.sliderBrushSize.setMessage(I18n.get("gui.space_race.create.brush_radius") + ": " + ((int) Math.floor(this.sliderBrushSize.getNormalizedValue() * 10) + 1));
            }

            if (this.sliderEraserSize != null && this.sliderEraserSize.visible)
            {
                this.sliderEraserSize.setMessage(I18n.get("gui.space_race.create.eraser_radius") + ": " + ((int) Math.floor(this.sliderEraserSize.getNormalizedValue() * 10) + 1));
            }

            if (this.sliderColorR != null && this.sliderColorR.visible)
            {
                this.sliderColorR.setMessage(String.valueOf((int) Math.floor(this.sliderColorR.getColorValueF())));
            }

            if (this.sliderColorG != null && this.sliderColorG.visible)
            {
                this.sliderColorG.setMessage(String.valueOf((int) Math.floor(this.sliderColorG.getColorValueF())));
            }

            if (this.sliderColorB != null && this.sliderColorB.visible)
            {
                this.sliderColorB.setMessage(String.valueOf((int) Math.floor(this.sliderColorB.getColorValueF())));
            }
        }
        else if (this.currentState == EnumSpaceRaceGui.MAIN)
        {
            if (this.lastMousePressed && !leftMouseClicked)
            {
                if (this.buttonFlag_hover)
                {
                    this.currentState = EnumSpaceRaceGui.DESIGN_FLAG;
                    this.init();
                }

                if (this.buttonTeamColor_hover)
                {
                    this.currentState = EnumSpaceRaceGui.CHANGE_TEAM_COLOR;
                    this.init();
                }
            }
        }

        this.lastMousePressed = leftMouseClicked;
    }

    private void setColor(int unScaledX, int unScaledY, Vector3 color)
    {
        if (this.selectionMaxX - this.selectionMinX > 0 && this.selectionMaxY - this.selectionMinY > 0)
        {
            if (unScaledX >= this.selectionMinX && unScaledX <= this.selectionMaxX - 1 && unScaledY >= this.selectionMinY && unScaledY <= this.selectionMaxY - 1)
            {
                this.markDirty();
                this.spaceRaceData.getFlagData().setColorAt(unScaledX, unScaledY, color);
            }
        }
        else
        {
            this.markDirty();
            this.spaceRaceData.getFlagData().setColorAt(unScaledX, unScaledY, color);
        }
    }

    private void setColorWithBrushSize(int unScaledX, int unScaledY, Vector3 color, int brushSize)
    {
        for (int x = unScaledX - brushSize + 1; x < unScaledX + brushSize; x++)
        {
            for (int y = unScaledY - brushSize + 1; y < unScaledY + brushSize; y++)
            {
                if (x >= 0 && x < this.spaceRaceData.getFlagData().getWidth() && y >= 0 && y < this.spaceRaceData.getFlagData().getHeight())
                {
                    float relativeX = x + 0.5F - (unScaledX + 0.5F);
                    float relativeY = y + 0.5F - (unScaledY + 0.5F);

                    if (Math.sqrt(relativeX * relativeX + relativeY * relativeY) <= brushSize)
                    {
                        this.setColor(x, y, color);
                    }
                }
            }
        }
    }

    public void updateSpaceRaceData()
    {
        String playerName = PlayerUtil.getName(this.minecraft.player);
        SpaceRace race = SpaceRaceManager.getSpaceRaceFromPlayer(playerName);

        if (race != null && !this.isDirty)
        {
            this.spaceRaceData = race;
            this.canEdit = canPlayerEdit();

            if (!this.textBoxRename.text.equals(race.getTeamName()))
            {
                this.textBoxRename.text = race.getTeamName();
            }
        }
    }

    public void sendSpaceRaceData()
    {
        if (this.canEdit)
        {
            List<Object> objList = new ArrayList<Object>();
            objList.add(this.spaceRaceData.getSpaceRaceID());
            objList.add(this.spaceRaceData.getTeamName());
            objList.add(this.spaceRaceData.getFlagData());
            objList.add(this.spaceRaceData.getTeamColor());
            objList.add(this.spaceRaceData.getPlayerNames().toArray(new String[this.spaceRaceData.getPlayerNames().size()]));
            GalacticraftCore.packetPipeline.sendToServer(new PacketSimple(EnumSimplePacket.S_START_NEW_SPACE_RACE, GCCoreUtil.getDimensionType(minecraft.level), objList));
        }
    }

    @Override
    public void render(int par1, int par2, float par3)
    {
        this.renderBackground();

        if (this.initialized)
        {
            this.buttonFlag_hover = this.canEdit && this.currentState == EnumSpaceRaceGui.MAIN && par1 >= this.buttonFlag_xPosition && par2 >= this.buttonFlag_yPosition && par1 < this.buttonFlag_xPosition + this.buttonFlag_width && par2 < this.buttonFlag_yPosition + this.buttonFlag_height;
            this.buttonTeamColor_hover = this.canEdit && this.currentState == EnumSpaceRaceGui.MAIN && par1 >= this.buttonTeamColor_xPosition && par2 >= this.buttonTeamColor_yPosition && par1 < this.buttonTeamColor_xPosition + this.buttonTeamColor_width && par2 < this.buttonTeamColor_yPosition + this.buttonTeamColor_height;

            switch (this.currentState)
            {
            case MAIN:
                this.drawCenteredString(this.font, I18n.get("gui.space_race.create.title"), this.width / 2, this.height / 2 - this.height / 3 - 15, 16777215);
                this.drawFlagButton();
                this.drawColorButton();

                GuiElementGradientButton serverStats = (GuiElementGradientButton) this.buttons.get(this.canEdit ? 4 : 2);
                GuiElementGradientButton localStats = (GuiElementGradientButton) this.buttons.get(this.canEdit ? 5 : 3);

                if (par1 > serverStats.x && par1 < serverStats.x + serverStats.getWidth() && par2 > serverStats.y && par2 < serverStats.y + serverStats.getHeight())
                {
                    serverStats.setMessage(I18n.get("gui.space_race.coming_soon"));
                }
                else
                {
                    serverStats.setMessage(I18n.get("gui.space_race.create.server_stats"));
                }

                if (par1 > localStats.x && par1 < localStats.x + localStats.getWidth() && par2 > localStats.y && par2 < localStats.y + localStats.getHeight())
                {
                    localStats.setMessage(I18n.get("gui.space_race.coming_soon"));
                }
                else
                {
                    localStats.setMessage(I18n.get("gui.space_race.create.global_stats"));
                }

                break;
            case ADD_PLAYER:
                this.drawCenteredString(this.font, I18n.get("gui.space_race.create.invite_player"), this.width / 2, this.height / 2 - this.height / 3 - 15, 16777215);
                this.drawCenteredString(this.font, I18n.get("gui.space_race.create.player_radius"), this.width / 2, this.height / 2 + this.height / 3 + 3, ColorUtil.to32BitColor(255, 180, 40, 40));
                break;
            case REMOVE_PLAYER:
                this.drawCenteredString(this.font, I18n.get("gui.space_race.create.remove_player"), this.width / 2, this.height / 2 - this.height / 3 - 15, 16777215);
                break;
            case DESIGN_FLAG:
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GlStateManager._blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.value,
                        GlStateManager.SourceFactor.ONE.value, GlStateManager.DestFactor.ZERO.value);
                GL11.glShadeModel(GL11.GL_SMOOTH);
                Tesselator tessellator = Tesselator.getInstance();
                BufferBuilder worldRenderer = tessellator.getBuilder();

                for (int x = 0; x < this.spaceRaceData.getFlagData().getWidth(); x++)
                {
                    for (int y = 0; y < this.spaceRaceData.getFlagData().getHeight(); y++)
                    {
                        Vector3 color = this.spaceRaceData.getFlagData().getColorAt(x, y);
                        GL11.glColor4f(color.floatX(), color.floatY(), color.floatZ(), 1.0F);
                        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION);
                        worldRenderer.vertex(this.flagDesignerMinX + x * this.flagDesignerScale.x, this.flagDesignerMinY + y * this.flagDesignerScale.y + 1 * this.flagDesignerScale.y, 0.0D).endVertex();
                        worldRenderer.vertex(this.flagDesignerMinX + x * this.flagDesignerScale.x + 1 * this.flagDesignerScale.x, this.flagDesignerMinY + y * this.flagDesignerScale.y + 1 * this.flagDesignerScale.y, 0.0D).endVertex();
                        worldRenderer.vertex(this.flagDesignerMinX + x * this.flagDesignerScale.x + 1 * this.flagDesignerScale.x, this.flagDesignerMinY + y * this.flagDesignerScale.y, 0.0D).endVertex();
                        worldRenderer.vertex(this.flagDesignerMinX + x * this.flagDesignerScale.x, this.flagDesignerMinY + y * this.flagDesignerScale.y, 0.0D).endVertex();
                        tessellator.end();
                    }
                }

                if (this.checkboxShowGrid != null && this.checkboxShowGrid.isSelected != null && this.checkboxShowGrid.isSelected)
                {
                    for (int x = 0; x <= this.spaceRaceData.getFlagData().getWidth(); x++)
                    {
                        worldRenderer.begin(GL11.GL_LINES, DefaultVertexFormat.POSITION_COLOR);
                        worldRenderer.vertex(this.flagDesignerMinX + x * this.flagDesignerScale.x, this.flagDesignerMinY, this.getBlitOffset()).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
                        worldRenderer.vertex(this.flagDesignerMinX + x * this.flagDesignerScale.x, this.flagDesignerMinY + this.flagDesignerHeight, this.getBlitOffset()).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
                        tessellator.end();
                    }

                    for (int y = 0; y <= this.spaceRaceData.getFlagData().getHeight(); y++)
                    {
                        worldRenderer.begin(GL11.GL_LINES, DefaultVertexFormat.POSITION_COLOR);
                        worldRenderer.vertex(this.flagDesignerMinX, this.flagDesignerMinY + y * this.flagDesignerScale.y, this.getBlitOffset()).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
                        worldRenderer.vertex(this.flagDesignerMinX + this.flagDesignerWidth, this.flagDesignerMinY + y * this.flagDesignerScale.y, this.getBlitOffset()).color(0.0F, 0.0F, 0.0F, 1.0F).endVertex();
                        tessellator.end();
                    }
                }

                if (!(this.lastMousePressed && this.checkboxSelector.isSelected != null && this.checkboxSelector.isSelected) && this.selectionMaxX - this.selectionMinX > 0 && this.selectionMaxY - this.selectionMinY > 0)
                {
                    worldRenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);
                    float col = (float) (Math.sin(this.ticksPassed * 0.3) * 0.4 + 0.1);
                    worldRenderer.vertex(this.flagDesignerMinX + this.selectionMinX * this.flagDesignerScale.x, this.flagDesignerMinY + this.selectionMinY * this.flagDesignerScale.y, this.getBlitOffset()).color(col, col, col, 1.0F).endVertex();
                    worldRenderer.vertex(this.flagDesignerMinX + this.selectionMaxX * this.flagDesignerScale.x, this.flagDesignerMinY + this.selectionMinY * this.flagDesignerScale.y, this.getBlitOffset()).color(col, col, col, 1.0F).endVertex();
                    worldRenderer.vertex(this.flagDesignerMinX + this.selectionMaxX * this.flagDesignerScale.x, this.flagDesignerMinY + this.selectionMaxY * this.flagDesignerScale.y, this.getBlitOffset()).color(col, col, col, 1.0F).endVertex();
                    worldRenderer.vertex(this.flagDesignerMinX + this.selectionMinX * this.flagDesignerScale.x, this.flagDesignerMinY + this.selectionMaxY * this.flagDesignerScale.y, this.getBlitOffset()).color(col, col, col, 1.0F).endVertex();
                    worldRenderer.vertex(this.flagDesignerMinX + this.selectionMinX * this.flagDesignerScale.x, this.flagDesignerMinY + this.selectionMinY * this.flagDesignerScale.y, this.getBlitOffset()).color(col, col, col, 1.0F).endVertex();
                    tessellator.end();
                }

                int guiRight = this.width / 2 + this.width / 3;
                int guiBottom = this.height / 2 + this.height / 4;
                float x1 = this.sliderColorR.x;
                float x2 = guiRight - 10;
                float y1 = guiBottom - 10 - (x2 - x1);
                float y2 = guiBottom - 10;

                worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION_COLOR);
                worldRenderer.vertex(x2, y1, this.getBlitOffset()).color(0, 0, 0, 1.0F).endVertex();
                worldRenderer.vertex(x1, y1, this.getBlitOffset()).color(0, 0, 0, 1.0F).endVertex();
                worldRenderer.vertex(x1, y2, this.getBlitOffset()).color(0, 0, 0, 1.0F).endVertex();
                worldRenderer.vertex(x2, y2, this.getBlitOffset()).color(0, 0, 0, 1.0F).endVertex();
                tessellator.end();

                worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION_COLOR);
                worldRenderer.vertex((double) x2 - 1, (double) y1 + 1, this.getBlitOffset()).color(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue(), 1.0F).endVertex();
                worldRenderer.vertex((double) x1 + 1, (double) y1 + 1, this.getBlitOffset()).color(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue(), 1.0F).endVertex();
                worldRenderer.vertex((double) x1 + 1, (double) y2 - 1, this.getBlitOffset()).color(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue(), 1.0F).endVertex();
                worldRenderer.vertex((double) x2 - 1, (double) y2 - 1, this.getBlitOffset()).color(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue(), 1.0F).endVertex();
                tessellator.end();

                GL11.glShadeModel(GL11.GL_FLAT);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);

                break;
            case CHANGE_TEAM_COLOR:
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GlStateManager._blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.value,
                        GlStateManager.SourceFactor.ONE.value, GlStateManager.DestFactor.ZERO.value);
                GL11.glShadeModel(GL11.GL_SMOOTH);
                x1 = this.sliderColorG.x;
                x2 = this.sliderColorG.x + this.sliderColorG.getWidth();
                y1 = this.height / 2 - this.height / 3 + 5;
                y2 = this.sliderColorG.y - 5;
                float xDiff = x2 - x1;
                float yDiff = y2 - y1;

                if (xDiff > yDiff)
                {
                    x1 = this.sliderColorG.x + this.sliderColorG.getWidth() / 2 - yDiff / 2;
                    x2 = this.sliderColorG.x + this.sliderColorG.getWidth() / 2 + yDiff / 2;
                }
                else
                {
                    y2 = y1 + xDiff;
                }

                tessellator = Tesselator.getInstance();
                worldRenderer = tessellator.getBuilder();
                worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION_COLOR);
                worldRenderer.vertex((double) x2 - 1, (double) y1 + 1, this.getBlitOffset()).color(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue(), 1.0F).endVertex();
                worldRenderer.vertex((double) x1 + 1, (double) y1 + 1, this.getBlitOffset()).color(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue(), 1.0F).endVertex();
                worldRenderer.vertex((double) x1 + 1, (double) y2 - 1, this.getBlitOffset()).color(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue(), 1.0F).endVertex();
                worldRenderer.vertex((double) x2 - 1, (double) y2 - 1, this.getBlitOffset()).color(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue(), 1.0F).endVertex();
                tessellator.end();

                this.spaceRaceData.setTeamColor(new Vector3(this.sliderColorR.getNormalizedValue(), this.sliderColorG.getNormalizedValue(), this.sliderColorB.getNormalizedValue()));

                GL11.glShadeModel(GL11.GL_FLAT);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                break;
            }
        }

        super.render(par1, par2, par3);

        if (this.currentState == EnumSpaceRaceGui.ADD_PLAYER && this.gradientListAddPlayers != null)
        {
            this.gradientListAddPlayers.draw(par1, par2);
        }

        if (this.currentState == EnumSpaceRaceGui.REMOVE_PLAYER && this.gradientListRemovePlayers != null)
        {
            this.gradientListRemovePlayers.draw(par1, par2);
        }

        for (AbstractWidget widget : this.buttons)
        {
            if (widget instanceof GuiElementSlider)
            {
                ((GuiElementSlider) widget).drawHoveringText();
            }
        }
    }

    private void markDirty()
    {
        this.isDirty = true;
    }

    private void drawFlagButton()
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(this.buttonFlag_xPosition + 2.9F, this.buttonFlag_yPosition + this.buttonFlag_height + 1 - 4, 0);
        GL11.glScalef(74.0F, 74.0F, 1F);
        GL11.glTranslatef(0.0F, 0.36F, 1.0F);
        GL11.glScalef(1.0F, 1.0F, -1F);
        this.dummyFlag.flagData = this.spaceRaceData.getFlagData();
//        this.dummyModel.renderFlag(this.dummyFlag, this.ticksPassed); TODO Render flag
        GL11.glColor3f(1, 1, 1);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, 500.0F);
        int color = this.buttonFlag_hover ? 170 : 100;
        if (this.canEdit)
        {
            String message = I18n.get("gui.space_race.create.customize");
            this.font.draw(message, this.buttonFlag_xPosition + this.buttonFlag_width / 2 - this.font.width(message) / 2, this.buttonFlag_yPosition + this.buttonFlag_height / 2 - 5, ColorUtil.to32BitColor(255, color, color, color)/*, this.buttonFlag_hover*/);
        }
        GL11.glPopMatrix();

        if (this.buttonFlag_hover)
        {
            GuiComponent.fill(this.buttonFlag_xPosition, this.buttonFlag_yPosition, this.buttonFlag_xPosition + this.buttonFlag_width, this.buttonFlag_yPosition + this.buttonFlag_height, ColorUtil.to32BitColor(255, 50, 50, 50));
        }

        GuiComponent.fill(this.buttonFlag_xPosition + this.buttonFlag_width - 1, this.buttonFlag_yPosition, this.buttonFlag_xPosition + this.buttonFlag_width, this.buttonFlag_yPosition + this.buttonFlag_height, ColorUtil.to32BitColor(255, 0, 0, 0));
        GuiComponent.fill(this.buttonFlag_xPosition, this.buttonFlag_yPosition, this.buttonFlag_xPosition + 1, this.buttonFlag_yPosition + this.buttonFlag_height, ColorUtil.to32BitColor(255, 0, 0, 0));
        GuiComponent.fill(this.buttonFlag_xPosition, this.buttonFlag_yPosition, this.buttonFlag_xPosition + this.buttonFlag_width, this.buttonFlag_yPosition + 1, ColorUtil.to32BitColor(255, 0, 0, 0));
        GuiComponent.fill(this.buttonFlag_xPosition, this.buttonFlag_yPosition + this.buttonFlag_height - 1, this.buttonFlag_xPosition + this.buttonFlag_width, this.buttonFlag_yPosition + this.buttonFlag_height, ColorUtil.to32BitColor(255, 0, 0, 0));
    }

    private void drawColorButton()
    {
        GuiComponent.fill(this.buttonTeamColor_xPosition + 2, this.buttonTeamColor_yPosition + 2, this.buttonTeamColor_xPosition + this.buttonTeamColor_width - 2, this.buttonTeamColor_yPosition + this.buttonTeamColor_height - 2, ColorUtil.to32BitColor(255, (int) (this.spaceRaceData.getTeamColor().x * 255.0F), (int) (this.spaceRaceData.getTeamColor().y * 255.0F), (int) (this.spaceRaceData.getTeamColor().z * 255.0F)));

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, 500.0F);
        int color = this.buttonTeamColor_hover ? 170 : 100;
        if (canEdit)
        {
            this.font.draw(I18n.get("gui.space_race.create.change_color.name.0"), this.buttonTeamColor_xPosition + this.buttonTeamColor_width / 2 - this.font.width(I18n.get("gui.space_race.create.change_color.name.0")) / 2, this.buttonTeamColor_yPosition + this.buttonTeamColor_height / 2 - 13, ColorUtil.to32BitColor(255, color, color, color)/*, this.buttonTeamColor_hover*/);
        }
        this.font.draw(I18n.get("gui.space_race.create.change_color.name.1"), this.buttonTeamColor_xPosition + this.buttonTeamColor_width / 2 - this.font.width(I18n.get("gui.space_race.create.change_color.name.1")) / 2, this.buttonTeamColor_yPosition + this.buttonTeamColor_height / 2 - (canEdit ? 3 : 9), ColorUtil.to32BitColor(255, color, color, color)/*, this.buttonTeamColor_hover*/);
        this.font.draw(I18n.get("gui.space_race.create.change_color.name.2"), this.buttonTeamColor_xPosition + this.buttonTeamColor_width / 2 - this.font.width(I18n.get("gui.space_race.create.change_color.name.2")) / 2, this.buttonTeamColor_yPosition + this.buttonTeamColor_height / 2 + (canEdit ? 7 : 1), ColorUtil.to32BitColor(255, color, color, color)/*, this.buttonTeamColor_hover*/);
        GL11.glPopMatrix();

        if (this.buttonTeamColor_hover)
        {
            GuiComponent.fill(this.buttonTeamColor_xPosition, this.buttonTeamColor_yPosition, this.buttonTeamColor_xPosition + this.buttonTeamColor_width, this.buttonTeamColor_yPosition + this.buttonTeamColor_height, ColorUtil.to32BitColor(55, 50, 50, 50));
        }

        GuiComponent.fill(this.buttonTeamColor_xPosition + this.buttonTeamColor_width - 1, this.buttonTeamColor_yPosition, this.buttonTeamColor_xPosition + this.buttonTeamColor_width, this.buttonTeamColor_yPosition + this.buttonTeamColor_height, ColorUtil.to32BitColor(255, 0, 0, 0));
        GuiComponent.fill(this.buttonTeamColor_xPosition, this.buttonTeamColor_yPosition, this.buttonTeamColor_xPosition + 1, this.buttonTeamColor_yPosition + this.buttonTeamColor_height, ColorUtil.to32BitColor(255, 0, 0, 0));
        GuiComponent.fill(this.buttonTeamColor_xPosition, this.buttonTeamColor_yPosition, this.buttonTeamColor_xPosition + this.buttonTeamColor_width, this.buttonTeamColor_yPosition + 1, ColorUtil.to32BitColor(255, 0, 0, 0));
        GuiComponent.fill(this.buttonTeamColor_xPosition, this.buttonTeamColor_yPosition + this.buttonTeamColor_height - 1, this.buttonTeamColor_xPosition + this.buttonTeamColor_width, this.buttonTeamColor_yPosition + this.buttonTeamColor_height, ColorUtil.to32BitColor(255, 0, 0, 0));
    }

    @Override
    public void renderBackground(int i)
    {
        if (this.minecraft.level != null)
        {
            int scaleX = Math.min(this.ticksPassed * 14, this.width / 3);
            int scaleY = Math.min(this.ticksPassed * 14, this.height / 3);

            if (scaleX == this.width / 3 && scaleY == this.height / 3 && !this.initialized)
            {
                this.initialized = true;
                this.init();
            }

            this.fillGradient(this.width / 2 - scaleX, this.height / 2 - scaleY, this.width / 2 + scaleX, this.height / 2 + scaleY, -1072689136, -804253680);
        }
        else
        {
            super.renderBackground(i);
        }
    }

    @Override
    public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
    {
        if (checkbox.equals(this.checkboxEraser))
        {
            if (newSelected)
            {
                this.sliderEraserSize.visible = true;

                if (this.checkboxPaintbrush.isSelected)
                {
                    this.sliderBrushSize.visible = false;
                    this.checkboxPaintbrush.isSelected = false;
                }
                else if (this.checkboxSelector.isSelected)
                {
                    this.checkboxSelector.isSelected = false;
                }
                else if (this.checkboxColorSelector.isSelected)
                {
                    this.checkboxColorSelector.isSelected = false;
                }
            }
            else
            {
                this.sliderEraserSize.visible = false;
            }
        }
        else if (checkbox.equals(this.checkboxPaintbrush))
        {
            if (newSelected)
            {
                this.sliderBrushSize.visible = true;

                if (this.checkboxEraser.isSelected)
                {
                    this.sliderEraserSize.visible = false;
                    this.checkboxEraser.isSelected = false;
                }
                else if (this.checkboxSelector.isSelected)
                {
                    this.checkboxSelector.isSelected = false;
                }
                else if (this.checkboxColorSelector.isSelected)
                {
                    this.checkboxColorSelector.isSelected = false;
                }
            }
            else
            {
                this.sliderBrushSize.visible = false;
            }
        }
        else if (checkbox.equals(this.checkboxSelector))
        {
            if (newSelected)
            {
                if (this.checkboxEraser.isSelected)
                {
                    this.sliderEraserSize.visible = false;
                    this.checkboxEraser.isSelected = false;
                }
                else if (this.checkboxPaintbrush.isSelected)
                {
                    this.sliderBrushSize.visible = false;
                    this.checkboxPaintbrush.isSelected = false;
                }
                else if (this.checkboxColorSelector.isSelected)
                {
                    this.checkboxColorSelector.isSelected = false;
                }
            }
        }
        else if (checkbox.equals(this.checkboxColorSelector))
        {
            if (newSelected)
            {
                if (this.checkboxEraser.isSelected)
                {
                    this.sliderEraserSize.visible = false;
                    this.checkboxEraser.isSelected = false;
                }
                else if (this.checkboxPaintbrush.isSelected)
                {
                    this.sliderBrushSize.visible = false;
                    this.checkboxPaintbrush.isSelected = false;
                }
                else if (this.checkboxSelector.isSelected)
                {
                    this.checkboxSelector.isSelected = false;
                }
            }
        }
    }

    @Override
    public boolean canPlayerEdit(GuiElementCheckbox checkbox, Player player)
    {
        return true;
    }

    @Override
    public boolean getInitiallySelected(GuiElementCheckbox checkbox)
    {
        return checkbox.equals(this.checkboxPaintbrush);

    }

    @Override
    public void onIntruderInteraction()
    {

    }

    @Override
    public void onIntruderInteraction(GuiElementTextBox textBox)
    {

    }

    @Override
    public boolean canPlayerEdit(GuiElementTextBox textBox, Player player)
    {
        return this.canEdit;
    }

    @Override
    public void onTextChanged(GuiElementTextBox textBox, String newText)
    {
        if (textBox == this.textBoxRename)
        {
            if (!newText.equals(this.spaceRaceData.getTeamName()))
            {
                this.spaceRaceData.setTeamName(newText);
                this.markDirty();
            }
        }
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    @Override
    public String getInitialText(GuiElementTextBox textBox)
    {
        if (textBox == this.textBoxRename)
        {
            return this.spaceRaceData.getTeamName();
        }

        return "";
    }

    @Override
    public int getTextColor(GuiElementTextBox textBox)
    {
        return ColorUtil.to32BitColor(255, 255, 255, 255);
    }

    private File writeFlagToFile()
    {
        try
        {
            String dirName = Minecraft.getInstance().gameDirectory.getAbsolutePath();
            File directory = new File(dirName, "assets");
            boolean success = true;
            if (!directory.exists())
            {
                success = directory.mkdir();
            }
            if (success)
            {
                directory = new File(directory, "flagCache");
                if (!directory.exists())
                {
                    success = directory.mkdir();
                }

                if (success)
                {
                    File file = new File(directory, this.spaceRaceData.getSpaceRaceID() + ".png");
                    ImageIO.write(this.spaceRaceData.getFlagData().toBufferedImage(), "png", file);
                    return file;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
