package micdoodle8.mods.galacticraft.core;

import net.minecraft.src.Achievement;
import net.minecraft.src.ItemStack;

public class GCAdvancedAchievement extends Achievement
{
    public final Achievement[] parentAchievements;
    
    public GCAdvancedAchievement(int par1, String par2Str, int par3, int par4, ItemStack par5ItemStack, Achievement[] par6Achievement)
    {
        super(par1, par2Str, par3, par4, par5ItemStack, par6Achievement[0]);
        this.parentAchievements = par6Achievement;
    }
}
