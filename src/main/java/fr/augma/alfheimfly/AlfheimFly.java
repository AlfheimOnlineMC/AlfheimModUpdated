package fr.augma.alfheimfly;

import fr.augma.alfheimfly.capabilities.PlayerDataCapProvider;
import fr.augma.alfheimfly.command.RaceRemoveCommand;
import fr.augma.alfheimfly.command.polish;
import fr.augma.alfheimfly.command.setSkin;
import fr.augma.alfheimfly.command.testRune;
import fr.augma.alfheimfly.common.AlfheimCommon;
import fr.augma.alfheimfly.ctab.AlfheimTab;
import fr.augma.alfheimfly.init.RaceInit;
import fr.augma.alfheimfly.init.RecipiesInit;
import fr.augma.alfheimfly.init.BlockInit;
import fr.augma.alfheimfly.init.EntityInit;
import fr.augma.alfheimfly.init.ItemInit;
import fr.augma.alfheimfly.packet.BukkitPacket;
import fr.augma.alfheimfly.packet.GuiClassOpen;
import fr.augma.alfheimfly.packet.RefreshCaitPlayer;
import fr.augma.alfheimfly.packet.SCPacketDataCap;
import fr.augma.alfheimfly.packet.SelectClassPacket;
import fr.augma.alfheimfly.packet.SyncWingsPacket;
import fr.augma.alfheimfly.packet.ToggleFlyPacket;
import fr.augma.alfheimfly.utils.AlfheimRef;
import fr.augma.alfheimfly.utils.handlers.AlfheimGuiHandler;
import fr.augma.alfheimfly.utils.handlers.SoundsHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import software.bernie.geckolib3.GeckoLib;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

@Mod(modid = AlfheimRef.MODID, name = AlfheimRef.NAME, version = AlfheimRef.VERSION, dependencies = "required-after:geckolib3;")
public class AlfheimFly {

    public static Logger logger;
    public static Map<String, Float> expEntityValue = new HashMap<>();

    @Mod.Instance
    public static AlfheimFly INSTANCE;

    @SidedProxy(clientSide = AlfheimRef.CLIENT_PROXY, serverSide = AlfheimRef.SERVER_PROXY)
    public static AlfheimCommon PROXY;
    public static SimpleNetworkWrapper network;
    public static SimpleNetworkWrapper network2;
    public static CreativeTabs ALFHEIM_TAB = new AlfheimTab(AlfheimRef.CREATIVE_TAB);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	logger = event.getModLog();
        PROXY.preinit(event.getSuggestedConfigurationFile());
        BlockInit.init();
        ItemInit.init();
        EntityInit.registerEntities();
        RecipiesInit.init();
        initEntityExpValue();
        INSTANCE = this;
        
        network = new SimpleNetworkWrapper(AlfheimRef.MODID);
        
        network.registerMessage(SCPacketDataCap.Handler.class, SCPacketDataCap.class, 0, Side.CLIENT);
        network.registerMessage(GuiClassOpen.Handler.class, GuiClassOpen.class, 1, Side.CLIENT);
        network.registerMessage(SelectClassPacket.Handler.class, SelectClassPacket.class , 2, Side.SERVER);
        network.registerMessage(RefreshCaitPlayer.Handler.class, RefreshCaitPlayer.class, 3, Side.CLIENT);
        network.registerMessage(ToggleFlyPacket.Handler.class, ToggleFlyPacket.class, 4, Side.SERVER);
        network.registerMessage(SyncWingsPacket.Handler.class, SyncWingsPacket.class, 5, Side.CLIENT);
        
        network2 = new SimpleNetworkWrapper("bukkitToForge");
        network2.registerMessage(BukkitPacket.Handler.class, BukkitPacket.class, 0, Side.CLIENT);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	GeckoLib.initialize();
        PROXY.init();
        RaceInit.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(AlfheimFly.INSTANCE, new AlfheimGuiHandler());
        PlayerDataCapProvider.register();
        SoundsHandler.registerSounds();
    }
    
    @EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
    	event.registerServerCommand(new RaceRemoveCommand());
    	event.registerServerCommand(new setSkin());
    	event.registerServerCommand(new testRune());
        event.registerServerCommand(new polish());
    }
    
    private static void initEntityExpValue() {
    	expEntityValue.put("zombie", 2000f);
    	expEntityValue.put("creeper", 4f);
    	expEntityValue.put("spider", 3f);
    	expEntityValue.put("skeleton", 4.5f);
    	expEntityValue.put("animal", 1.5f);
    	expEntityValue.put("other", 1f);
    }
}
