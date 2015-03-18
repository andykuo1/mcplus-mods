package net.minecraftplus.sickle;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModSickle.MODID, version = ModSickle.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModSickle extends MCP
{
	public static final String MODID = "sickle";
	public static final String VERSION = "1.0.1";

	@Instance(MCP.MCPID + MODID)
	public static ModSickle INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Item sickle = new ItemSickle().setUnlocalizedName("sickle");
	
	public ModSickle()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);
		
		MCP.ITEMS().add(sickle);
		MCP.ITEMS().initialize();
		
		this.addEventHandler(MinecraftForge.EVENT_BUS, new EventSickleHandler());
		
		proxy.register();
	}

	@EventHandler
	@Override
	public void Initialize(FMLInitializationEvent parEvent)
	{
		super.Initialize(parEvent);
	}

	@EventHandler
	@Override
	public void PostInitialize(FMLPostInitializationEvent parEvent)
	{
		super.PostInitialize(parEvent);
	}
}
