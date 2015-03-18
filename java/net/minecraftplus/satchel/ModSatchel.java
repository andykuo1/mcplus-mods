package net.minecraftplus.satchel;

import net.minecraft.item.Item;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModSatchel.MODID, version = ModSatchel.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModSatchel extends MCP
{
	public static final String MODID = "satchel";
	public static final String VERSION = "1.3.3";

	@Instance(MCP.MCPID + MODID)
	public static ModSatchel INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Item satchel = new ItemSatchel().setUnlocalizedName("satchel");
	public static final Item enderSatchel = new ItemEnderSatchel().setUnlocalizedName("ender_satchel");

	public ModSatchel()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(satchel);
		MCP.ITEMS().add(enderSatchel);
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
