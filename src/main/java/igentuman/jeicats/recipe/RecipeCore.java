package igentuman.jeicats.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class RecipeCore {
    protected final IRecipeIngredient ingredient;
    protected final ItemStack outputItemStack;
    protected final FluidStack outputFluidStack;
    protected final int inputAmount;

    public RecipeCore(ItemStack te, ItemStack outputItems, FluidStack outputFluidStack)
    {
        this.outputItemStack = outputItems;
        this.outputFluidStack = outputFluidStack;
        this.inputAmount = te.isEmpty() ? 0 : te.getCount();
        this.ingredient = IRecipeIngredient.of(te);
    }


    public boolean test(Object input)
    {
        return ingredient.test(input);
    }


    @Deprecated
    public boolean test(Object... inputs)
    {
        throw new UnsupportedOperationException("This recipe does not support access by multiple inputs");
    }


    public boolean matches(Object input)
    {
        return input instanceof IRecipeIngredient && ingredient.matches((IRecipeIngredient) input);
    }


    @Deprecated
    public boolean matches(Object... inputs)
    {
        throw new UnsupportedOperationException("This recipe does not support access by multiple inputs");
    }

    public ItemStack consumeInput(ItemStack stack)
    {
        return ItemHelper.consumeItem(stack, inputAmount);
    }

    @Nonnull
    public FluidStack getFluidOutput()
    {
        return outputFluidStack.copy();
    }

    @Nonnull
    public ItemStack getItemOutput()
    {
        return outputItemStack.copy();
    }


    public IRecipeIngredient getInput()
    {
        return ingredient;
    }


    public String getName()
    {
        return ingredient.getName();
    }
}
