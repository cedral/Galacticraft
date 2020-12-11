package team.galacticraft.galacticraft.common.core.client.screen;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import team.galacticraft.galacticraft.common.api.client.IGameScreen;
import team.galacticraft.galacticraft.common.api.client.IScreenManager;
import team.galacticraft.galacticraft.common.api.entity.ITelemetry;
import team.galacticraft.galacticraft.common.core.tile.TileEntityTelemetry;
import team.galacticraft.galacticraft.common.core.util.ColorUtil;
import team.galacticraft.galacticraft.common.core.util.GCCoreUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Method;
import java.nio.DoubleBuffer;

public class GameScreenText implements IGameScreen
{
    private float frameA;
    private float frameBx;
    private float frameBy;
    private int yPos;
    private DoubleBuffer planes;
    private Method renderModelMethod;
    private Method renderLayersMethod;

    public GameScreenText()
    {
        if (GCCoreUtil.getEffectiveSide().isClient())
        {
            planes = BufferUtils.createDoubleBuffer(4 * Double.SIZE);
            try
            {
                Class clazz = LivingEntityRenderer.class;
                int count = 0;
                for (Method m : clazz.getDeclaredMethods())
                {
                    String s = m.getName();
                    if (s.equals(GCCoreUtil.isDeobfuscated() ? "renderModel" : "func_77036_a"))
                    {
                        m.setAccessible(true);
                        this.renderModelMethod = m;
                        if (count == 1)
                        {
                            break;
                        }
                        count = 1;
                    }
                    else if (s.equals(GCCoreUtil.isDeobfuscated() ? "renderLayers" : "func_177093_a"))
                    {
                        m.setAccessible(true);
                        this.renderLayersMethod = m;
                        if (count == 1)
                        {
                            break;
                        }
                        count = 1;
                    }
                }
            }
            catch (Exception e)
            {
            }
        }
    }

    @Override
    public void setFrameSize(float frameSize)
    {
        this.frameA = frameSize;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void render(int type, float ticks, float sizeX, float sizeY, IScreenManager scr)
    {
        DrawGameScreen screen = (DrawGameScreen) scr;

        frameBx = sizeX - frameA;
        frameBy = sizeY - frameA;
        drawBlackBackground(0.0F);
        planeEquation(frameA, frameA, 0, frameA, frameBy, 0, frameA, frameBy, 1);
        GL11.glClipPlane(GL11.GL_CLIP_PLANE0, planes);
        GL11.glEnable(GL11.GL_CLIP_PLANE0);
        planeEquation(frameBx, frameBy, 0, frameBx, frameA, 0, frameBx, frameA, 1);
        GL11.glClipPlane(GL11.GL_CLIP_PLANE1, planes);
        GL11.glEnable(GL11.GL_CLIP_PLANE1);
        planeEquation(frameA, frameBy, 0, frameBx, frameBy, 0, frameBx, frameBy, 1);
        GL11.glClipPlane(GL11.GL_CLIP_PLANE2, planes);
        GL11.glEnable(GL11.GL_CLIP_PLANE2);
        planeEquation(frameBx, frameA, 0, frameA, frameA, 0, frameA, frameA, 1);
        GL11.glClipPlane(GL11.GL_CLIP_PLANE3, planes);
        GL11.glEnable(GL11.GL_CLIP_PLANE3);
        yPos = 0;

        TileEntityTelemetry telemeter = TileEntityTelemetry.getNearest(screen.driver);
        //Make the text to draw.  To look good it's important the width and height
        //of the whole text box are correctly set here.
        String strName = "";
        String[] str = {I18n.get("gui.display.nolink"), "", "", "", ""};
        EntityRenderer renderEntity = null;
        Entity entity = null;
        float Xmargin = 0;

        if (telemeter != null && telemeter.clientData.length >= 3)
        {
            if (telemeter.clientType != null)
            {
                if (telemeter.clientType == screen.telemetryLastType && (telemeter.clientType != EntityType.PLAYER || telemeter.clientName.equals(screen.telemetryLastName)))
                {
                    //Used cached data from last time if possible
                    entity = screen.telemetryLastEntity;
                    renderEntity = screen.telemetryLastRender;
                    strName = screen.telemetryLastName;
                }
                else
                {
                    //Create an entity to render, based on class, and get its name
                    entity = null;

                    if (telemeter.clientType == EntityType.PLAYER)
                    {
                        strName = telemeter.clientName;
                        entity = new RemotePlayer((ClientLevel) screen.driver.getLevel(), telemeter.clientGameProfile);
                        renderEntity = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
                    }
                    else
                    {
                        try
                        {
                            entity = telemeter.clientType.create(screen.driver.getLevel());
                        }
                        catch (Exception ex)
                        {
                        }
                        if (entity != null)
                        {
                            strName = entity.getName().getColoredString();
                        }
                        renderEntity = Minecraft.getInstance().getEntityRenderDispatcher().renderers.get(entity.getClass());
                    }
                }

                //Setup special visual types from data sent by Telemetry
                if (entity instanceof Horse)
                {
//                    ((EntityHorse) entity).setType(HorseType.values()[telemeter.clientData[3]]);
                    ((Horse) entity).setVariant(telemeter.clientData[4]);
                }
                if (entity instanceof Villager)
                {
//                    ((VillagerEntity) entity).getVillagerData().setProfession(telemeter.clientData[3]); TODO Fix for MC 1.14+
                    ((Villager) entity).setAge(telemeter.clientData[4]);
                }
                else if (entity instanceof Wolf)
                {
//                    ((WolfEntity) entity).setCollarColor(DyeColor.byDyeDamage(telemeter.clientData[3])); TODO Fix for MC 1.14+
                    ((Wolf) entity).setIsInterested(telemeter.clientData[4] == 1);
                }
                else if (entity instanceof Sheep)
                {
//                    ((SheepEntity) entity).setFleeceColor(DyeColor.byDyeDamage(telemeter.clientData[3])); TODO Fix for MC 1.14+
                    ((Sheep) entity).setSheared(telemeter.clientData[4] == 1);
                }
                else if (entity instanceof Ocelot)
                {
//                    ((OcelotEntity) entity).setTameSkin(telemeter.clientData[3]); TODO Fix for MC 1.14+
                }
                else if (entity instanceof Skeleton)
                {
//                    ((EntitySkeleton) entity).setSkeletonType(SkeletonType.values()[telemeter.clientData[3]]);
                }
                else if (entity instanceof Zombie)
                {
//                    ((EntityZombie) entity).setVillager(telemeter.clientData[3] == 1); TODO Fix for MC 1.10
                    ((Zombie) entity).setBaby(telemeter.clientData[4] == 1);
                }

            }

            if (entity instanceof ITelemetry)
            {
                ((ITelemetry) entity).receiveData(telemeter.clientData, str);
            }
            else if (entity instanceof LivingEntity)
            {
                //Living entity:
                //  data0 = time to show red damage
                //  data1 = health in half-hearts
                //  data2 = pulse
                //  data3 = hunger (for player); horsetype (for horse)
                //  data4 = oxygen (for player); horsevariant (for horse)
                str[0] = telemeter.clientData[0] > 0 ? I18n.get("gui.player.ouch") : "";
                if (telemeter.clientData[1] >= 0)
                {
                    str[1] = I18n.get("gui.player.health") + ": " + telemeter.clientData[1] + "%";
                }
                else
                {
                    str[1] = "";
                }
                str[2] = "" + telemeter.clientData[2] + " " + I18n.get("gui.player.bpm");
                if (telemeter.clientData[3] > -1)
                {
                    str[3] = I18n.get("gui.player.food") + ": " + telemeter.clientData[3] + "%";
                }
                if (telemeter.clientData[4] > -1)
                {
                    int oxygen = telemeter.clientData[4];
                    oxygen = (oxygen % 4096) + (oxygen / 4096);
                    if (oxygen == 180 || oxygen == 90)
                    {
                        str[4] = I18n.get("gui.oxygen_storage.desc.1") + ": OK";
                    }
                    else
                    {
                        str[4] = I18n.get("gui.oxygen_storage.desc.1") + ": " + this.makeOxygenString(oxygen) + I18n.get("gui.seconds");
                    }
                }
            }
            else
                //Generic - could be boats or minecarts etc - just show the speed
                //TODO  can add more here, e.g. position data?
                if (telemeter.clientData[2] >= 0)
                {
                    str[2] = makeSpeedString(telemeter.clientData[2]);
                }
        }
        else
        {
            //Default - draw a simple time display just to show the Display Screen is working
            Level w1 = screen.driver.getLevel();
            int time1 = w1 != null ? (int) ((w1.getDayTime() + 6000L) % 24000L) : 0;
            str[2] = makeTimeString(time1 * 360);
        }

        int textWidthPixels = 155;
        int textHeightPixels = 60;  //1 lines
        if (str[3].isEmpty())
        {
            textHeightPixels -= 10;
        }
        if (str[4].isEmpty())
        {
            textHeightPixels -= 10;
        }

        //First pass - approximate border size
        float borders = frameA * 2 + 0.05F * Math.min(sizeX, sizeY);
        float scaleXTest = (sizeX - borders) / textWidthPixels;
        float scaleYTest = (sizeY - borders) / textHeightPixels;
        float scale = sizeX;
        if (scaleYTest < scaleXTest)
        {
            scale = sizeY;
        }
        //Second pass - the border size may be more accurate now
        borders = frameA * 2 + 0.05F * scale;
        scaleXTest = (sizeX - borders) / textWidthPixels;
        scaleYTest = (sizeY - borders) / textHeightPixels;
        scale = sizeX;
        float scaleText = scaleXTest;
        if (scaleYTest < scaleXTest)
        {
            scale = sizeY;
            scaleText = scaleYTest;
        }

        //Centre the text in the display
        float border = frameA + 0.025F * scale;
        if (entity != null && renderEntity != null)
        {
            Xmargin = (sizeX - borders) / 2;
        }
        float Xoffset = (sizeX - borders - textWidthPixels * scaleText) / 2 + Xmargin;
        float Yoffset = (sizeY - borders - textHeightPixels * scaleText) / 2 + scaleText;
        GL11.glTranslatef(border + Xoffset, border + Yoffset, 0.0F);
        GL11.glScalef(scaleText, scaleText, 1.0F);

        //Actually draw the text
        int whiteColour = ColorUtil.to32BitColor(255, 240, 216, 255);
        drawText(strName, whiteColour);
        drawText(str[0], whiteColour);
        drawText(str[1], whiteColour);
        drawText(str[2], whiteColour);
        drawText(str[3], whiteColour);
        drawText(str[4], whiteColour);

        //If there is an entity to render, draw it on the left of the text
        if (renderEntity != null && entity != null)
        {
            GL11.glTranslatef(-Xmargin / 2 / scaleText, textHeightPixels / 2 + (-Yoffset + (sizeY - borders) / 2) / scaleText, -0.0005F);
            float scalefactor = 38F / (float) Math.pow(Math.max(entity.getBbHeight(), entity.getBbWidth()), 0.65);
            GL11.glScalef(scalefactor, scalefactor, 0.0015F);
            GL11.glRotatef(180F, 0, 0, 1);
            GL11.glRotatef(180F, 0, 1, 0);
            if (entity instanceof ITelemetry)
            {
                ((ITelemetry) entity).adjustDisplay(telemeter.clientData);
            }
//            RenderPlayerGC.flagThermalOverride = true;
//            if (entity instanceof LivingEntity && renderEntity instanceof LivingRenderer && renderModelMethod != null)
//            {
//                this.renderLiving((LivingEntity) entity, (LivingRenderer) renderEntity, ticks % 1F);
//            }
//            else
//            {
//                renderEntity.doRender(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
//            }
//            RenderPlayerGC.flagThermalOverride = false;
            // TODO Player Rendering ^
//            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
//            GL11.glDisable(GL11.GL_TEXTURE_2D);
//            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        }

        //TODO  Cross-dimensional tracking (i.e. old entity setDead, new entity created)
        //TODO  Deal with text off screen (including where localizations longer than English)

        screen.telemetryLastType = (telemeter == null) ? null : telemeter.clientType;
        screen.telemetryLastEntity = entity;
        screen.telemetryLastRender = renderEntity;
        screen.telemetryLastName = strName;
        GL11.glDisable(GL11.GL_CLIP_PLANE3);
        GL11.glDisable(GL11.GL_CLIP_PLANE2);
        GL11.glDisable(GL11.GL_CLIP_PLANE1);
        GL11.glDisable(GL11.GL_CLIP_PLANE0);
    }

    // This is a simplified version of doRender() in RenderLivingEntity
    // No lighting adjustment, no sitting, no name text, no sneaking and no Forge events
    private void renderLiving(LivingEntity entity, LivingEntityRenderer render, float partialTicks)
    {
//        GlStateManager.pushMatrix();
//        GlStateManager.disableCull();
//        render.getEntityModel().isChild = entity.isChild();
//
//        try
//        {
//            float f = 0F;
//            float f1 = 0F;
//            float f2 = f1 - f;
//            float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
//            float f8 = 0F;
//            GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
//            GlStateManager.enableRescaleNormal();
//            GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
//            float f4 = 0.0625F;
//            GlStateManager.translatef(0.0F, -1.5078125F, 0.0F);
//            float f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
//            float f6 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
//
//            if (entity.isChild())
//            {
//                f6 *= 3.0F;
//            }
//
//            if (f5 > 1.0F)
//            {
//                f5 = 1.0F;
//            }
//
//            GlStateManager.enableAlphaTest();
//            render.getEntityModel().setLivingAnimations(entity, f6, f5, partialTicks);
//            render.getEntityModel().setRotationAngles(entity, f6, f5, f8, f2, f7, 0.0625F);
//
//            renderModelMethod.invoke(render, entity, f6, f5, f8, f2, f7, 0.0625F);
//            GlStateManager.depthMask(true);
//            renderLayersMethod.invoke(render, entity, f6, f5, partialTicks, f8, f2, f7, 0.0625F);
//
//            GlStateManager.disableRescaleNormal();
//        }
//        catch (Exception exception)
//        {
//        }
//
//        GlStateManager.activeTexture(GLX.GL_TEXTURE1);
//        GlStateManager.enableTexture();
//        GlStateManager.activeTexture(GLX.GL_TEXTURE0);
//        GlStateManager.enableCull();
//        GlStateManager.popMatrix(); TODO Render entity
    }

    private String makeTimeString(int l)
    {
        int hrs = l / 360000;
        int mins = l / 6000 - hrs * 60;
        int secs = l / 100 - hrs * 3600 - mins * 60;
        String hrsStr = hrs > 9 ? "" + hrs : "0" + hrs;
        String minsStr = mins > 9 ? "" + mins : "0" + mins;
        String secsStr = secs > 9 ? "" + secs : "0" + secs;
        return hrsStr + ":" + minsStr + ":" + secsStr;
    }

    public static String makeSpeedString(int speed100)
    {
        int sp1 = speed100 / 100;
        int sp2 = (speed100 % 100);
        String spstr1 = I18n.get("gui.rocket.speed") + ": " + sp1;
        String spstr2 = (sp2 > 9 ? "" : "0") + sp2;
        return spstr1 + "." + spstr2 + " " + I18n.get("gui.lander.velocityu");
    }

    private String makeHealthString(int hearts2)
    {
        int sp1 = hearts2 / 2;
        int sp2 = (hearts2 % 2) * 5;
        String spstr1 = "" + sp1;
        String spstr2 = "" + sp2;
        return spstr1 + "." + spstr2 + " hearts";
    }

    private String makeOxygenString(int oxygen)
    {
        //Server takes 1 air away every 9 ticks (OxygenUtil.getDrainSpacing)
        int sp1 = oxygen * 9 / 20;
        int sp2 = ((oxygen * 9) % 20) / 2;
        String spstr1 = "" + sp1;
        String spstr2 = "" + sp2;
        return spstr1 + "." + spstr2;
    }

    private void drawText(String str, int colour)
    {
        Minecraft.getInstance().font.draw(str, 0, yPos, colour);
        yPos += 10;
    }

    private void drawBlackBackground(float greyLevel)
    {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        final Tesselator tess = Tesselator.getInstance();
        BufferBuilder worldRenderer = tess.getBuilder();
        GL11.glColor4f(greyLevel, greyLevel, greyLevel, 1.0F);
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION);
        worldRenderer.vertex(frameA, frameBy, 0.005F).endVertex();
        worldRenderer.vertex(frameBx, frameBy, 0.005F).endVertex();
        worldRenderer.vertex(frameBx, frameA, 0.005F).endVertex();
        worldRenderer.vertex(frameA, frameA, 0.005F).endVertex();
        tess.end();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void planeEquation(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3)
    {
        double[] result = new double[4];
        result[0] = y1 * (z2 - z3) + y2 * (z3 - z1) + y3 * (z1 - z2);
        result[1] = z1 * (x2 - x3) + z2 * (x3 - x1) + z3 * (x1 - x2);
        result[2] = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);
        result[3] = -(x1 * (y2 * z3 - y3 * z2) + x2 * (y3 * z1 - y1 * z3) + x3 * (y1 * z2 - y2 * z1));
        planes.put(result, 0, 4);
        planes.position(0);
    }
}
