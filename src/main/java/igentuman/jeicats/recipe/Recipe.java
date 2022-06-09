package igentuman.jeicats.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Recipe extends RecipeCore{
    public Recipe(ItemStack te, ItemStack outputItems, FluidStack outputFluids)
    {
        super(te, outputItems, outputFluids);
    }

    public Recipe(ItemStack te, FluidStack outputFluids)
    {
        super(te, null, outputFluids);
    }

    public Recipe(ItemStack te, ItemStack outputItems)
    {
        super(te, outputItems, null);
    }
}
