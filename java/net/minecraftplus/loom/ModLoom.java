package net.minecraftplus.loom;

import net.minecraft.block.Block;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModLoom.MODID, version = ModLoom.VERSION, dependencies = "required-after:" + MCP.MODID + ";" + "required-after:" + MCP.MCPID + "wheel")
public class ModLoom extends MCP
{
	public static final String MODID = "loom";
	public static final String VERSION = "1.2.4";

	@Instance(MCP.MCPID + MODID)
	public static ModLoom INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Block loom = new BlockLoom().setBlockName("wooden_loom");

	public ModLoom()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(loom).setIcon("planks_oak", MCP.MINECRAFT);
		MCP.ITEMS().initialize();

		this.addGuiHandler(new GuiHandler());

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
