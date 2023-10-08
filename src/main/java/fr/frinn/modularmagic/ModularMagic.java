package fr.frinn.modularmagic;

import fr.frinn.modularmagic.common.CreativeTabModularMagic;
import fr.frinn.modularmagic.common.event.RegistrationEvent;
import fr.frinn.modularmagic.common.event.StarlightEventHandler;
import fr.frinn.modularmagic.common.network.StarlightMessage;
import fr.frinn.modularmagic.common.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import thaumcraft.common.tiles.essentia.TileJarFillable;

@Mod(modid = ModularMagic.MODID, name = "Modular Magic", version = "2.0.0")
public class ModularMagic {
    public static final String MODID = "modularmagic";

    public static boolean bloodmagicLoaded = false;
    public static boolean thaumcraftLoaded = false;
    public static boolean thaumicAugmentationLoaded = false;
    public static boolean thaumicJEILoaded = false;
    public static boolean extraUtils2Loaded = false;
    public static boolean astralLoaded = false;
    public static boolean naturesauraLoaded = false;
    public static boolean botaniaLoaded = false;

    @SidedProxy(modId = MODID, clientSide = "fr.frinn.modularmagic.common.proxy.ClientProxy", serverSide = "fr.frinn.modularmagic.common.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static ModularMagic INSTANCE;

    public static CreativeTabModularMagic creativeTabModularMagic =new CreativeTabModularMagic();
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    public ModularMagic() {
        MinecraftForge.EVENT_BUS.register(RegistrationEvent.class);
        new TileJarFillable();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        bloodmagicLoaded = Loader.isModLoaded("bloodmagic");
        thaumcraftLoaded = Loader.isModLoaded("thaumcraft");
        thaumicJEILoaded = Loader.isModLoaded("thaumicjei");
        extraUtils2Loaded = Loader.isModLoaded("extrautils2");
        astralLoaded = Loader.isModLoaded("astralsorcery");
        naturesauraLoaded = Loader.isModLoaded("naturesaura");
        botaniaLoaded = Loader.isModLoaded("botania");
        thaumicAugmentationLoaded = Loader.isModLoaded("thaumicaugmentation");

        if(astralLoaded) {
            MinecraftForge.EVENT_BUS.register(StarlightEventHandler.class);
            NETWORK.registerMessage(StarlightMessage.StarlightMessageHandler.class, StarlightMessage.class, 0, Side.SERVER);
            NETWORK.registerMessage(StarlightMessage.StarlightMessageHandler.class, StarlightMessage.class, 0, Side.CLIENT);
        }

        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
