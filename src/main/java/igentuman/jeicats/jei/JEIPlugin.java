package igentuman.jeicats.jei;

import igentuman.jeicats.recipe.Recipe;
import igentuman.jeicats.recipe.Recipes;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@mezz.jei.api.JEIPlugin
public class JEIPlugin implements IModPlugin {
    public JEIPlugin() {
        super();
    }

    @Override
    public void register(IModRegistry registry) {
        registry.handleRecipes(Recipe.class, ItemRecipeCategory.Wrapper::new, ItemRecipeCategory.UID);
        registry.addRecipes(Recipes.ITEM.getAll(), ItemRecipeCategory.UID);

        registry.handleRecipes(Recipe.class, FluidRecipeCategory.Wrapper::new, FluidRecipeCategory.UID);
        registry.addRecipes(Recipes.FLUID.getAll(), FluidRecipeCategory.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        final IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new FluidRecipeCategory(guiHelper));
        registry.addRecipeCategories(new ItemRecipeCategory(guiHelper));
    }

}
