package net.minecraftplus.beetroot;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftplus._api.Proxy;
import net.minecraftplus._api.mod.MCP;
import net.minecraftplus._common.ItemFoodstuff;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MCP.MCPID + ModBeetroot.MODID, version = ModBeetroot.VERSION, dependencies = "required-after:" + MCP.MODID)
public class ModBeetroot extends MCP
{
	public static final String MODID = "beetroot";
	public static final String VERSION = "1.2.0";

	@Instance(MCP.MCPID + MODID)
	public static ModBeetroot INSTANCE;

	@SidedProxy(clientSide = MCP.MCPDIR + "." + MODID + "." + MCP.CLIENTPROXY, serverSide = MCP.MCPDIR + "." + MODID + "." + MCP.COMMONPROXY)
	public static Proxy proxy;

	public static final Block beetroots = new BlockBeetroot().setBlockName("beetroots");

	public static final Item beetroot = new ItemFoodstuff(2, 0.4F).setUnlocalizedName("beetroot");
	public static final Item beetrootSeeds = new ItemSeeds(beetroots, Blocks.farmland).setUnlocalizedName("beetroot_seeds");
	public static final Item beetrootSoup = new ItemFoodstuff(8, 0.6F).setUnlocalizedName("beetroot_soup");

	public ModBeetroot()
	{
		super(MODID, VERSION);
	}

	@EventHandler
	@Override
	public void PreInitialize(FMLPreInitializationEvent parEvent)
	{
		super.PreInitialize(parEvent);

		MCP.ITEMS().add(beetroots).setOreDict("cropBeetroot");
		MCP.ITEMS().add(beetroot).setOreDict("cropBeetroot");
		MCP.ITEMS().add(beetrootSeeds).setOreDict("seedBeetroot");
		MCP.ITEMS().add(beetrootSoup).setOreDict("soupBeetroot");
		MCP.ITEMS().initialize();

		this.addEventHandler(MinecraftForge.EVENT_BUS, new EventBeetrootDropHandler());

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
