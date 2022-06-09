package igentuman.jeicats;

import igentuman.jeicats.recipe.Recipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static igentuman.jeicats.ModInfo.MODID;


@Mod(
    name = ModInfo.NAME,
    modid = MODID,
    version = ModInfo.VERSION,
    acceptedMinecraftVersions = ModInfo.MC_VERSION,
    dependencies = ModInfo.DEPENDENCIES
)
public class JEIcats {

  @Mod.Instance(MODID)
  public static JEIcats instance;

  public static final Logger logger = LogManager.getLogger(MODID);

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    logger.info("Starting PreInitialization.");
    Recipes.init();
    MinecraftForge.EVENT_BUS.register(this);
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    logger.info("Starting Initialization.");
    ConfigManager.sync(MODID, Config.Type.INSTANCE);
  }

  @SubscribeEvent
  public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
    if(event.getModID().equals(MODID)) {
      ConfigManager.sync(MODID, Config.Type.INSTANCE);
    }
  }
}
