package team.galacticraft.galacticraft.common.core.recipe;
//
//import java.util.ArrayList;
//
//import team.galacticraft.galacticraft.common.core.GCBlocks;
//import team.galacticraft.galacticraft.common.core.GCItems;
//import team.galacticraft.galacticraft.common.core.GalacticraftCore;
//import team.galacticraft.galacticraft.planets.mars.blocks.MarsBlocks;
//import team.galacticraft.galacticraft.planets.mars.items.MarsItems;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.oredict.OreDictionary;
//import de.ellpeck.actuallyadditions.api.ActuallyAdditionsAPI;
//
//public class CompatModuleActuallyAdditions
//{
//    public static void addRecipes()
//    {
//        ActuallyAdditionsAPI.addCrusherRecipes(OreDictionary.getOres("oreAluminum"), OreDictionary.getOres("dustAluminum"), 2, new ArrayList<ItemStack>(0), 0, 0);
//        // Silicon ore :- gives 5 silicon  (normally 1-5 depending on fortune enchantment)
//        ActuallyAdditionsAPI.addCrusherRecipe(new ItemStack(GCBlocks.basicBlock, 1, 8), new ItemStack(GCItems.basicItem, 5, 2), ItemStack.EMPTY, 0);
//        // Cheese ore :- gives 2 cheese curd (normally 1, fortune enchantment gives chance of 2)
//        ActuallyAdditionsAPI.addCrusherRecipe(new ItemStack(GCBlocks.blockMoon, 1, 2), new ItemStack(GCItems.cheeseCurd, 2, 0), ItemStack.EMPTY, 0);
//        if (GalacticraftCore.isPlanetsLoaded)
//        {
//            // Desh ore :- gives 2 raw desh (normally 1, fortune enchantment gives chance of 2)
//            ActuallyAdditionsAPI.addCrusherRecipe(new ItemStack(MarsBlocks.marsBlock, 1, 2), new ItemStack(MarsItems.marsItemBasic, 2, 0), ItemStack.EMPTY, 0);
//        }
//    }
//}