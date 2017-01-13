package darkelfe14728.personalarmor;

import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import darkelfe14728.personalarmor.core.LogHelper;
import darkelfe14728.personalarmor.core.proxy.CommonProxy;


/**
 * @author Julien Rosset
 * 
 *         Mod entry point.
 */
@Mod(
    modid = PersonalArmor.MOD_ID,
    name = PersonalArmor.MOD_NAME,
    version = PersonalArmor.MOD_VERSION)
public class PersonalArmor
{
    public static final String       MOD_ID          = "personalarmor";
    public static final String       MOD_NAME        = "Personal Armor";
    public static final String       MOD_VERSION     = "0.0.1";
    public static final String       MOD_DESCRIPTION = "Not a simply armor : powerful and modular";
    public static final List<String> MOD_AUTHORS     = Arrays.asList(new String[] {"Julien Rosset"});
    public static final String       MOD_CREDITS     = "";

    @Instance
    public static PersonalArmor      instance        = new PersonalArmor();
    @SidedProxy(
        clientSide = "darkelfe14728.personalarmor.utils.proxy.CombinedClientProxy",
        serverSide = "darkelfe14728.personalarmor.utils.proxy.DedicatedServerProxy")
    public static CommonProxy        proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LogHelper.info("Initializing mod metadata");
        ModMetadata meta = event.getModMetadata();
        meta.modId = PersonalArmor.MOD_ID;
        meta.name = PersonalArmor.MOD_NAME;
        meta.version = PersonalArmor.MOD_VERSION;
        meta.description = PersonalArmor.MOD_DESCRIPTION;
        meta.authorList = PersonalArmor.MOD_AUTHORS;
        meta.credits = PersonalArmor.MOD_CREDITS;

        PersonalArmor.proxy.preInit(event);
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(PersonalArmor.instance, new GuiHandler());

        PersonalArmor.proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        PersonalArmor.proxy.postInit(event);
    }
}
