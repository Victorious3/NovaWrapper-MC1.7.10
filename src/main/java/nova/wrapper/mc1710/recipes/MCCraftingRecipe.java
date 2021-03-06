package nova.wrapper.mc1710.recipes;

import net.minecraft.item.crafting.IRecipe;
import nova.core.item.ItemStack;
import nova.core.recipes.crafting.CraftingGrid;
import nova.core.recipes.crafting.CraftingRecipe;
import nova.wrapper.mc1710.util.WrapUtility;

import java.util.Optional;

/**
 * @author Stan Hebben
 */
public class MCCraftingRecipe implements CraftingRecipe {
    private final IRecipe recipe;

    public MCCraftingRecipe(IRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean matches(CraftingGrid craftingGrid) {
        // TODO: supply world somehow?
        return recipe.matches(new NovaCraftingGrid(craftingGrid), null);
    }

    @Override
    public Optional<ItemStack> getCraftingResult(CraftingGrid craftingGrid) {
        return WrapUtility.unwrapItemStack(recipe.getCraftingResult(new NovaCraftingGrid(craftingGrid)));
    }

    @Override
    public void consumeItems(CraftingGrid craftingGrid) {
        // not supported
    }

    @Override
    public Optional<ItemStack> getNominalOutput() {
        return WrapUtility.unwrapItemStack(recipe.getRecipeOutput());
    }
}
