package igentuman.jeicats;

import net.minecraftforge.common.config.Config;

@Config(modid = ModInfo.MODID)
public class  ModConfig {
    public static FluidOutputs fluidOutputs = new FluidOutputs();
    public static ItemOutputs itemOutputs = new ItemOutputs();

    public static class FluidOutputs {
        @Config.Name("fluid_recipes")
        @Config.Comment({
                "First item is a machine block always.",
                "Format: somemod:machine@meta, fluid",
                "Example: minecraft:furnace, lava"
        })

        public String[] fluid_recipes = new String[] {

        };
    }

    public static class ItemOutputs {
        @Config.Name("item_recipes")
        @Config.Comment({
                "First item is a machine block always.",
                "Format: somemod:machine@meta, somemod:item@meta",
                "Example: minecraft:furnace, minecraft:stone"
        })

        public String[] item_recipes = new String[] {

        };
    }
}
