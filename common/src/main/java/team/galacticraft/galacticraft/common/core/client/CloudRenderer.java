package team.galacticraft.galacticraft.common.core.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.client.IRenderHandler;

public class CloudRenderer implements IRenderHandler
{
    @Override
    @Environment(EnvType.CLIENT)
    public void render(int ticks, float partialTicks, ClientLevel world, Minecraft mc)
    {
        // Do nothing
    }
}