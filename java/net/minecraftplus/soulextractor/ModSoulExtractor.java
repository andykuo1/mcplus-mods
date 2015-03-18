package net.minecraftplus.soulextractor;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModSoulExtractor.MODID, version = ModSoulExtractor.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModSoulExtractor extends MCP
{
	public static final String MODID = "soulextractor";
	public static final String VERSION = "1.0.4";

	@Instance(MCP.MCPID + MODID)
	public static ModSoulExtractor INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Block soulExtractor = new BlockSoulExtractor().setBlockName("soul_extractor");

	public ModSoulExtractor()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(soulExtractor);
		Items.experience_bottle.setMaxStackSize(1);
		MCP.ITEMS().initialize();

		MCP.PACKETS().add(PacketAbsorbSoul.class);
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
