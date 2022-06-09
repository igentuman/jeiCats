package igentuman.jeicats.jei;

import com.google.common.collect.ImmutableList;
import igentuman.jeicats.recipe.Recipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static igentuman.jeicats.ModInfo.MODID;

@ParametersAreNonnullByDefault
public class FluidRecipeCategory implements IRecipeCategory<FluidRecipeCategory.Wrapper>
{
    public static String UID = MODID + "_fluid_category";

    private static final ResourceLocation GUI_LOCATION = new ResourceLocation(MODID, "textures/gui/jei/jeicats_fluids.png");
    private static final String TRANSLATION_KEY = MODID + ".jei.category.fluids";

    private final IDrawable background;
    private final IDrawableAnimated animatedArrow;

    public FluidRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(GUI_LOCATION, 0, 0, 74, 20);
        IDrawableStatic staticArrow = guiHelper.createDrawable(GUI_LOCATION, 0, 20, 26, 20);
        animatedArrow = guiHelper.createAnimatedDrawable(staticArrow, 200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Nonnull
    @Override
    public String getUid()
    {
        return UID;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return I18n.format(TRANSLATION_KEY);
    }

    @Nonnull
    @Override
    public String getModName()
    {
        return MODID;
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawExtras(Minecraft minecraft)
    {
        animatedArrow.draw(minecraft, 25, 2);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, Wrapper wrapper, IIngredients ingredients)
    {
        int index = 0;
        recipeLayout.getItemStacks().init(index, true, 0, 0);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(ItemStack.class).get(0));

        index++;
        recipeLayout.getFluidStacks().init(index, false, 57, 1);
        recipeLayout.getFluidStacks().set(index, ingredients.getOutputs(FluidStack.class).get(0));
    }

    public static class Wrapper implements IRecipeWrapper
    {
        private final List<List<ItemStack>> input;
        private final List<List<FluidStack>> output;

        public Wrapper(Recipe recipe)
        {
            ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();

            // Add the ingredient
            builder.add(recipe.getInput().getStacks());

            // Set the input
            this.input = builder.build();

            // Reset builder and add output
            ImmutableList.Builder<List<FluidStack>> builderOut = ImmutableList.builder();
            builderOut.add(ImmutableList.of(recipe.getFluidOutput()));

            // Set the output
            output = builderOut.build();

        }

        @Override
        public void getIngredients(@Nonnull IIngredients ingredients)
        {
            ingredients.setInputLists(ItemStack.class, input);
            ingredients.setOutputLists(FluidStack.class, output);
        }
    }
}
