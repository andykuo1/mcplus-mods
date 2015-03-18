package net.minecraftplus.blowpipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModBlowpipe.MODID, version = ModBlowpipe.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModBlowpipe extends MCP
{
	public static final String MODID = "blowpipe";
	public static final String VERSION = "1.4.1";

	@Instance(MCP.MCPID + MODID)
	public static ModBlowpipe INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Item blowpipe = new ItemBlowpipe().setUnlocalizedName("blowpipe");

	public ModBlowpipe()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(blowpipe);
		MCP.ITEMS().initialize();

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

		Object[] objs = Item.itemRegistry.getKeys().toArray();
		for(Object obj : objs)
		{
			if (obj instanceof ItemSeeds)
			{
				MCP.FUELS().register((Item) obj, 25);
			}
		}
	}
}
