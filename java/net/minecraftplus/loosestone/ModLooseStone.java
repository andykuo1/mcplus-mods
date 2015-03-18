package net.minecraftplus.loosestone;

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

@Mod(modid = MCP.MCPID + ModLooseStone.MODID, version = ModLooseStone.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModLooseStone extends MCP
{
	public static final String MODID = "loosestone";
	public static final String VERSION = "1.1.0";

	@Instance(MCP.MCPID + MODID)
	public static ModLooseStone INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Block looseStone = new BlockLooseStone().setBlockName("loose_stone");

	public ModLooseStone()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(looseStone).setIcon("stone", MCP.MINECRAFT);
		MCP.ITEMS().initialize();

		MCP.WORLDGENS().add(new WorldGenLooseStone());

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
