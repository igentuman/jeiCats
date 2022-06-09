package igentuman.jeicats.recipe;

import igentuman.jeicats.ModConfig;

public class Recipes {

    public static final RecipeManager<Recipe> ITEM = new RecipeManager<>();
    public static final RecipeManager<Recipe> FLUID = new RecipeManager<>();

    public static void init()
    {
        for(String recipe: ModConfig.fluidOutputs.fluid_recipes) {
            String[] parts = recipe.split(", ");
            String te = parts[0];
            String fluid = parts[1];
            if(te == null || te.isEmpty() || fluid == null || fluid.isEmpty()) continue;
            FLUID.add(new Recipe(
                    ItemHelper.getStackFromString(te),
                    ItemHelper.getFluidStackFromString(fluid, 1000)
            ));
        }

        for(String recipe: ModConfig.itemOutputs.item_recipes) {
            String[] parts = recipe.split(", ");
            String te = parts[0];
            String item = parts[1];
            if(te == null || te.isEmpty() || item == null || item.isEmpty()) continue;
            ITEM.add(new Recipe(
                    ItemHelper.getStackFromString(te),
                    ItemHelper.getStackFromString(item)
            ));
        }
    }
}
